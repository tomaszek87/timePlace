package com.tomaszek.timeplace.modules.navigation.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.tomaszek.timeplace.R;
import com.tomaszek.timeplace.common.model.GeofenceModel;
import com.tomaszek.timeplace.database.DatabaseHandler;
import com.tomaszek.timeplace.modules.navigation.activity.NavigationActivity;
import com.tomaszek.timeplace.modules.navigation.adapter.SampleFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GeoFencesListWithMapFragment extends Fragment implements OnMapReadyCallback {

    @BindView(R.id.pager)
    ViewPager viewPager;


    private GoogleMap map;
    private NavigationActivity activity;
    View view;
    private Context mContext;
    private SupportMapFragment supportMapFragment;
    private MarkerOptions currentPositionMarker = null;
    private Marker currentLocationMarker;
    private FusedLocationProviderClient mFusedLocationClient;
    private String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
    private static int PERMISSION_CODE = 1;
    private float zoomLevel = Float.valueOf("16.0");


    public static GeoFencesListWithMapFragment newInstance(NavigationActivity activity) {
        GeoFencesListWithMapFragment fragment = new GeoFencesListWithMapFragment();
        fragment.activity = activity;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.geofences_list_map_fragment, container, false);
        ButterKnife.bind(this, view);
        final SampleFragmentPagerAdapter adapter = new SampleFragmentPagerAdapter(getChildFragmentManager(),
                activity, prepareFragmentsForPager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setPadding(250, 0, 250, 0);
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(50);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                PointFragment selectedFragment = (PointFragment) adapter.getItem(position);
                LatLng latLng = new LatLng(selectedFragment.getGeofenceModel().getLatitude(), selectedFragment.getGeofenceModel().getLongitude());
                map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

    private List<Fragment> prepareFragmentsForPager() {
        List<Fragment> fragments = new ArrayList<>();

        for (GeofenceModel model : DatabaseHandler.getInstance(getContext()).getGeofences()) {
            fragments.add(PointFragment.newInstance(model));
        }
        ;
        return fragments;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity();

        FragmentManager fm = getActivity().getSupportFragmentManager();
        supportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map_container);
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map_container, supportMapFragment).commit();
        }
        supportMapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        //map.setMyLocationEnabled(true);
        map.animateCamera(CameraUpdateFactory.zoomTo(15));
        /*map.setOnMapLongClickListener(MapyFragment.this);
        map.setOnMapClickListener(MapFragment.this);*/
        getLocation();
        for (GeofenceModel model : DatabaseHandler.getInstance(getContext()).getGeofences()) {
            createMarker(model.getLatitude(), model.getLongitude());
        }
        ;
    }

    private void createMarker(Double lat, Double lang) {
        LatLng latLng = new LatLng(lat, lang);
        currentPositionMarker = new MarkerOptions();

        currentPositionMarker.position(latLng)
                .title("My Location")
        ;
        currentLocationMarker = map.addMarker(currentPositionMarker);
    }

    public void updateCurrentLocationMarker(Location currentLatLng) {

        if (map != null) {

            LatLng latLng = new LatLng(currentLatLng.getLatitude(), currentLatLng.getLongitude());
            if (currentPositionMarker == null) {
                currentPositionMarker = new MarkerOptions();

                currentPositionMarker.position(latLng)
                        .title("My Location")
                ;
                currentLocationMarker = map.addMarker(currentPositionMarker);
            }

            if (currentLocationMarker != null)
                currentLocationMarker.setPosition(latLng);

            ///currentPositionMarker.position(latLng);
            map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    private void getLocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS, PERMISSION_CODE);
            return;
        } else {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                LatLng positiion = new LatLng(location.getLatitude(), location.getLongitude());
//                                googleMap.addMarker(new MarkerOptions().position(positiion)
//                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
//                                        .title("Marker in actual position").draggable(true));
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(positiion, zoomLevel));
                            }
                        }
                    });
        }
    }

}