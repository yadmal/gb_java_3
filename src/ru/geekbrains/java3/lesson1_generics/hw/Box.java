package ru.geekbrains.java3.lesson1_generics.hw;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {

    private ArrayList<T> fruits;

    public void addFruits(T fruits){
        this.fruits.add(fruits);
    }

    public ArrayList<T> getFruits(){
        return this.fruits;
    }

    public Box(T... fruits){
        this.fruits = new ArrayList<T>(Arrays.asList(fruits));
    }

    public int getWeight(){
        if(this.fruits.size() == 0){
            return 0;
        } else{
            return this.fruits.size() * this.fruits.get(0).getWeight();
        }
    }

    //Сравнение коробок по весу
    public boolean compare(Box anotherBox){
        return Math.abs(this.getWeight() - anotherBox.getWeight()) < 0.00001;
    }

    //Пересыпать фрукты из одной коробки в другую
    public void transferTo(Box<? super Fruit> anotherBox){
        anotherBox.fruits.addAll(this.fruits);
        this.fruits.clear();
    }
}
