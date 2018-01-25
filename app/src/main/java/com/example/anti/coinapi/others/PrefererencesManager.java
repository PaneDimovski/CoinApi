package com.example.anti.coinapi.others;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.anti.coinapi.Models.Favorites;
import com.google.gson.Gson;

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



}
