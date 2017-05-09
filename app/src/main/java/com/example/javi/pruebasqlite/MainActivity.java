package com.example.javi.pruebasqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements MyLongClickListener{

    RecyclerView myRecycler;

    String stCiudad;
    String stFecha;
    String stDesc;

    ArrayList<Item> aItems;
    Adapter myAdapter;
    Boolean deleteClicked;
    int positiondelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deleteClicked = false;
        Toolbar toolbar = (Toolbar) findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);
        myRecycler = (RecyclerView) findViewById(R.id.MyRecycler);

        aItems = new ArrayList<Item>();

        RellenarDatos();

        myAdapter = new Adapter(this, aItems);
        myRecycler.setLayoutManager(new LinearLayoutManager(this));
        myRecycler.setAdapter(myAdapter);
        myAdapter.setMyLongClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (deleteClicked == true){

            menu.findItem(R.id.add_action).setVisible(false);
            menu.findItem(R.id.action_cancelmain).setVisible(true);
            menu.findItem(R.id.action_remove).setVisible(true);

        }else {

            menu.findItem(R.id.add_action).setVisible(true);
            menu.findItem(R.id.action_cancelmain).setVisible(false);
            menu.findItem(R.id.action_remove).setVisible(false);

        }


        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.about_action:

                Intent iabout = new Intent(this, AboutActivity.class);
                startActivity(iabout);

                break;

            case R.id.restore_action:

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Restaurar Datos de Fábrica");
                builder.setIcon(R.drawable.ic_warning);
                builder.setMessage("Esto restaurará la aplicación a su estado original de fábrica");
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ResetearTabla();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                break;

            case R.id.add_action:

                Intent iadd = new Intent(this, DataActivity.class);
                startActivityForResult(iadd, 2);
                break;

            case R.id.action_cancel:

                deleteClicked = false;
                invalidateOptionsMenu();
                break;

            case R.id.action_remove:

                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setTitle("Eliminar Datos");
                builder1.setMessage("¿Esta Seguro que desea eliminar los datos señalados?");
                builder1.setIcon(R.drawable.ic_warning);
                builder1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        eliminarDatos();
                        deleteClicked = false;
                        invalidateOptionsMenu();

                    }
                });
                builder1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                      dialog.cancel();
                    }
                });

                AlertDialog alertDialog = builder1.create();
                alertDialog.show();

        }


        return super.onOptionsItemSelected(item);
    }


    public void RellenarDatos(){



        DBAdapter helper = new DBAdapter(this, "viajes", null, 1);

        SQLiteDatabase db = helper.getReadableDatabase();

        if (db == null){

            Toast.makeText(this, "No existe la tabla", Toast.LENGTH_LONG).show();

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {


            String sNuevaCiudad = data.getStringExtra("CIUDAD");
            String sNuevaFecha = data.getStringExtra("FECHA");
            String sNuevaDesc = data.getStringExtra("DESC");

            Item itemnew = new Item(sNuevaCiudad, sNuevaFecha, sNuevaDesc);
            aItems.add(itemnew);
            myAdapter.notifyDataSetChanged();

            DBAdapter helper = new DBAdapter(this, "viajes", null, 1);
            SQLiteDatabase db = helper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("ciudad", sNuevaCiudad);
            values.put("fecha", sNuevaFecha);
            values.put("descripcion", sNuevaDesc);
            db.insert("viajes", null, values);
            db.close();

            if (resultCode == RESULT_CANCELED) {

                return;
            }

        }
    }

    public void ResetearTabla(){

        getApplicationContext().deleteDatabase("viajes");

        aItems.clear();

        RellenarDatos();

        myAdapter.notifyDataSetChanged();

    }


    @Override
    public void myLongClick(View view, int position) {

        deleteClicked = true;
        invalidateOptionsMenu();
        positiondelete = position;

    }

    public void eliminarDatos(){

        String sqlCiudad = "'"+ aItems.get(positiondelete).getsCiudad().toString()+"'";

        DBAdapter helper = new DBAdapter(this, "viajes", null, 1);
        SQLiteDatabase db =helper.getWritableDatabase();
        db.execSQL("DELETE FROM viajes WHERE ciudad ="+sqlCiudad);
        db.close();
        aItems.remove(positiondelete);
        myAdapter.notifyDataSetChanged();


    }

}
