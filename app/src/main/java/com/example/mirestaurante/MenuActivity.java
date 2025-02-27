package com.example.mirestaurante;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import com.example.mirestaurante.Producto;

public class MenuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //Declaramos variables

    private MyAdapter mAdapter = null;

    //Arraylist
    private static ArrayList<Producto> l_categorias = new ArrayList<Producto>();

    //String [] Categorias = new String [] {"Entrada", "Platos Fuertes", "Bebidas", "Postres", "Licores"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /*
        ListView lv =findViewById(android.R.id.list);
        ListAdapter la = new ArrayAdapter <String>(this, android.R.layout.simple_list_item_1,Categorias );
        lv.setAdapter(la);
         */

        setData();

        mAdapter = new MyAdapter(this);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(this);

    }

    public void setData() {
        l_categorias.clear();

        String[] categorias = getResources().getStringArray(R.array.categorias);

        for (String categoria : categorias) {
            l_categorias.add(new Producto(categoria, "", R.drawable.festinn)); // Descripción vacía
        }
    }

    public static class MyAdapter extends BaseAdapter{

        private Context mContext;
        public MyAdapter(Context c) {
            mContext = c;
        }

        @Override
        public int getCount() {
            return l_categorias.size();
        }

        @Override
        public Object getItem(int i) {
            return l_categorias.get(i);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intencion = null;

        switch (i){

            case 0:
                intencion = new Intent(this, EntradasActivity.class);
                startActivity(intencion);
                break;

            case 1:
                intencion = new Intent(this, PlatosActivity.class);
                startActivity(intencion);
                break;

            case 2:
                intencion = new Intent(this, BebidasActivity.class);
                startActivity(intencion);
                break;

            case 3:
                intencion = new Intent(this, PostresActivity.class);
                startActivity(intencion);
                break;

            case 4:
                intencion = new Intent(this, LicoresActivity.class);
                startActivity(intencion);
                break;

        }
    }

}