package com.example.javi.pruebasqlite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Javi on 25/4/17.
 */

public class Adapter extends RecyclerView.Adapter<MyViewHolder> implements View.OnLongClickListener{

    Context context;
    ArrayList<Item> itemArraList;
    MyLongClickListener myLongClickListener;

    public interface MyLongClickListener{

        boolean onItemlongClick (View view, int position);

    }

    @Override
    public boolean onLongClick(View v) {

        myLongClickListener.onItemlongClick(v, getPo

        return true;
    }

    
    public Adapter(Context context, ArrayList<Item> itemArraList) {
        this.context = context;
        this.itemArraList = itemArraList;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View v = inflater.inflate(R.layout.cardlayout, parent, false);

        MyViewHolder holder = new MyViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Item itemselected = itemArraList.get(position);

        holder.tvCiudad.setText(itemselected.getsCiudad());
        holder.tvFecha.setText(itemselected.getsFecha());
        holder.tvDesc.setText(itemselected.getsDesc());
        final ImageView img = holder.ivCheck;
        holder.itemView.setOnLongClickListener(this);

    }

    @Override
    public int getItemCount() {

        return itemArraList.size();
    }

    public void setMyLongClickListener(MyLongClickListener clickListener){

        clickListener = this.myLongClickListener;
    }


}
 class MyViewHolder extends RecyclerView.ViewHolder {

     TextView tvCiudad;
     TextView tvFecha;
     TextView tvDesc;
     ImageView ivCheck;

    public MyViewHolder(View itemView) {

        super(itemView);

        tvCiudad = (TextView) itemView.findViewById(R.id.TVCiudad);
        tvFecha = (TextView) itemView.findViewById(R.id.TVFecha);
        tvDesc = (TextView) itemView.findViewById(R.id.TVDescripcion);
        ivCheck = (ImageView) itemView.findViewById(R.id.IVCheck);


    }


}