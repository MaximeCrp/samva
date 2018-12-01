package com.example.utilisateur.samva;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity implements MyActivityCallback{

       private FragmentManager fragmentManager;
       private DetailsFragment detailsFragment;
       private CreationFragment creationFragment;
       private TravelFragment travelFragment;

       private Event event;

       private TravelList travelList;
       private FirebaseDatabase database = FirebaseDatabase.getInstance();
       private DatabaseReference myRef;

       private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        event = (Event) getIntent().getSerializableExtra("EVENT_CLICK");

        travelList = new TravelList();
        travelList.setTitle(event.getTitle());

        myRef = database.getReference("travels/" + event.getTitle());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<TravelList> travelListType = new GenericTypeIndicator<TravelList>() {
                };
                TravelList list = (TravelList) dataSnapshot.getValue(travelListType);


                if(list != null) {
                    travelList.setList(list.getList());
                }

                /*
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "requête réussie, nb de travels : " + travelList.getList().size(), Toast.LENGTH_LONG);
                toast.show();*/

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

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        detailsFragment = new DetailsFragment();
        creationFragment = new CreationFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("EVENT",event);
        bundle.putSerializable("TRAVEL_LIST",travelList);

        detailsFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.container, detailsFragment);
        fragmentTransaction.commit();

    }

    public void newTravel(){

        Travel travel = new Travel();
        travel.setTitle(this.event.getTitle());

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, this.creationFragment);
        fragmentTransaction.addToBackStack("tag");
        fragmentTransaction.commit();

        Bundle bundle = new Bundle();
        bundle.putSerializable("TRAVEL",travel);
        creationFragment.setArguments(bundle);
        creationFragment.notifyDataChanged();
    }

    public void addTravel(Travel travel) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, this.detailsFragment);
        fragmentTransaction.addToBackStack("tag");
        fragmentTransaction.commit();

        this.travelList.addTravel(travel);
        String name = travelList.getTitle();

        myRef = database.getReference("travels/"+name);
        myRef.setValue(travelList);
/*
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "trajet ajouté : "+ travel.getTitle() + " - " + travel.getSam(), Toast.LENGTH_LONG);
        toast.show();*/

    }

    public void travelDetails(Travel travel, int position) {

        this.position = position;

        travelFragment = new TravelFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, this.travelFragment);
        fragmentTransaction.addToBackStack("tag");
        fragmentTransaction.commit();

        Bundle bundleEvents = new Bundle();
        bundleEvents.putSerializable("TRAVEL", travel);
        travelFragment.setArguments(bundleEvents);

    }

    public void passengerAdded(Travel travel) {
        travelFragment = new TravelFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, this.detailsFragment);
        fragmentTransaction.addToBackStack("tag");
        fragmentTransaction.commit();
        detailsFragment.notifyDataChanged(travel, this.position);

        travelList.getList().set(position, travel);
        String name = travelList.getTitle();

        myRef = database.getReference("travels/"+name);
        myRef.setValue(travelList);


    }
}
