package com.example.mirestaurante;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void consultarSedes(View v) {
        Intent i = new Intent(this, SedesActivity.class);
        startActivity(i);
    }

    public void verMenu(View v) {
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }

    public void verPromociones(View v) {
        Intent i = new Intent(this, PromocionesActivity.class);
        startActivity(i);
    }

    public void Reservar(View v) {
        //Intent i = new Intent(this,MenuActivity.class);
        //startActivity(i);

        String numeroTelefono = "+573023213763";
        String mensajePredeterminado = "Hola, gracias por comunicarse con nosotros. En breve, uno de nuestros asesores atenderá su solicitud. ¡Quedamos atentos!";

        PackageManager pm = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VIEW);

        // Crear la URI con el número de teléfono y el mensaje
        Uri uri = Uri.parse("whatsapp://send?phone=" + numeroTelefono + "&text=" + Uri.encode(mensajePredeterminado));

        intent.setData(uri);

        if (isAppInstalled("com.whatsapp", pm)) {
            startActivity(intent);
        } else {
            // Mostrar mensaje si WhatsApp no está instalado
            android.widget.Toast.makeText(this, "WhatsApp no está instalado en este dispositivo", android.widget.Toast.LENGTH_LONG).show();
        }
    }

    private boolean isAppInstalled(String packageName, PackageManager pm) {
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}