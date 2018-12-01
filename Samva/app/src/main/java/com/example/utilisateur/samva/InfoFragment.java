package com.example.utilisateur.samva;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class InfoFragment extends Fragment {

    TextView infoTitle, infoDescription, infoDataOrigin, infoDataShown, infoLibraries;

    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);
        infoTitle = rootView.findViewById(R.id.infoTitle);
        infoDescription = rootView.findViewById(R.id.infoDescription);
        infoDataOrigin = rootView.findViewById(R.id.infoDataOrigin);
        infoDataShown = rootView.findViewById(R.id.infoDataShown);
        infoLibraries = rootView.findViewById(R.id.infoLibraries);
        return rootView;

    }
}
