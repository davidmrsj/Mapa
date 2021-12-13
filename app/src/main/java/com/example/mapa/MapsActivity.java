package com.example.mapa;

import androidx.fragment.app.FragmentActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mapa.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    Marker marker;
    Marker marker1;
    Marker marker2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng julianMarias = new LatLng(41.63230050670098, -4.758513892601716);
        LatLng galileo = new LatLng(41.64719165350418, -4.702616445972433);
        LatLng ribera = new LatLng(41.66482946430514, -4.723221638573773);
        marker = mMap.addMarker(new MarkerOptions().position(julianMarias).title("Julian Marias").draggable(true));
        marker1 = mMap.addMarker(new MarkerOptions().position(galileo).title("Galileo").draggable(true));
        marker2 = mMap.addMarker(new MarkerOptions().position(ribera).title("Ribera").draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(julianMarias));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setOnMarkerClickListener(this);

        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker));
        marker1.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        mMap.getUiSettings().setZoomControlsEnabled(true);

        CircleOptions circulo = new CircleOptions().center(julianMarias).radius(400);
        Circle circle = mMap.addCircle(circulo);
        circle.setStrokeColor(Color.RED);
        circle.setFillColor(Color.parseColor("#33FF0000"));

        PolylineOptions lineas = new PolylineOptions().add(julianMarias).add(ribera).add(galileo);
        Polyline line = mMap.addPolyline(lineas);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(this, "Has hecho click en: "+marker.getTitle(), Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {
        Log.d("Marker","Marker " + marker.getId() + " Drag@" + marker.getPosition().latitude+", "+marker.getPosition().longitude);
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }
}