package com.majiekun.echartstest.entity;

public class intakeStock {
    private String date;
    private int coalFactory;
    private int coalStove;
    private int stock;
    private double intakeFactoryList;
    private double intakeStoveList;
    private double intakeFactoryCoal;
    private double intakeStoveCoal;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public int getCoalFactory() {
        return coalFactory;
    }

    public void setCoalFactory(int coalFactory) {
        this.coalFactory = coalFactory;
    }

    public int getCoalStove() {
        return coalStove;
    }

    public void setCoalStove(int coalStove) {
        this.coalStove = coalStove;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getIntakeFactoryList() {
        return intakeFactoryList;
    }

    public void setIntakeFactoryList(double intakeFactoryList) {
        this.intakeFactoryList = intakeFactoryList;
    }

    public double getIntakeStoveList() {
        return intakeStoveList;
    }

    public void setIntakeStoveList(double intakeStoveList) {
        this.intakeStoveList = intakeStoveList;
    }

    public double getIntakeFactoryCoal() {
        return intakeFactoryCoal;
    }

    public void setIntakeFactoryCoal(double intakeFactoryCoal) {
        this.intakeFactoryCoal = intakeFactoryCoal;
    }

    public double getIntakeStoveCoal() {
        return intakeStoveCoal;
    }

    public void setIntakeStoveCoal(double intakeStoveCoal) {
        this.intakeStoveCoal = intakeStoveCoal;
    }
}
