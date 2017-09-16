package com.tomaszek.timeplace.modules.navigation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tomaszek.timeplace.R;
import com.tomaszek.timeplace.common.model.GeofenceModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PointFragment extends Fragment {

    @BindView(R.id.item_label)
    TextView itemLabel;
    private GeofenceModel geofenceModel;

    public static PointFragment newInstance(GeofenceModel model) {
        PointFragment fragment = new PointFragment();
        fragment.geofenceModel = model;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.point_fragment, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        itemLabel.setText(geofenceModel.getGeofenceId());
    }

    public GeofenceModel getGeofenceModel() {
        return geofenceModel;
    }

    public void setGeofenceModel(GeofenceModel geofenceModel) {
        this.geofenceModel = geofenceModel;
    }
}