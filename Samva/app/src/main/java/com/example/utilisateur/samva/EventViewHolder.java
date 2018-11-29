package com.example.utilisateur.samva;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EventViewHolder extends RecyclerView.ViewHolder{

    // each data item is just a string in this case
    public TextView txvName;
    public EditText txvDate;

    public EventViewHolder(View rootView) {
        super(rootView);
        this.txvName = rootView.findViewById(R.id.name);
        this.txvDate = rootView.findViewById(R.id.date);
    }

}
