package com.example.javi.pruebasqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    RecyclerView myRecycler;

    String stCiudad;
    String stFecha;
    String stDesc;

    ArrayList<Item> aItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);
        myRecycler = (RecyclerView) findViewById(R.id.MyRecycler);

        aItems = new ArrayList<Item>();

        RellenarDatos();

        Adapter myAdapter = new Adapter(this, aItems);
        myRecycler.setLayoutManager(new LinearLayoutManager(this));
        myRecycler.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.about_action:

                Toast.makeText(this, "Has pulsado Acerca de...", Toast.LENGTH_SHORT).show();

            case R.id.add_action:

                Toast.makeText(this, "Has pulsado Añadir", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }


    public void RellenarDatos(){



        DBAdapter helper = new DBAdapter(this, "viajes", null, 1);

        SQLiteDatabase db = helper.getReadableDatabase();

        if (db == null){

            Toast.makeText(this, "No se encuentra Base de Datos", Toast.LENGTH_LONG).show();

        } else {

            String sqlLook = "SELECT * FROM viajes";

            Cursor cursor = db.rawQuery(sqlLook, null, null);

            while (cursor.moveToNext()) {

            stCiudad = cursor.getString(0);
            stFecha = cursor.getString(1);
            stDesc = cursor.getString(2);

            Item itemadd = new Item(stCiudad, stFecha, stDesc);

              aItems.add(itemadd);
            }
        }

        db.close();

    }

}
