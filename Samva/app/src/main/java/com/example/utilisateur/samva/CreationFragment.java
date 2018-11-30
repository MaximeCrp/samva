package com.example.utilisateur.samva;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreationFragment extends Fragment {

    private MyActivityCallback activity;
    private TextView title;
    private EditText sam;
    private EditText departPlace;
    private EditText departTime;
    private EditText returnPlace;
    private EditText returnTime;
    private EditText nbPlaces;

    private Button addButton;

    private Travel travel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.creation_fragment, container, false);

        title = rootview.findViewById(R.id.title);
        sam = rootview.findViewById(R.id.sam_name);
        departPlace = rootview.findViewById(R.id.depart_place);
        departTime = rootview.findViewById(R.id.depart_time);
        returnPlace = rootview.findViewById(R.id.return_place);
        returnTime = rootview.findViewById(R.id.return_time);
        nbPlaces = rootview.findViewById(R.id.nb_places);
        addButton = rootview.findViewById(R.id.button);

        Bundle bundle = getArguments();
        travel = (Travel) bundle.getSerializable("TRAVEL");

        title.setText(travel.getTitle());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                travel.setSam(sam.getText().toString());
                travel.setDepartPlace(departPlace.getText().toString());
                travel.setReturnPlace(returnPlace.getText().toString());
                travel.setNbPlaces(Integer.parseInt(nbPlaces.getText().toString()));

                String date = departTime.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Date date1 = null;
                try {
                    date1 = sdf.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Context context = getContext();
                Toast toast = Toast.makeText(context, "heure de départ : " + date1.toString(), Toast.LENGTH_LONG);
                toast.show();

                travel.setDepartTime(date1);

                date = returnTime.getText().toString();
                date1 = null;
                try {
                    date1 = sdf.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                travel.setReturnTime(date1);

                activity.addTravel(travel);
            }
        });


        return rootview;

    }

    public void notifyDataChanged() {

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
