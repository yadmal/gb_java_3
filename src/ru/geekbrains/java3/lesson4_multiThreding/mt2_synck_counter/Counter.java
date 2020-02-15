package ru.geekbrains.java3.lesson4_multiThreding.mt2_synck_counter;

public class Counter {
    private int c;

    public Counter(){
        c = 0;
    }

    public int value(){
        return c;
    }

    public void inc(){
        this.c++;
    }

    public void dec(){
        this.c--;
    }
}
