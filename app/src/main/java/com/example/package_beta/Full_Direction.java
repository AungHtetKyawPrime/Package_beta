package com.example.package_beta;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.package_beta.DetailTab.PkMapHelper;
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

public class Full_Direction extends AppCompatActivity {
    MapView mapView;
    public MapboxMap mapboxMap1;
    PermissionsManager permissionsManager;
    LocationComponent locationComponent;
    PkMapHelper pkMapHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(getApplicationContext(),getResources().getString(R.string.access_token));
        setContentView(R.layout.activity_full__direction);
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        pkMapHelper = new PkMapHelper(this,this,mapView);
    }
    @SuppressWarnings("MissingPermission")
    @Override
    protected void onStart() {
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
                            if(PermissionsManager.areLocationPermissionsGranted(Full_Direction.this))
                            {
                                locationComponent = pkMapHelper.setUpLocatinComponentDetail(style,locationComponent,false);

                                LatLng latLng = new LatLng();
                                latLng.setLatitude(17.322071);
                                latLng.setLongitude(96.466331);
                                String place_name="bago";
                                pkMapHelper.markerSetUp(mapboxMap1,latLng,place_name);
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
                                            latLng.setLatitude(17.322071);
                                            latLng.setLongitude(96.466331);
                                            String place_name="bago";
                                            pkMapHelper.markerSetUp(mapboxMap1,latLng,place_name);
                                            pkMapHelper.setUpCameraPostionOption(mapboxMap1,latLng);
                                        }
                                        else
                                        {
                                            Toast.makeText(Full_Direction.this,"Permission Cancel",Toast.LENGTH_SHORT).show();


                                        }
                                    }
                                });
                                permissionsManager.requestLocationPermissions(Full_Direction.this);



                            }
                        }
                    }
                });

            }
        });
        mapView.onStart();


    }
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionsManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
