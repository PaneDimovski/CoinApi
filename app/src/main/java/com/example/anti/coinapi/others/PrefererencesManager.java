package com.example.anti.coinapi.others;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.anti.coinapi.Models.Coins;
import com.example.anti.coinapi.Models.Favorites;
import com.example.anti.coinapi.Models.Settings;
import com.google.gson.Gson;

import java.util.Set;

/**
 * Created by Anti on 1/21/2018.
 */

public class PrefererencesManager  {

    private static SharedPreferences getPreferences (Context c) {

        return c.getApplicationContext().getSharedPreferences("MySharedPreffsFile", Activity.MODE_PRIVATE);

    }

    public static void  addFavorites (Favorites favorites, Context c) {

        Gson gson = new Gson();
        String mapString = gson.toJson(favorites);
        getPreferences(c).edit().putString("favorites",mapString).apply();

    }

    public static Favorites getFavorites (Context c) {

        return  new Gson().fromJson(getPreferences(c).getString("favorites", "" ),Favorites.class);
    }



    public static void addSetings (Settings settings, Context c){

        Gson gson2 = new Gson();
        String mapString2 = gson2.toJson(settings);
        getPreferences(c).edit().putString("limit", mapString2).apply();

    }

    public static Settings getSettings (Context c) {

        return  new Gson().fromJson(getPreferences(c).getString("limit", "" ),Settings.class);
    }






}
