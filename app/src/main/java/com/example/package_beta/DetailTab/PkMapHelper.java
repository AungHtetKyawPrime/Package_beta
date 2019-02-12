package com.example.package_beta.DetailTab;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.widget.Toast;


import com.example.package_beta.R;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.directions.DirectionsCriteria;
import com.mapbox.directions.MapboxDirections;
import com.mapbox.directions.service.models.DirectionsResponse;
import com.mapbox.directions.service.models.Waypoint;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;

import java.io.IOException;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class PkMapHelper {

    private Context context;
    private LocationComponent locationComponent;
    private MapboxMap mapboxMap1;
    private PermissionsManager permissionsManager;
    public static final int  DETAIL_REQUEST_CODE = 1000;
    private Activity activity;
    private MapView mapView;
    private Location originLocation;

    public PkMapHelper(Context context, MapView mapView)
    {
        this.context = context;
        //this. activity = activity;
        this. mapView = mapView;
    }

    public void setMapboxMap1(MapboxMap mapboxMap1) { this.mapboxMap1 = mapboxMap1;}

    public MapboxMap getMapboxMap1() {return mapboxMap1;}

    @SuppressWarnings("MissingPermission")
    public LocationComponent setUpLocationComponent(LocationComponent locationComponent1, Style style, boolean myPosition)
    {
        locationComponent1.activateLocationComponent(context, style);
        locationComponent1.setLocationComponentEnabled(true);
        if(myPosition) {
            locationComponent1.setCameraMode(CameraMode.TRACKING);
        }
        else
        {
            locationComponent1.setCameraMode(CameraMode.NONE_GPS);
        }
        return locationComponent1;

    }

    @SuppressWarnings("MissingPermission")
    public void setUpCameraPostionOption(MapboxMap mapboxMap, LatLng latLng)
    {
        CameraPosition.Builder cameraPosition_buider = new CameraPosition.Builder();
        cameraPosition_buider.zoom(16);
        cameraPosition_buider.target(latLng);
        cameraPosition_buider.tilt(20);
        mapboxMap.setCameraPosition(cameraPosition_buider.build());

    }

    public void setUpRoute(Location fromPlace, LatLng toPlace) throws IOException {

        Waypoint origin = new Waypoint(fromPlace.getLongitude(),fromPlace.getLatitude());
        Waypoint destination = new Waypoint(toPlace.getLongitude(),toPlace.getLatitude());

        MapboxDirections mapboxDirections = new MapboxDirections.Builder()
                .setOrigin(origin)
                .setDestination(destination)
                .setProfile(DirectionsCriteria.PROFILE_DRIVING)
                .setAccessToken(context.getResources().getString(R.string.mapbox_token))
                .build();

        mapboxDirections.enqueue(new Callback<DirectionsResponse>() {
            @Override
            public void onResponse(Response<DirectionsResponse> response, Retrofit retrofit) {
                // Toast.makeText(MainActivity.this,"Responnse"+response.body().getRoutes().get(0).getDistance(),Toast.LENGTH_SHORT).show();

                List<Waypoint> waypoints = response.body().getRoutes().get(0).getGeometry().getWaypoints();
                LatLng[] points = new LatLng[waypoints.size()];

                for (int i = 0; i < waypoints.size(); i++) {
                    points[i] = new LatLng(
                            waypoints.get(i).getLatitude(),
                            waypoints.get(i).getLongitude());
                }

                getMapboxMap1().addPolyline(new PolylineOptions()
                        .add(points)
                        .color(Color.parseColor("#F566AE"))
                        .width(4));

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(context,"Respoonse error",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void markerSetUp(MapboxMap m,LatLng latLng)
    {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("Bago").setPosition(latLng);
        m.addMarker(markerOptions);
    }

    public LocationComponent setUpLocatinComponentDetail (Style style,LocationComponent locationComponentperm,boolean myposition)
    {
        LocationComponent locationComponent1 = setUpLocationComponent(locationComponentperm,style,myposition);
        return locationComponent1;
    }




}
