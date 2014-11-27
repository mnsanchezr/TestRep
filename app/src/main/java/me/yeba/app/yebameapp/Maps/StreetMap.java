package me.yeba.app.yebameapp.Maps;

import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import me.yeba.app.yebameapp.R;
import me.yeba.app.yebameapp.yApp;

/**
 * Created by Javier on 20-11-14.
 */
public class StreetMap {
    private MapFragment _mapFragment;
    private GoogleMap _map;
    private List<Marker> Markers;

    public StreetMap(MapFragment mapFragment) {
        _mapFragment = mapFragment;
        _map = mapFragment.getMap();
        _map.setMyLocationEnabled(true);
        Markers = new LinkedList<Marker>();

        UiSettings mapUi = _map.getUiSettings();
        mapUi.setZoomControlsEnabled(true);
        mapUi.setCompassEnabled(true);
        mapUi.setMyLocationButtonEnabled(true);
        goToLocation(new LatLng(-33.346776, -70.542908));
        setZoom(16f);

    }
    public void setZoom(float zoom) {
        _map.animateCamera(CameraUpdateFactory.zoomTo(zoom));
    }
    public void goToLocation(LatLng location) {
        _map.animateCamera(CameraUpdateFactory.newLatLng(location));
    }
    public LatLng getMarkerLocation() {
        View mapView = _mapFragment.getView();
        return _map.getProjection().fromScreenLocation(new Point(mapView.getWidth() / 2, mapView.getHeight() / 2));
    }
    public LatLng getMyLocation() {
        Location myLocation = _map.getMyLocation();
        return new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
    }
    public String getMarkerAddress() {
        Geocoder geocoder = new Geocoder(_mapFragment.getView().getContext(), Locale.getDefault());
        LatLng myLocation = this.getMarkerLocation();
        try {
            List<Address> Addresses = geocoder.getFromLocation(myLocation.latitude, myLocation.longitude, 1);
            if(Addresses.size()>0) {
                return Addresses.get(0).getAddressLine(0);
            }
        } catch (IOException e) {
        }
        return null;
    }
    public void addCarMarker(LatLng position) {
        Marker marker = _map.addMarker(new MarkerOptions()
                .position(position)
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.icon_car_driver)));
        Markers.add(marker);
    }
    public void removeCarMarker(){
        _map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

            }
        });

    }
}
