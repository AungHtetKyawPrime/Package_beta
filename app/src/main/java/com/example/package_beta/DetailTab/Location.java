package com.example.package_beta.DetailTab;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.package_beta.DetailMain;
import com.example.package_beta.R;
import com.example.package_beta.Register.SignIn;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.util.List;

public class Location extends Fragment {
    public static MapView mapView;
    public  MapboxMap mapboxMap1;
    PermissionsManager permissionsManager;
    LocationComponent locationComponent;
    PkMapHelper pkMapHelper;
    TextView book_now,package_name,package_price;
    Button book_now_button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(getActivity(), getResources().getString(R.string.mapbox_token));

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
       mapView.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.location_tab, container,
                false);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.map_view);

        mapView.onCreate(savedInstanceState);
        book_now=(TextView)view.findViewById(R.id.book_now);
        book_now_button=(Button)view.findViewById(R.id.book_now_button);
        package_name=(TextView)view.findViewById(R.id.package_name);
        package_price=(TextView)view.findViewById(R.id.place_price);
        package_name.setText(DetailMain.place_name);
        package_price.setText(DetailMain.place_price);
        book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Ok Click","Success Intent to login");
                startActivity(new Intent(getContext(), SignIn.class));
            }
        });
        book_now_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SignIn.class));
            }
        });
        pkMapHelper = new PkMapHelper(getContext(),mapView);
        // Add a MapboxMap

    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMapp) {
                mapboxMap1 = mapboxMapp;
                pkMapHelper.setMapboxMap1(mapboxMap1);
                mapboxMap1.setStyle(Style.OUTDOORS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull final Style style) {

                        locationComponent = mapboxMap1.getLocationComponent();

                        if(PermissionsManager.areRuntimePermissionsRequired())
                        {
                            if(PermissionsManager.areLocationPermissionsGranted(getContext()))
                            {
                                locationComponent = pkMapHelper.setUpLocatinComponentDetail(style,locationComponent,false);

                                LatLng latLng = new LatLng();
                                latLng.setLatitude(17.33521);
                                latLng.setLongitude(96.48135);
                                pkMapHelper.markerSetUp(mapboxMap1,latLng);
                                pkMapHelper.setUpCameraPostionOption(mapboxMap1,latLng);




                            } else
                            {

                                permissionsManager = new PermissionsManager(new PermissionsListener() {
                                    @Override
                                    public void onExplanationNeeded(List<String> permissionsToExplain) {

                                        //Toast.makeText(package_location.this,permissionsToExplain.get(0).toString(),Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onPermissionResult(boolean granted) {

                                        if(granted)
                                        {
                                            //  pkMapHelper.setUpLocationComponent(locationComponent,style,false);

                                            locationComponent = pkMapHelper.setUpLocatinComponentDetail(style,locationComponent,false);
                                            @SuppressLint("MissingPermission") android.location.Location originLocation =  locationComponent.getLastKnownLocation();
                                            LatLng latLng = new LatLng();
                                            latLng.setLatitude(17.33521);
                                            latLng.setLongitude(96.48135);
                                            pkMapHelper.markerSetUp(mapboxMap1,latLng);
                                            pkMapHelper.setUpCameraPostionOption(mapboxMap1,latLng);
                                        }
                                        else
                                        {
                                            Toast.makeText(getContext(),"Permission Cancel",Toast.LENGTH_SHORT).show();


                                        }
                                    }
                                });
                                permissionsManager.requestLocationPermissions(getActivity());



                            }
                        }
                    }
                });

            }
        });
        mapView.onStart();
    }


}
