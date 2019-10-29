package com.nettytry.rpc;

/**
 * Created by adimn on 2019/10/29.
 */
public class CalculateImpl implements Calculator {
    @Override
    public double add(double v1, double v2) {
        return v1 + v2;
    }

    @Override
    public double substraction(double v1, double v2) {
        return v1 - v2;
    }

    @Override
    public double multiply(double v1, double v2) {
        return v1 * v2;
    }
}
