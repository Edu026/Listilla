package com.example.listilla;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Model: Record (intents=puntuació, nom)
    class Record {
        public int intents;
        public String nom;

        public Record(int _intents, String _nom ) {
            intents = _intents;
            nom = _nom;
        }
    }
    // Model = Taula de records: utilitzem ArrayList
    ArrayList<Record> records;

    // ArrayAdapter serà l'intermediari amb la ListView
    ArrayAdapter<Record> adapter;

    //Seleccion de nombres e intentos

    ArrayList<String> listNombres =  new ArrayList<String>();
    ArrayList<Integer> listIntentos =  new ArrayList<Integer>();

    String nombre;
    int n_intentos;


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


        // Inicialitzem model
        records = new ArrayList<Record>();
        // Afegim alguns exemples
        records.add( new Record(33,"Manolo") );
        records.add( new Record(12,"Pepe") );
        records.add( new Record(42,"Laura") );

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
                    records.add(new Record(n_intentos, nombre));
                }
                // notificar l'adapter dels canvis al model
                adapter.notifyDataSetChanged();
            }
        });
    }
}