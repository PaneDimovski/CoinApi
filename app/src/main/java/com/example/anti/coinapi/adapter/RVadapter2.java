package com.example.anti.coinapi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anti.coinapi.Models.Coins;
import com.example.anti.coinapi.Models.Favorites;
import com.example.anti.coinapi.R;
import com.example.anti.coinapi.others.OnRowClick;
import com.example.anti.coinapi.others.PrefererencesManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anti on 1/21/2018.
 */

public class RVadapter2 extends RecyclerView.Adapter<RVadapter2.ViewHolder> {

    public Context context;
    public ArrayList<Coins> favoritesLis;

    OnRowClick listener2;


    public RVadapter2(Context context, ArrayList<Coins> favoritesLis, OnRowClick listener2) {
        this.context = context;
        this.favoritesLis = favoritesLis;
        this.listener2= listener2;

    }

    public void setItems(ArrayList<Coins> favoritesLis) {
        this.favoritesLis = favoritesLis;
        notifyDataSetChanged();
    }

    @Override
    public RVadapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // inflate the custom layout
        View view = inflater.inflate(R.layout.recycle_view2, parent, false);
        // Return a new holde instance
        RVadapter2.ViewHolder viewHolder = new RVadapter2.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {



        final Coins coins=  favoritesLis.get(position);

        holder.name.setText(coins.getName());
        holder.symbol.setText(coins.getSymbol());
        holder.price.setText(coins.getPrice_usd()+"");

        Picasso.with(context).load("https://files.coinmarketcap.com/static/img/coins/64x64/" + coins.getId() +".png").centerInside().fit().into(holder.img1);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener2.OnRowClick(coins,position);
            }
        });



    }

    @Override
    public int getItemCount() {
        return favoritesLis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.symbol)
        TextView symbol;

        @BindView(R.id.price)
        TextView price;

        @BindView(R.id.slika)
        ImageView img1;




        public ViewHolder(View itemView) {

            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
