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
import com.example.anti.coinapi.activitys.Main2Activity;
import com.example.anti.coinapi.others.OnRowClick;
import com.example.anti.coinapi.others.PrefererencesManager;
import com.squareup.picasso.Picasso;

import java.nio.InvalidMarkException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anti on 1/21/2018.
 */

public class RVadapter extends RecyclerView.Adapter<RVadapter.ViewHolder> {

    public Context context;
    public ArrayList<Coins> coinsList;
    OnRowClick listener;

    public RVadapter(Context context, ArrayList<Coins> coinsList,OnRowClick listener) {
        this.context = context;
        this.coinsList = coinsList;
        this.listener = listener;

    }

    public void setItems(ArrayList<Coins> coinsList, OnRowClick listener) {
        this.coinsList = coinsList;
        this.listener = listener;

        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // inflate the custom layout
        View view = inflater.inflate(R.layout.recycle_view, parent, false);
        // Return a new holde instance
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Coins coins = coinsList.get(position);

        holder.ime.setText(coins.getName());
        holder.simbol.setText(coins.getSymbol());
        holder.cena.setText(coins.getPrice_usd() + "");

        Picasso.with(context).load("https://files.coinmarketcap.com/static/img/coins/64x64/" + coins.getId() + ".png").centerInside().fit().into(holder.slika);


        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((Main2Activity) context).favo.favorites.add(coins);


            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               listener.OnRowClick(coins, position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return coinsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.ime)
        TextView ime;

        @BindView(R.id.simbol)
        TextView simbol;

        @BindView(R.id.cena)
        TextView cena;

        @BindView(R.id.img)
        ImageView slika;

        @BindView(R.id.plus)
        ImageView plus;


        public ViewHolder(View itemView) {

            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
