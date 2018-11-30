package com.example.utilisateur.samva;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TravelFragment extends Fragment {

    private MyActivityCallback activity;

    private TextView title;
    private TextView sam;
    private TextView passengers_names;
    private TextView depart_place;
    private TextView return_place;
    private TextView return_time;
    private TextView depart_time;
    private TextView nbplaces;
    private EditText yourname;
    private Button addButton;
    private ImageView logo_sam;

    private Context context;
    private Toast toast;
    private Travel travel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_travel, container, false);
        title = rootview.findViewById(R.id.title);
        sam = rootview.findViewById(R.id.sam);
        logo_sam = rootview.findViewById(R.id.logo_sam);
        passengers_names = rootview.findViewById(R.id.passengers_names);
        nbplaces = rootview.findViewById(R.id.nb_places);
        depart_place = rootview.findViewById(R.id.depart_place);
        depart_time = rootview.findViewById(R.id.depart_time);
        return_place = rootview.findViewById(R.id.return_place);
        return_time = rootview.findViewById(R.id.return_time);
        yourname = rootview.findViewById(R.id.your_name);
        addButton = rootview.findViewById(R.id.add);

        Bundle bundle = getArguments();
        travel = (Travel) bundle.getSerializable("TRAVEL");

        context = getContext();
        /*toast = Toast.makeText(context, "passagers reçus : "+ travel.getPassengers().size(), Toast.LENGTH_LONG);
        toast.show();*/

        title.setText(travel.getTitle());
        sam.setText(travel.getSam());
        depart_place.setText(travel.getDepartPlace());
        nbplaces.setText(Integer.toString(travel.getNbPlaces()));

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String strDate = dateFormat.format(travel.getDepartTime());
        depart_time.setText(strDate);
        return_place.setText(travel.getReturnPlace());
        strDate = dateFormat.format(travel.getDepartTime());
        return_time.setText(strDate);
        String passengers = "";

        if(travel.getPassengers() != null) {
            for (String str : travel.getPassengers()) {
                passengers += str + "\n";
            }
        }

        passengers_names.setText(passengers);

        Picasso.with(context).load("http://www.asre44.com/wp-content/uploads/2014/06/sam250.jpg").into(logo_sam);




        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(travel.getNbPlaces()> 0) {
                    travel.addPassenger(yourname.getText().toString());
                    activity.passengerAdded(travel);
                }
                else {
                    toast = Toast.makeText(context, "Ce trajet est plein", Toast.LENGTH_LONG);
                    toast.show();
                }
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
