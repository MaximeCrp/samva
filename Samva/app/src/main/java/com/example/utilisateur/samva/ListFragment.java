package com.example.utilisateur.samva;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class ListFragment extends Fragment {

    private MainActivityCallback activity;

    private RecyclerView rcvEvents;
    private EventAdapter eventsAdapter;

    private ArrayList<Event> eventList = new ArrayList<Event>();

    private static final String ARG_EVENTS ="EVENTS";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.list_fragment, container, false);

        rcvEvents = rootview.findViewById(R.id.a_main_rcv_events);

        Serializable ser = getArguments().getSerializable(ARG_EVENTS);
        eventList = (ArrayList<Event>) ser;


        Context context = getContext();
        Toast toast = Toast.makeText(context, "Taille de la liste reçue : " + eventList.size(), Toast.LENGTH_LONG);
        toast.show();

        // use a linear layout manager
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
        if (context instanceof MainActivityCallback) {
            activity = (MainActivityCallback) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    public static ListFragment newInstance(ArrayList<Event> events) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_EVENTS, events);
        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
