package com.example.anti.coinapi.activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anti.coinapi.Models.Coins;
import com.example.anti.coinapi.Models.Favorites;
import com.example.anti.coinapi.Models.Settings;
import com.example.anti.coinapi.R;
import com.example.anti.coinapi.adapter.RVadapter;
import com.example.anti.coinapi.api.RestApi;
import com.example.anti.coinapi.others.OnRowClick;
import com.example.anti.coinapi.others.PrefererencesManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.recycle)
    RecyclerView recycle;


    RVadapter adapter;
    ArrayList<Coins> coins;

    public Favorites favo;
    RestApi api;

    Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        favo = PrefererencesManager.getFavorites(this);

        ButterKnife.bind(this);



        favo = PrefererencesManager.getFavorites(this);

        api = new RestApi(Main2Activity.this);

        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setAdapter(adapter);

        settings = PrefererencesManager.getSettings(this);
        if (settings == null )  {

            settings = new Settings();}
            else  {

            Call<Settings> call2 = api.getLimit(settings.convert, settings.limit);

            call2.enqueue(new Callback<Settings>() {
                @Override
                public void onResponse(Call<Settings> call, Response<Settings> response) {

                    if (response.code() == 200) {

                        settings = response.body();
                        adapter = new RVadapter(Main2Activity.this, coins, new OnRowClick() {
                            @Override
                            public void OnRowClick(Coins coins, int position) {
                                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                                intent.putExtra("details", favo.favorites.get(position).getId());
                                //intent.putExtra("pozicija", position);
                                startActivity(intent);
                            }
                        });
                        recycle.setAdapter(adapter);


                    } else if (response.code() == 401) {
                        Toast.makeText(Main2Activity.this, "Greska", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Settings> call, Throwable t) {

                }
            });


        }



//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Main2Activity.this, 2);
//        recycle.setLayoutManager(mLayoutManager);

        Call<ArrayList<Coins>> call = api.getCoins();

        call.enqueue(new Callback<ArrayList<Coins>>() {
            @Override
            public void onResponse(Call<ArrayList<Coins>> call, Response<ArrayList<Coins>> response) {

                if (response.code() == 200) {

                    coins = response.body();
                    adapter = new RVadapter(Main2Activity.this, coins, new OnRowClick() {
                        @Override
                        public void OnRowClick(Coins coins, int position) {
                            Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                            intent.putExtra("details", favo.favorites.get(position).getId());
                            //intent.putExtra("pozicija", position);
                            startActivity(intent);
                        }
                    });
                    recycle.setAdapter(adapter);


                } else if (response.code() == 401) {
                    Toast.makeText(Main2Activity.this, "Greska", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Coins>> call, Throwable t) {

            }
        });




    }

    @Override
    public void onBackPressed() {
        PrefererencesManager.addFavorites(favo, this);
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();

    }
}
