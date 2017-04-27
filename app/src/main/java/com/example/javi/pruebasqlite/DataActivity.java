package com.example.javi.pruebasqlite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Javi on 27/4/17.
 */

public class DataActivity extends AppCompatActivity {

    Toolbar toolbaradd;
    EditText edCiudad;
    EditText edFecha;
    EditText edDesc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        toolbaradd = (Toolbar) findViewById(R.id.Toolbaradd);
        setSupportActionBar(toolbaradd);

        edCiudad = (EditText) findViewById(R.id.ETCiudad);
        edFecha = (EditText) findViewById(R.id.ETFecha);
        edDesc = (EditText) findViewById(R.id.ETDesc);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menuadd, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){

            case R.id.action_cancel:

                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void pulsarBotonAceptar(View v){

        String ciudad = edCiudad.getText().toString();
        String Fecha = edFecha.getText().toString();

    }

}
