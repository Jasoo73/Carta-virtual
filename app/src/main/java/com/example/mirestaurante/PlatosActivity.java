package com.example.mirestaurante;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.res.TypedArray;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class PlatosActivity extends AppCompatActivity {

    private MyAdapterPF myAdapter = null;
    private static ArrayList<Producto> l_categorias = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_platos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setData();

        myAdapter = new MyAdapterPF(this);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(myAdapter);
    }
    public void setData( ) {

        l_categorias.clear();

        // Obtener las categorías de los platos fuertes desde el archivo de recursos
        String[] platosNombres = getResources().getStringArray(R.array.fuertes);
        String[] platosDescripciones = getResources().getStringArray(R.array.fuertesDescripciones);

        TypedArray platosImagenes = getResources().obtainTypedArray(R.array.fuertesImagenes);

        // Iterar sobre las categorías y crear los objetos Producto
        for (int i = 0; i < platosNombres.length; i++) {
            Producto producto = new Producto(
                    platosNombres[i],  // Título
                    platosDescripciones[i],  // Descripción
                    platosImagenes.getResourceId(i, -1)  // Obtenemos la imagen usando el índice
            );
            l_categorias.add(producto);
        }
    }

    public static class MyAdapterPF extends BaseAdapter {

        private Context mContext;
        public MyAdapterPF(Context c) {
            mContext = c;
        }

        @Override
        public int getCount() {

            return l_categorias.size();
        }

        @Override
        public Object getItem(int position) {

            return l_categorias.get(position);
        }

        @Override
        public long getItemId(int i) {

            return 0;
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;

            if (convertView == null) {
                // Make up a new view
                LayoutInflater inflater = (LayoutInflater) mContext
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.layout_items, null);
            } else {
                // Use convertView if it is available
                view = convertView;
            }

            // Example to get an image resource
            ImageView img = (ImageView) view.findViewById(R.id.image);
            img.setImageDrawable(mContext.getResources().getDrawable(l_categorias.get(position).image));
            TextView tTitle = (TextView) view.findViewById(R.id.title);
            tTitle.setText(l_categorias.get(position).titulo);

            TextView Tdescription = (TextView) view.findViewById(R.id.description);
            Tdescription.setText(l_categorias.get(position).descripcion);

            return view;

        }
    }
}