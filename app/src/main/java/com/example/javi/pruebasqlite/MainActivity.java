package com.example.javi.pruebasqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView myRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);
        myRecycler = (RecyclerView) findViewById(R.id.MyRecycler);

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




}
