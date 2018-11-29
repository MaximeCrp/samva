package com.example.utilisateur.samva;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

public class MainActivity extends AppCompatActivity implements MainActivityCallback{

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

                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "requête réussie, nb de events : "+ events.size(), Toast.LENGTH_LONG);
                toast.show();

                createFragment();

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
}
