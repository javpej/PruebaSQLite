package com.example.javi.pruebasqlite;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Javi on 25/4/17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context context;
    ArrayList<Item> itemArraList;

    MyLongClickListener myLongClickListener;

    public Adapter(Context context, ArrayList<Item> itemArraList) {
        this.context = context;
        this.itemArraList = itemArraList;
        myLongClickListener = (MyLongClickListener) context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View v = inflater.inflate(R.layout.cardlayout, parent, false);

        MyViewHolder holder = new MyViewHolder(v);


        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Item itemselected = itemArraList.get(position);

        holder.tvCiudad.setText(itemselected.getsCiudad());
        holder.tvFecha.setText(itemselected.getsFecha());
        holder.tvDesc.setText(itemselected.getsDesc());

    }

    @Override
    public int getItemCount() {

        return itemArraList.size();
    }

    public  void setMyLongClickListener(MyLongClickListener myLongClickListener){

        myLongClickListener = this.myLongClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        TextView tvCiudad;
        TextView tvFecha;
        TextView tvDesc;
        ImageView imCheck;
        RelativeLayout layout;

        public MyViewHolder(View itemView) {

            super(itemView);

            layout = (RelativeLayout) itemView.findViewById(R.id.LayoutCard);
            tvCiudad = (TextView) itemView.findViewById(R.id.TVCiudad);
            tvFecha = (TextView) itemView.findViewById(R.id.TVFecha);
            tvDesc = (TextView) itemView.findViewById(R.id.TVDescripcion);
            imCheck = (ImageView) itemView.findViewById(R.id.IVCheck);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {


            layout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorBackground));
            imCheck.setVisibility(View.VISIBLE);
            myLongClickListener.myLongClick(v, getAdapterPosition());

            return true;
        }

    }
}