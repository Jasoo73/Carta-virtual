package com.example.mirestaurante;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.example.mirestaurante.databinding.ActivitySedesBinding;

public class SedesActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private ActivitySedesBinding binding;
    private BitmapDescriptor resizeMapIcon(int width, int height) {
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cerveza);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return BitmapDescriptorFactory.fromBitmap(resizedBitmap);
    }

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
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(this));

        //Declarar ubicación geografica de las sedes

        LatLng aranjuez = new LatLng(6.285068, -75.555971);
        LatLng calle70 = new LatLng(6.247120, -75.589154);
        LatLng bello = new LatLng(6.334999, -75.557858);
        LatLng envigado = new LatLng(6.1759, -75.5917); // Envigado
        LatLng poblado = new LatLng(6.2095, -75.5670); // El Poblado
        LatLng sabaneta = new LatLng(6.1515, -75.6166); // Sabaneta
        LatLng estrella = new LatLng(6.1576, -75.6430); // La Estrella
        LatLng berrio = new LatLng(6.2518, -75.5636); // Parque Berrío
        LatLng palmas = new LatLng(6.2170, -75.5562); // Las Palmas
        LatLng llanoGrande = new LatLng(6.1360, -75.3879); // Llanogrande
        LatLng buenosAires = new LatLng(6.2476, -75.5538); // Buenos Aires
        LatLng robledo = new LatLng(6.2758, -75.5905); // Robledo
        LatLng villaHermosa = new LatLng(6.2560, -75.5581); // Villa Hermosa


        // Aplicar estilo personalizado desde res/raw/map_style.json
        try {
            boolean success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style));

            if (!success) {
                Log.e("MAPA", "Error al aplicar el estilo.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("MAPA", "No se pudo encontrar el estilo.", e);
        }

        // Añadir maracadores

        //Marcador en Aranjuez

        // Agregar marcadores al mapa con el mismo ícono personalizado
        mMap.addMarker(new MarkerOptions()
                .position(envigado)
                .title("Taberna en Envigado")
                .snippet("Dirección: Calle X # Y - Z\nHorario: 6PM - 2AM")
                .icon(resizeMapIcon(100, 100)));

        mMap.addMarker(new MarkerOptions()
                .position(poblado)
                .title("Taberna en El Poblado")
                .snippet("Dirección: Calle X # Y - Z\nHorario: 6PM - 2AM")
                .icon(resizeMapIcon(100, 100)));

        mMap.addMarker(new MarkerOptions()
                .position(sabaneta)
                .title("Taberna en Sabaneta")
                .snippet("Dirección: Calle X # Y - Z\nHorario: 6PM - 2AM")
                .icon(resizeMapIcon(100, 100)));

        mMap.addMarker(new MarkerOptions()
                .position(estrella)
                .title("Taberna en La Estrella")
                .snippet("Dirección: Calle X # Y - Z\nHorario: 6PM - 2AM")
                .icon(resizeMapIcon(100, 100)));

        mMap.addMarker(new MarkerOptions()
                .position(berrio)
                .title("Taberna en Parque Berrío")
                .snippet("Dirección: Calle X # Y - Z\nHorario: 6PM - 2AM")
                .icon(resizeMapIcon(100, 100)));

        mMap.addMarker(new MarkerOptions()
                .position(palmas)
                .title("Taberna en Las Palmas")
                .snippet("Dirección: Calle X # Y - Z\nHorario: 6PM - 2AM")
                .icon(resizeMapIcon(100, 100)));

        mMap.addMarker(new MarkerOptions()
                .position(llanoGrande)
                .title("Taberna en Llanogrande")
                .snippet("Dirección: Calle X # Y - Z\nHorario: 6PM - 2AM")
                .icon(resizeMapIcon(100, 100)));

        mMap.addMarker(new MarkerOptions()
                .position(buenosAires)
                .title("Taberna en Buenos Aires")
                .snippet("Dirección: Calle X # Y - Z\nHorario: 6PM - 2AM")
                .icon(resizeMapIcon(100, 100)));

        mMap.addMarker(new MarkerOptions()
                .position(robledo)
                .title("Taberna en Robledo")
                .snippet("Dirección: Calle X # Y - Z\nHorario: 6PM - 2AM")
                .icon(resizeMapIcon(100, 100)));

        mMap.addMarker(new MarkerOptions()
                .position(villaHermosa)
                .title("Taberna en Villa Hermosa")
                .snippet("Dirección: Calle X # Y - Z\nHorario: 6PM - 2AM")
                .icon(resizeMapIcon(100, 100)));

        mMap.addMarker(new MarkerOptions()
                .position(calle70)
                .title("Taberna en Buenos Aires")
                .snippet("Dirección: Calle X # Y - Z\nHorario: 6PM - 2AM")
                .icon(resizeMapIcon(100, 100)));

        mMap.addMarker(new MarkerOptions()
                .position(aranjuez)
                .title("Taberna en Robledo")
                .snippet("Dirección: Calle X # Y - Z\nHorario: 6PM - 2AM")
                .icon(resizeMapIcon(100, 100)));

        mMap.addMarker(new MarkerOptions()
                .position(bello)
                .title("Taberna en Villa Hermosa")
                .snippet("Dirección: Calle X # Y - Z\nHorario: 6PM - 2AM")
                .icon(resizeMapIcon(100, 100)));



        // Ajustar la cámara para mostrar todas las ubicaciones
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(envigado);
        builder.include(poblado);
        builder.include(sabaneta);
        builder.include(estrella);
        builder.include(berrio);
        builder.include(palmas);
        builder.include(llanoGrande);
        builder.include(buenosAires);
        builder.include(robledo);
        builder.include(villaHermosa);
        builder.include(calle70);
        builder.include(aranjuez);
        builder.include(bello);

        LatLngBounds bounds = builder.build();
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));

        // Zoom mínimo
        mMap.setMinZoomPreference(12f);
    }
}