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
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    private MyActivityCallback activity;

    private Event event;

    private TextView title;
    private TextView date;
    private TextView placename;
    private TextView address;
    private ImageView image;
    private FloatingActionButton addButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.event_details, container, false);
        title = rootview.findViewById(R.id.title);
        date = rootview.findViewById(R.id.date);
        placename = rootview.findViewById(R.id.placename);
        address = rootview.findViewById(R.id.address);
        addButton = rootview.findViewById(R.id.addbutton);

        Bundle bundle = getArguments();
        event = (Event) bundle.getSerializable("EVENT");

        title.setText(event.getTitle());
        date.setText(event.getTimetable());
        placename.setText(event.getPlacename());
        address.setText(event.getAddress());
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
