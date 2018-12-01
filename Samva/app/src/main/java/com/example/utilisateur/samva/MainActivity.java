package com.example.utilisateur.samva;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MainActivityCallback, TabLayout.OnTabSelectedListener{

    private Retrofit retrofit;
    private RecyclerView rcvEvents;
    private EventAdapter eventsAdapter;
    private ArrayList<Event> events = new ArrayList<>();
    private List<Event> eventbis;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    MapsFragment mapsFragment;
    ListFragment listFragment;
    FragmentTest testFragment;

    private TabLayout tabLayout;
    private StatePagerAdapterFragment statePagerAdapterFragment;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("records");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Event>> eventListType = new GenericTypeIndicator<List<Event>>() {};
                //List<Event> serverEvents = (List<Event>) dataSnapshot.getValue(eventListType);
                eventbis = (List<Event>) dataSnapshot.getValue(eventListType);

                for(Event e : eventbis) {
                    events.add(e);
                }

                //createFragment();
                initLayout();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Ça n'a pas marché !", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    public void createFragment() {

        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "test :  "+ this.events.size(), Toast.LENGTH_LONG);
        toast.show();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        mapsFragment = new MapsFragment();
        listFragment = new ListFragment();
        testFragment = new FragmentTest();

        Bundle bundleEvents = new Bundle();
        bundleEvents.putSerializable("EVENTS_LIST", this.events);
        listFragment.setArguments(bundleEvents);


        //fragmentTransaction.add(R.id.container, mapsFragment);
        fragmentTransaction.add(R.id.container, listFragment);
        fragmentTransaction.commit();

    }

    public void details(Event event) {
/*
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, this.testFragment);
        fragmentTransaction.addToBackStack("tag");
        fragmentTransaction.commit();
*/


        Intent intent = new Intent(this, EventActivity.class);
        intent.putExtra("EVENT_CLICK", event);
        this.startActivity(intent);
    }

    private void initLayout() {
        //Ajouter la toolbar
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        //Init tabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Ajout des onglets
        int colorARGB = R.color.colorAccent;
        Drawable list = ContextCompat.getDrawable(this, R.drawable.ic_list);
        list.setColorFilter(getResources().getColor(colorARGB), PorterDuff.Mode.SRC_ATOP);
        Drawable maps = ContextCompat.getDrawable(this, R.drawable.ic_maps);
        maps.setColorFilter(getResources().getColor(colorARGB), PorterDuff.Mode.SRC_ATOP);
        Drawable info = ContextCompat.getDrawable(this, R.drawable.ic_info);
        info.setColorFilter(getResources().getColor(colorARGB), PorterDuff.Mode.SRC_ATOP);

        //Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.getImageId(), null);
        tabLayout.addTab(createTab("List", list));
        tabLayout.addTab(createTab("Map", maps));
        tabLayout.addTab(createTab("Info", info));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Init ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager);
        //Create and setting pager adapter
        statePagerAdapterFragment = new StatePagerAdapterFragment(getSupportFragmentManager(),tabLayout.getTabCount(), this.events);
        viewPager.setAdapter(statePagerAdapterFragment);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(this);
    }

    private TabLayout.Tab createTab(String text, Drawable icon){
        TabLayout.Tab tab = tabLayout.newTab().setText(text).setIcon(icon).setCustomView(R.layout.tab_customview);

        // remove imageView bottom margin
        if (tab.getCustomView() != null){
            ImageView imageView = (ImageView) tab.getCustomView().findViewById(android.R.id.icon);
            ViewGroup.MarginLayoutParams lp = ((ViewGroup.MarginLayoutParams) imageView.getLayoutParams());
            lp.bottomMargin = 0;
            imageView.requestLayout();
        }

        return tab;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
