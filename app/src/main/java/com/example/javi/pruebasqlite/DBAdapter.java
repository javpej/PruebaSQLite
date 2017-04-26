package com.example.javi.pruebasqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Javi on 25/4/17.
 */

public class DBAdapter extends SQLiteOpenHelper {

    String SqlCreate = "CREATE TABLE viajes (id INTEGER PRIMARY KEY AUTOINCREMENT, ciudad TEXT, fecha TEXT, descripcion TEXT)";

    public DBAdapter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SqlCreate);
        String db1 = "INSERT INTO viajes VALUES('Pais Vasco', 'Agosto 2013', 'Pasada de Viaje!!!')";
        db.execSQL(db1);
        String db2 = "INSERT INTO viajes VALUES ('Pamplona', 'Julio 2017', 'Increibles San Fermines!!!')";
        db.execSQL(db2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
