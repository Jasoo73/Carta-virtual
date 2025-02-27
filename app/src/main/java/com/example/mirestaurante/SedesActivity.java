package com.example.mirestaurante;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mirestaurante.databinding.ActivitySedesBinding;

public class SedesActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private ActivitySedesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivitySedesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtener el fragmento del mapa y establecer el callback
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add marker in brasil
        LatLng aranjuez = new LatLng(6.285068, -75.555971);
        mMap.addMarker(new MarkerOptions().position(aranjuez).title("Mago de OZ : Aranjuez"));

        // Add a marker in UPB
        LatLng upbMed = new LatLng(6.247120, -75.589154);
        mMap.addMarker(new MarkerOptions().position(upbMed).title("Kraken: Carrera 70"));

        // Add a marker in Medellin and move the camera
        LatLng medellin = new LatLng(6.334999, -75.557858);
        mMap.addMarker(new MarkerOptions().position(medellin).title("Dragon: Bello"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(medellin, 15f));
        //mMap.setMaxZoomPreference(5000f);
        mMap.setMinZoomPreference(13f);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        float zoomLevel = 12.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(medellin, zoomLevel));


        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }
}