package ru.stqa.island.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

    public class DistanceTests {
        @Test
        public void test1() {
            Point p1 = new Point(5, 8);
            Point p2 = new Point(11, 22);
            Assert.assertEquals(p1.distance(p2), 15.23,"Здесь какое-то неправильное число! Переделать!");
        }


        @Test
        public void test2() {
            Point p1 = new Point(5, 10);
            Point p2 = new Point(-5, -10);
            Assert.assertEquals(p1.distance(p2), 22.36,"Здесь какое-то неправильное число! Переделать!");
        }

        @Test
        public void test3() {
            Point p1 = new Point(0, 0);
            Point p2 = new Point(0, 0);
            Assert.assertEquals(p1.distance(p2), 0.0,"Здесь какое-то неправильное число! Переделать!");
        }

        @Test
        public void test4() {
            Point p1 = new Point(2, 4);
            Point p2 = new Point(5, 6);
            Point p3 = p1.getMidpoint(p2);
            Assert.assertEquals(p3.x, 3.5, "Здесь какое-то неправильное число! Переделать!");
            Assert.assertEquals(p3.y, 5.0, "Здесь какое-то неправильное число! Переделать!");
        }
}
