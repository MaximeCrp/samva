package com.example.utilisateur.samva;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private MainActivityCallback activity;

    private RecyclerView rcvEvents;
    private EventAdapter eventsAdapter;

    private ArrayList<Event> eventList = new ArrayList<Event>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.event_details, container, false);

        rcvEvents = rootview.findViewById(R.id.a_main_rcv_events);
        rcvEvents.setHasFixedSize(true);
        // use a linear layout manager
        Context context = getContext();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rcvEvents.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        eventsAdapter = new EventAdapter(eventList);
        rcvEvents.setAdapter(eventsAdapter);

        rcvEvents.addOnItemTouchListener(new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                activity.details(eventList.get(position));
            }
        }));

        return rootview;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MyActivityCallback) {
            activity = (MainActivityCallback) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

}
