package ru.stqa.island.sandbox;

public class Square {
    public double l;

    /*это конструктор*/
    public Square(double l) {
        this.l = l;
    }

    /*это метод, которым мы заменяем функцию из основного файла*/
    public double area() {
        return this.l * this.l;
    }

}
