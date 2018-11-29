package com.example.utilisateur.samva;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class EventActivity extends AppCompatActivity implements MyActivityCallback{

       private FragmentManager fragmentManager;
       private DetailsFragment detailsFragment;
       private CreationFragment creationFragment;

       private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        event = (Event) getIntent().getSerializableExtra("EVENT_CLICK");

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        detailsFragment = new DetailsFragment();
        creationFragment = new CreationFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("EVENT",event);
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

        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "trajet ajouté : "+ travel.getTitle() + " - " + travel.getSam(), Toast.LENGTH_LONG);
        toast.show();

    }
}
