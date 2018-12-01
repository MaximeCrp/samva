package com.example.utilisateur.samva;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class StatePagerAdapterFragment extends FragmentStatePagerAdapter {

    int nb;
    ArrayList<Event> events;

    public StatePagerAdapterFragment(FragmentManager fm, int nb, ArrayList<Event> events) {
        super(fm);
        this.nb = nb;
        this.events = events;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0 :
                ListFragment listFragment = new ListFragment().newInstance(this.events);
                return listFragment;
            case 1 :
                MapsFragment mapsFragment = new MapsFragment().newInstance(this.events);
                return mapsFragment;
            case 2 :
                ListFragment listFragment2 = new ListFragment().newInstance(this.events);
                return listFragment2;


            default :
                return null;
        }

    }

    @Override
    public int getCount() {
        return this.nb;
    }
}