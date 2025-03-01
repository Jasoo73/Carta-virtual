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

public class PromocionesActivity extends AppCompatActivity {

    private PromocionesActivity.MyAdapter myAdapter = null;
    private static ArrayList<Producto> promocionesLista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_promociones);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setData();

        myAdapter = new PromocionesActivity.MyAdapter(this);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(myAdapter);
    }

    public void setData() {
        promocionesLista.clear();

        // Obtener los datos de las promociones desde los recursos
        String[] nombres = getResources().getStringArray(R.array.promociones);
        String[] descripciones = getResources().getStringArray(R.array.promocionesDescripciones);
        TypedArray imagenes = getResources().obtainTypedArray(R.array.promocionesImagenes);

        // Agregar productos a la lista
        for (int i = 0; i < nombres.length; i++) {
            Producto producto = new Producto(
                    nombres[i],
                    descripciones[i],
                    imagenes.getResourceId(i, -1)
            );
            promocionesLista.add(producto);
        }
    }

    public static class MyAdapter extends BaseAdapter {
        private Context mContext;
        public MyAdapter(Context c) {
            mContext = c;
        }

        @Override
        public int getCount() {
            return promocionesLista.size();
        }

        @Override
        public Object getItem(int position) {
            return promocionesLista.get(position);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.layout_items, null);
            }

            ImageView img = view.findViewById(R.id.image);
            img.setImageDrawable(mContext.getResources().getDrawable(promocionesLista.get(position).image));

            TextView tTitle = view.findViewById(R.id.title);
            tTitle.setText(promocionesLista.get(position).titulo);

            TextView tDescription = view.findViewById(R.id.description);
            tDescription.setText(promocionesLista.get(position).descripcion);

            return view;
        }
    }
}
