package com.example.utilisateur.samva;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class StatePagerAdapterFragment extends FragmentStatePagerAdapter {

    int nb;

    public StatePagerAdapterFragment(FragmentManager fm, int nb) {
        super(fm);
        this.nb = nb;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0 :
                MapsFragment mapsFragment = new MapsFragment();
                return mapsFragment;
            case 1 :
                MapsFragment mapsFragment1 = new MapsFragment();
                return mapsFragment1;
            case 2 :
                MapsFragment mapsFragment2 = new MapsFragment();
                return mapsFragment2;
            default :
                return null;
        }

    }

    @Override
    public int getCount() {
        return this.nb;
    }
}
