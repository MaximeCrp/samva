package com.example.utilisateur.samva;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import java.util.List;

public class TravelAdapter extends RecyclerView.Adapter<TravelViewHolder> {

    private List<Travel> travels;

    // Provide a suitable constructor (depends on the kind of dataset)
    public TravelAdapter(List<Travel> travels) {
        this.travels = travels;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public TravelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sam, parent, false);
        return new TravelViewHolder(rootView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull TravelViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Travel travelToDisplay = this.travels.get(position);

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String strDate = dateFormat.format(travelToDisplay.getDepartTime());
        holder.sam_name.setText(travelToDisplay.getSam());
        holder.depart_place.setText(travelToDisplay.getDepartPlace());
        holder.depart_time.setText(strDate);
        holder.nb_places.setText(Integer.toString(travelToDisplay.getNbPlaces()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.travels.size();
    }

}