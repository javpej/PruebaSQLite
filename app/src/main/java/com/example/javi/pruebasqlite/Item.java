package com.example.javi.pruebasqlite;

import android.content.Context;

/**
 * Created by Javi on 25/4/17.
 */

public class Item {

    String sCiudad;
    String sFecha;
    String sDesc;
    Context context;

    public Item(String sCiudad, String sFecha, String sDesc, Context context) {
        this.sCiudad = sCiudad;
        this.sFecha = sFecha;
        this.sDesc = sDesc;
        this.context = context;
    }

    public String getsCiudad() {
        return sCiudad;
    }

    public void setsCiudad(String sCiudad) {
        this.sCiudad = sCiudad;
    }

    public String getsFecha() {
        return sFecha;
    }

    public void setsFecha(String sFecha) {
        this.sFecha = sFecha;
    }

    public String getsDesc() {
        return sDesc;
    }

    public void setsDesc(String sDesc) {
        this.sDesc = sDesc;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
