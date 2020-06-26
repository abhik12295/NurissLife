package com.demo.stuartabhi.nurisslife.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by stuartabhi on 6/1/2016.
 */
public class CustomMapFragment extends SupportMapFragment implements OnMapReadyCallback {

    private static final LatLng NurissLife = new LatLng(28.602012, 77.098750);
    private GoogleMap mMap;

    public CustomMapFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("MyMap", "onMapReady");
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(NurissLife).title("Nuriss LifeCare"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NurissLife, 15));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }
}

