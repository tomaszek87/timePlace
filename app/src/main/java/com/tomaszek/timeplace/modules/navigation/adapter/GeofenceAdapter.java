package com.tomaszek.timeplace.modules.navigation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tomaszek.timeplace.R;
import com.tomaszek.timeplace.common.model.GeofenceModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GeofenceAdapter extends RecyclerView.Adapter<GeofenceAdapter.ViewHolder> {

    private List<GeofenceModel> data;
    private Context context;
    protected LatLng mMapLocation;


    public GeofenceAdapter(Context context, List<GeofenceModel> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.geofence_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final GeofenceModel model = data.get(position);
        holder.mapView.onCreate(null);
        holder.mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                holder.mGoogleMap = googleMap;
                MapsInitializer.initialize(context);
                googleMap.getUiSettings().setMapToolbarEnabled(true);
                setMapLocation(model.getLatitude(), model.getLongitude(), googleMap);
            }
        });
    }

    public void setMapLocation(double lat, double lon, GoogleMap googleMap) {
        mMapLocation = new LatLng(lat, lon);
        updateMapContents(googleMap);
    }

    protected void updateMapContents(GoogleMap googleMap) {
        googleMap.clear();
        // Update the mapView feature data and camera position.
        googleMap.addMarker(new MarkerOptions().position(mMapLocation));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mMapLocation, 16f);
        googleMap.moveCamera(cameraUpdate);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.mapLite)
        public MapView mapView;

        protected GoogleMap mGoogleMap;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
} 