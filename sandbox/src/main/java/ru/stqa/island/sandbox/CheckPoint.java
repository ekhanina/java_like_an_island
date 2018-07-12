package ru.stqa.island.sandbox;

public class CheckPoint {
    public static void main(String[] args) {
        Point p1 = new Point(5, 7);
        Point p2 = new Point(64, 111);
        System.out.println("Расстояние между двумя точками " + p1.distance(p2));
    }
}
