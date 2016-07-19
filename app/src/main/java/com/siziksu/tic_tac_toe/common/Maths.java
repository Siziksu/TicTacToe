package com.siziksu.tic_tac_toe.common;

public class Maths {

    private static Maths instance;

    private Maths() {}

    public static Maths getInstance() {
        if (instance == null) {
            instance = new Maths();
        }
        return instance;
    }

    public double round(double number) {
        return round(number, 2);
    }

    public double round(double number, int decimals) {
        return (double) Math.round(number * Math.pow(10, decimals)) / Math.pow(10l, decimals);
    }
}
