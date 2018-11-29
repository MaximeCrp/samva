package com.example.utilisateur.samva;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private List<Event> events;

    // Provide a suitable constructor (depends on the kind of dataset)
    public EventAdapter(List<Event> events) {
        this.events = events;
        }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.evenement, parent, false);
        return new EventViewHolder(rootView);
    }

    // Replace the contents of a view (invoked by the layout manager)
        @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Event eventToDisplay = this.events.get(position);

        //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        //String strDate = dateFormat.format(eventToDisplay.getTimetable());
        holder.txvName.setText(eventToDisplay.getTitle());
        holder.txvDate.setText(eventToDisplay.getTimetable());

        }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.events.size();
        }

}