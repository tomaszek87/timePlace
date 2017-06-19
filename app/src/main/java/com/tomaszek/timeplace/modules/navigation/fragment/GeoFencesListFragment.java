package com.tomaszek.timeplace.modules.navigation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tomaszek.timeplace.R;
import com.tomaszek.timeplace.database.DatabaseHandler;
import com.tomaszek.timeplace.modules.navigation.adapter.GeofenceAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GeoFencesListFragment extends Fragment {

    @BindView(R.id.geofences_list)
    RecyclerView geofencesRecyclerView;

    public static GeoFencesListFragment newInstance() {
        GeoFencesListFragment fragment = new GeoFencesListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.geofences_list_fragment, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        GeofenceAdapter adapter = new GeofenceAdapter(getContext(), DatabaseHandler.getInstance(getContext()).getGeofences());
        geofencesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        geofencesRecyclerView.setAdapter(adapter);
    }
}