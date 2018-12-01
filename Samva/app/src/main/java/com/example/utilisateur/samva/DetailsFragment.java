package com.example.utilisateur.samva;


import android.content.Context;
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

import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {

    private MyActivityCallback activity;

    private Event event;

    private TextView title;
    private TextView date;
    private TextView placename;
    private TextView address;
    private ImageView image;
    private FloatingActionButton addButton;

    private RecyclerView rcvTravels;
    private TravelAdapter travelsAdapter;

    private TravelList travels = new TravelList();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.event_details, container, false);
        title = rootview.findViewById(R.id.title);
        date = rootview.findViewById(R.id.date);
        placename = rootview.findViewById(R.id.placename);
        address = rootview.findViewById(R.id.address);
        addButton = rootview.findViewById(R.id.addbutton);
        rcvTravels = rootview.findViewById(R.id.a_main_rcv_sams);
        image = rootview.findViewById(R.id.image);

        Bundle bundle = getArguments();
        event = (Event) bundle.getSerializable("EVENT");
        travels = (TravelList) bundle.getSerializable("TRAVEL_LIST");

        Context context = getContext();

        title.setText(event.getTitle());
        String text="";
        for(int i=0 ; i<event.getDate().length ; i++) {
            text+= event.getDate()[i][0] + "\n" + event.getDate()[i][1] + "\n";
        }
        date.setText(text);
        placename.setText(event.getPlacename());
        address.setText(event.getAddress());
        Picasso.with(context).load("http://jackjack.fr/wp-content/uploads/2017/04/bandeau_programmation.jpg").into(image);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rcvTravels.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        travelsAdapter = new TravelAdapter(travels.getList());
        rcvTravels.setAdapter(travelsAdapter);

        rcvTravels.addOnItemTouchListener(new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //lancer la page détails du trajet et ajout d'une personne
                 activity.travelDetails(travels.getList().get(position), position);
            }
        }));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.newTravel();
            }
        });

        return rootview;
    }

    public void notifyDataChanged(Travel travel, int position) {
        travels.getList().set(position, travel);
        travelsAdapter.notifyDataSetChanged();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MyActivityCallback) {
            activity = (MyActivityCallback) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

}
