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

    public void verMenu (View v){
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }

    public void Reservar (View v) {
        //Intent i = new Intent(this,MenuActivity.class);
        //startActivity(i);

        String numeroTelefono = "+573023213763";
        String mensajePredeterminado = "Hola, muchas gracias por comunicarse con nosotros en unos segundo te atendemos";

        Uri uri = Uri.parse("whatsapp://send?phone=" + numeroTelefono + "&text=" + Uri.encode(mensajePredeterminado));

        //Aqui podemos conectarnos a un proveedor en este caso, llama el paquete de Whatsapp
        PackageManager pm = getPackageManager();
        Intent i = new Intent(Intent.ACTION_VIEW);
        //Intent i = pm.getLaunchIntentForPackage("com.whatsapp");
        i.setData(uri);
        startActivity(i);
    }
}