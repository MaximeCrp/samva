package com.example.utilisateur.samva;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.maps.android.clustering.ClusterManager;

import java.io.Serializable;
import java.util.ArrayList;

public class MapsFragment extends Fragment implements GoogleMap.OnMarkerClickListener {

    View rootView;
    private GoogleMap gMap;
    Context context = getContext();
    private SupportMapFragment mSupportMapFragment;

    ArrayList<Event> eventList;
    private ClusterManager<Event> eventClusterManager;


    private static final String ARG_EVENTS ="EVENTS";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Serializable ser = getArguments().getSerializable(ARG_EVENTS);
            eventList = (ArrayList<Event>) ser ;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mSupportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mSupportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.map, mSupportMapFragment).commit();
        }
        if (mSupportMapFragment != null) {
            mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    if (googleMap != null) {

                        gMap = googleMap;
                        setUpClusterer("0,0");
                        eventClusterManager.cluster();
                    }
                }
            });
        }
        return rootView;
    }

    public static MapsFragment newInstance(ArrayList<Event> events) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_EVENTS, events);
        MapsFragment fragment = new MapsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void setUpClusterer(String camPos) {
        // Position the map.
        if(!camPos.equals("0,0")){
            String[] sepLatLng = camPos.split(",");
            LatLng camLatLng = new LatLng(Double.valueOf(sepLatLng[0]), Double.valueOf(sepLatLng[1]));
            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(camLatLng,10));
        }else{
            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(43.529742, 5.447427), 10));
        }


        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        eventClusterManager = new ClusterManager<Event>(getContext(), gMap);

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        gMap.setOnCameraIdleListener(eventClusterManager);
        gMap.setOnMarkerClickListener(eventClusterManager);
        gMap.setOnInfoWindowClickListener(eventClusterManager);
        // Add cluster items (markers) to the cluster manager.
        addItems();
    }

    private void addItems() {
        eventClusterManager.addItems(eventList);
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}