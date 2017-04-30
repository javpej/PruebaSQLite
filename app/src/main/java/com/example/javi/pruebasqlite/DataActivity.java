package com.example.javi.pruebasqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

                Intent icancel = getIntent();
                setResult(RESULT_CANCELED, icancel);

                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void pulsarBotonAceptar(View v){


        if (edCiudad.getText().toString().trim().length()==0 || edFecha.getText().toString().trim().length()==0 || edDesc.getText().toString().trim().length()==0){

            Toast.makeText(this, "No has rellenado todos los campos", Toast.LENGTH_SHORT).show();

            edCiudad.setText("");
            edFecha.setText("");
            edDesc.setText("");

        }

        else{

            String ciudad = edCiudad.getText().toString();
            String Fecha = edFecha.getText().toString();
            String Desc = edDesc.getText().toString();

            Intent imessage = getIntent();

            imessage.putExtra("CIUDAD" , ciudad);
            imessage.putExtra("FECHA", Fecha);
            imessage.putExtra("DESC", Desc);

            setResult(2, imessage);

            finish();

        }

    }

}
