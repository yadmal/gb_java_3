package ru.geekbrains.java3.lesson4_multiThreding.mt2_synck_counter;

public class SynchCounter {

    // если методу задать synchronized, то одновременно им сможет воспользоваться только один поток
    private int c;

    public SynchCounter(){
        c = 0;
    }

    public int value(){
        return c;
    }

    public synchronized void inc(){
        this.c++;
    }

    public synchronized void dec(){
        this.c--;
    }
}
