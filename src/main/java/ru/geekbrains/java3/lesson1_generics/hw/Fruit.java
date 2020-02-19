package ru.geekbrains.java3.lesson1_generics.hw;

public abstract class Fruit {
    private int weight;

    public Fruit(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }
}
