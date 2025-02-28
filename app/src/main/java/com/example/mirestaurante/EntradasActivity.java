package com.example.mirestaurante;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class EntradasActivity extends AppCompatActivity {

    private EntradasActivity.MyAdapterE myAdapter = null;
    private static ArrayList<Producto> l_categorias = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_entradas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setData();

        myAdapter = new EntradasActivity.MyAdapterE(this);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(myAdapter);
    }
    public void setData( ) {

        l_categorias.clear();

        // Obtener las categorías de los platos fuertes desde el archivo de recursos
        String[] entradasNombres = getResources().getStringArray(R.array.entradas);
        String[] entradasDescripciones = getResources().getStringArray(R.array.entradasDescripciones);

        TypedArray entradasImagenes = getResources().obtainTypedArray(R.array.entradasImagenes);

        // Iterar sobre las categorías y crear los objetos Producto
        for (int i = 0; i < entradasNombres.length; i++) {
            Producto producto = new Producto(
                    entradasNombres[i],  // Título
                    entradasDescripciones[i],  // Descripción
                    entradasImagenes.getResourceId(i, -1)
            );
            l_categorias.add(producto);
        }
    }

    public static class MyAdapterE extends BaseAdapter {

        private Context mContext;
        public MyAdapterE(Context c) {
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