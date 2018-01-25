package com.example.anti.coinapi.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Anti on 1/21/2018.
 */

public class Coins implements Serializable {

        String id;
        String name;
        String symbol;
        Double price_usd;
        Double price_btc;
        Double percent_change_24h;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(Double price_usd) {
        this.price_usd = price_usd;
    }

    public Double getPrice_btc() {
        return price_btc;
    }

    public void setPrice_btc(Double price_btc) {
        this.price_btc = price_btc;
    }

    public Double getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(Double percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public Coins(String id, String name, String symbol, Double price_usd, Double price_btc, Double percent_change_24h) {

        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.price_usd = price_usd;
        this.price_btc = price_btc;
        this.percent_change_24h = percent_change_24h;
    }
}
