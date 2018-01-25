package com.example.anti.coinapi.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.anti.coinapi.Models.Coins;
import com.example.anti.coinapi.Models.Favorites;
import com.example.anti.coinapi.R;
import com.example.anti.coinapi.adapter.RVadapter;
import com.example.anti.coinapi.adapter.RVadapter2;
import com.example.anti.coinapi.others.OnRowClick;
import com.example.anti.coinapi.others.PrefererencesManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    Coins coins;
    Favorites fav = new Favorites();



    @BindView(R.id.recycle2)
    RecyclerView recycle2;

    RVadapter2 adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

            fav.favorites = new ArrayList<>();

            adapter2 = new RVadapter2(this, fav.favorites, new OnRowClick() {
                @Override
                public void OnRowClick(Coins coins, int position) {


                    Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                    intent.putExtra("details", fav.favorites.get(position).getId());
                    //intent.putExtra("pozicija", position);
                    startActivity(intent);
                }
            });




      //  adapter2.setItems(getList());

        recycle2.setHasFixedSize(true);
        recycle2.setLayoutManager(new LinearLayoutManager(this));
        recycle2.setAdapter(adapter2);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.flobtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivityForResult(intent,1000);


            }
        });
    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if(requestCode==1000 && resultCode==RESULT_OK) {



            fav = PrefererencesManager.getFavorites(this);

            adapter2 = new RVadapter2(this, fav.favorites, new OnRowClick() {
                @Override
                public void OnRowClick(Coins coins, int position) {


                    Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                    intent.putExtra("details", fav.favorites.get(position).getId());
                    //intent.putExtra("pozicija", position);
                    startActivity(intent);

                }
            });

           // adapter2.setItems(fav.favorites);

            recycle2.setAdapter(adapter2);

        }

    }

//    ArrayList<Coins> getList() {
//
//        fav = PrefererencesManager.getFavorites(this);
//
//        return fav.favorites;
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
