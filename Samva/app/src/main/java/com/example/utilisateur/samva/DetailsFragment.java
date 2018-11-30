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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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

        Bundle bundle = getArguments();
        event = (Event) bundle.getSerializable("EVENT");
        travels = (TravelList) bundle.getSerializable("TRAVEL_LIST");


        title.setText(event.getTitle());
        date.setText(event.getTimetable());
        placename.setText(event.getPlacename());
        address.setText(event.getAddress());

        // use a linear layout manager
        Context context = getContext();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rcvTravels.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        travelsAdapter = new TravelAdapter(travels.getList());
        rcvTravels.setAdapter(travelsAdapter);

        rcvTravels.addOnItemTouchListener(new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                //lancer la page détails du trajet et ajout d'une personne
                // activity.details(eventList.get(position));
                Context context = getContext();
                Toast toast = Toast.makeText(context, "trajet ajouté : "+ travels.getList().get(position).getSam() + " - " +  travels.getList().get(position).getNbPlaces(), Toast.LENGTH_LONG);
                toast.show();
            }
        }));

        /*
        Context context = getContext();
        Picasso.with(context).load(event.getImage()).into(image);*/
/*
        try {
            URL url = new URL(event.getImage());
            Bitmap bmimage = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            image.setImageBitmap(bmimage);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.newTravel();
            }
        });


        return rootview;
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
