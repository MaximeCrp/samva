package com.example.utilisateur.samva;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TravelViewHolder extends RecyclerView.ViewHolder {

    // each data item is just a string in this case
    public TextView sam_name;
    public TextView depart_place;
    public TextView depart_time;
    public TextView nb_places;


    public TravelViewHolder(View rootView) {
        super(rootView);
        this.sam_name = rootView.findViewById(R.id.sam_name);
        this.depart_place = rootView.findViewById(R.id.depart_place);
        this.depart_time = rootView.findViewById(R.id.depart_time);
        this.nb_places = rootView.findViewById(R.id.nb_places);
    }
}
