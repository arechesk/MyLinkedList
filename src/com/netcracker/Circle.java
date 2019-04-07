package com.netcracker;

import java.util.Random;

public class Circle implements Comparable{
    private double radius=1.0;
    private String color ="red";

    public Circle() {
        Random rnd=new Random();
        String[] colors={"red","green","black","white","blue","rose","aqua","violet","lime","orange"};
        this.color=colors[rnd.nextInt(colors.length)];
        this.radius=rnd.nextInt(10000);
    }


    public Circle(double radius) {
        this.radius = radius;
    }

    public Circle(double radius, String color) {
        this.radius = radius;
        this.color = color;
    }



    public double getRadius() {
        return radius;
    }

    public String getColor() {
        return color;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Circle[" +
                "radius=" + radius +
                ", color='" + color + '\'' +
                ']';
    }
    public double getArea(){
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Circle circle = (Circle) o;

        if (Double.compare(circle.radius, radius) != 0) return false;
        return color != null ? color.equals(circle.color) : circle.color == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(radius);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Object o) {
        Circle anotherCircle = (Circle) o;
        return (int)this.radius-(int)anotherCircle.radius;
    }
}
