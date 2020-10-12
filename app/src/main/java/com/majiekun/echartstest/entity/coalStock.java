package com.majiekun.echartstest.entity;

import android.webkit.JavascriptInterface;

public class coalStock {
    private String date;
    private String FCMC;
    private int stockCoal;
    private int stockHeat;
    private double stockUnitPrice;
    private double stockSulfur;

    public void setDate(String date) {
        this.date = date;
    }

    public void setFCMC(String FCMC) {
        this.FCMC = FCMC;
    }

    public void setStockCoal(int stockCoal) {
        this.stockCoal = stockCoal;
    }

    public void setStockHeat(int stockHeat) {
        this.stockHeat = stockHeat;
    }

    public void setStockUnitPrice(double stockUnitPrice) {
        this.stockUnitPrice = stockUnitPrice;
    }

    public void setStockSulfur(double stockSulfur) {
        this.stockSulfur = stockSulfur;
    }
    //标注可以在JS中用
    @JavascriptInterface
    public String getDate() {

        return date;
    }

    @JavascriptInterface
    public String getFCMC() {
        return FCMC;
    }

    @JavascriptInterface
    public int getStockCoal() {
        return stockCoal;
    }

    @JavascriptInterface
    public int getStockHeat() {
        return stockHeat;
    }

    @JavascriptInterface
    public double getStockUnitPrice() {
        return stockUnitPrice;
    }

    @JavascriptInterface
    public double getStockSulfur() {
        return stockSulfur;
    }
}
