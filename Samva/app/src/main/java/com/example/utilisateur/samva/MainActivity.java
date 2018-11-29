package com.example.utilisateur.samva;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RecyclerView rcvEvents;
    private EventAdapter eventsAdapter;
    private ArrayList<Event> events = new ArrayList<>();
    private List<Event> eventbis;

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

                initlayout();
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

    public void initlayout() {

        rcvEvents = findViewById(R.id.a_main_rcv_events);
        rcvEvents.setHasFixedSize(true);
        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvEvents.setLayoutManager(layoutManager);

        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "init layout "+ events.size(), Toast.LENGTH_LONG);
        toast.show();

        // specify an adapter (see also next example)
        eventsAdapter = new EventAdapter(events);
        rcvEvents.setAdapter(eventsAdapter);

        rcvEvents.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(MainActivity.this, EventActivity.class);
                intent.putExtra("EVENT_CLICK", events.get(position));
                startActivity(intent);
            }
        }));


    }
}
