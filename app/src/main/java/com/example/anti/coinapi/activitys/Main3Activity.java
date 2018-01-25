package com.example.anti.coinapi.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anti.coinapi.Models.Coins;
import com.example.anti.coinapi.Models.Favorites;
import com.example.anti.coinapi.R;
import com.example.anti.coinapi.adapter.RVadapter;
import com.example.anti.coinapi.api.RestApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main3Activity extends AppCompatActivity {


    @BindView(R.id.name2)
    TextView ime;

    @BindView(R.id.imgdetails2)
    ImageView slika;

    @BindView(R.id.simbol)
    TextView simbol;

    @BindView(R.id.usd)
    TextView usd;

    @BindView(R.id.btc)
    TextView btc;

    @BindView(R.id.procenti)
    TextView procenti;

   // Coins  detalis;

ArrayList<Coins> detali;

    RestApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ButterKnife.bind(this);


        final Intent intent = getIntent();
        if (intent.hasExtra("details")) {

            api = new RestApi(Main3Activity.this);

            Call<ArrayList<Coins>> call = api.getBtc(intent.getStringExtra("details"));

           // detali = (Coins) intent.getSerializableExtra("details");

            call.enqueue(new Callback<ArrayList<Coins>>() {
                @Override
                public void onResponse(Call<ArrayList<Coins>> call, Response<ArrayList<Coins>> response) {

                    if (response.code() == 200) {

                        detali = response.body();


                        Coins coin = detali.get(0);


                        ime.setText(coin.getName().toString());
                        simbol.setText(coin.getSymbol().toString());
                        usd.setText(coin.getPrice_usd().toString());
                        btc.setText(coin.getPrice_btc().toString());
                        procenti.setText(coin.getPercent_change_24h().toString());

                        Picasso.with(Main3Activity.this).load("https://files.coinmarketcap.com/static/img/coins/64x64/" + coin.getId() + ".png").centerInside().fit().into(slika);


                    } else if (response.code() == 401) {
                        Toast.makeText(Main3Activity.this, "Greska", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ArrayList<Coins>> call, Throwable t) {

                }
            });
           }




    }



}
