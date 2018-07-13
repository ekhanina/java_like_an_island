package ru.stqa.island.sandbox;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p1) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        double result = Math.sqrt(Math.pow((x - p1.x), 2) + Math.pow((y - p1.y), 2));
        return Double.parseDouble(formatter.format(result));
    }

    public Point getMidpoint(Point p1) {
        double midx;
        double midy;
        midx = (x + p1.x)/2;
        midy = (y + p1.y)/2;
        return new Point(midx, midy);
    }
}