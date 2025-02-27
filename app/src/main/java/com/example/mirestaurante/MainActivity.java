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


    MainActivityBinding binding;
    ListAdapter listAdapter;
    ArrayList<DataLista> dataArrayList = new ArrayList<>();
        DataLista listData;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            int[] imageList = {R.drawable.carne, R.drawable.estofado, R.drawable.sopa, R.drawable.sushi};
            int[] ingredientList = {R.string.pastaIngredients};
            int[] descList = {R.string.pastaDesc};
            String[] nameList = {"Carne", "Estofado", "Sopa", "Sushi"};
            String[] timeList = {"30 mins", "2 mins", "45 mins","10 mins", "60 mins", "45 mins", "30 mins"};
            for (int i = 0; i < imageList.length; i++){
                listData = new ListData(nameList[i], timeList[i], ingredientList[i], descList[i], imageList[i]);
                dataArrayList.add(listData);
            }
            listAdapter = new ListAdapter(MainActivity.this, dataArrayList);
            binding.listview.setAdapter(listAdapter);
            binding.listview.setClickable(true);
            binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
                    intent.putExtra("name", nameList[i]);
                    intent.putExtra("time", timeList[i]);
                    intent.putExtra("ingredients", ingredientList[i]);
                    intent.putExtra("desc", descList[i]);
                    intent.putExtra("image", imageList[i]);
                    startActivity(intent);
                }
            });
        }
}