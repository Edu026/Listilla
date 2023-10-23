package com.example.listilla;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Model: Record (intents=puntuació, nom)
    class Record {
        public int intents;
        public String nom;
        public int resId = R.drawable.logo;

        public Record(int _intents, String _nom, int _resId) {
            intents = _intents;
            nom = _nom;
            resId = _resId;
        }
    }
    // Model = Taula de records: utilitzem ArrayList
     ArrayList<Record> records;

    // ArrayAdapter serà l'intermediari amb la ListView
    static ArrayAdapter<Record> adapter;

    //Seleccion de nombres e intentos

    ArrayList<String> listNombres =  new ArrayList<String>();
    ArrayList<Integer> listIntentos =  new ArrayList<Integer>();

    ArrayList<Integer> listImages = new ArrayList<Integer>();

    String nombre;
    int n_intentos;
    int rImagen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Nombres
        listNombres.add("Manuel");
        listNombres.add("Paco");
        listNombres.add("Jorge");
        listNombres.add("Clara");
        listNombres.add("Marta");
        listNombres.add("Edu");
        listNombres.add("Ainoa");
        listNombres.add("Enric");
        listNombres.add("Albert");
        listNombres.add("Mar");

        //Intentos
        listIntentos.add(10);
        listIntentos.add(11);
        listIntentos.add(12);
        listIntentos.add(13);
        listIntentos.add(44);
        listIntentos.add(1);
        listIntentos.add(150);
        listIntentos.add(5);
        listIntentos.add(80);
        listIntentos.add(14);

        listImages.add(R.drawable.logo);
        listImages.add(R.drawable.logo1);
        listImages.add(R.drawable.logo2);
        listImages.add(R.drawable.logo3);
        listImages.add(R.drawable.logo4);




        // Inicialitzem model
        records = new ArrayList<Record>();
        // Afegim alguns exemples
        records.add( new Record(33,"Manolo", R.drawable.logo) );
        records.add( new Record(12,"Pepe", R.drawable.logo) );
        records.add( new Record(42,"Laura", R.drawable.logo) );

        // Inicialitzem l'ArrayAdapter amb el layout pertinent
        adapter = new ArrayAdapter<Record>( this, R.layout.list_item, records )
        {
            @Override
            public View getView(int pos, View convertView, ViewGroup container)
            {
                // getView ens construeix el layout i hi "pinta" els valors de l'element en la posició pos
                if( convertView==null ) {
                    // inicialitzem l'element la View amb el seu layout
                    convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
                }
                // "Pintem" valors (també quan es refresca)
                ((TextView) convertView.findViewById(R.id.nom)).setText(getItem(pos).nom);
                ((TextView) convertView.findViewById(R.id.intents)).setText(Integer.toString(getItem(pos).intents));
                ((ImageView) convertView.findViewById(R.id.image)).setImageResource(getItem(pos).resId);
                return convertView;
            }

        };

        // busquem la ListView i li endollem el ArrayAdapter
        ListView lv = (ListView) findViewById(R.id.recordsView);
        lv.setAdapter(adapter);

        // botó per afegir entrades a la ListView
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<50;i++) {
                    nombre = listNombres.get( new Random().nextInt(9));
                    n_intentos = listIntentos.get(new Random().nextInt(9));
                    rImagen = listImages.get(new Random().nextInt(5));
                    records.add(new Record(n_intentos, nombre,rImagen));
                }
                // notificar l'adapter dels canvis al model
                adapter.notifyDataSetChanged();
            }
        });

        Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(records, new Comparator<Record>() {
                    @Override
                    public int compare(Record o1, Record o2) {
                        return new String (o1.nom).compareTo(o2.nom);
                    }
                });
                adapter.notifyDataSetChanged();

            }
        });

    }
}