package ru.geekbrains.java3.lesson1_generics;

import java.io.Serializable;

public class BoxWithNumbers<T extends Number & Serializable> {

    private T[] arr;

    public T[] getArr() {
        return arr;
    }

    public void setArr(T[] arr) {
        this.arr = arr;
    }

    public BoxWithNumbers(T... arr) {
        this.arr = arr;
    }

    public double avg(){
        double d = 0.0;
        for (int i = 0; i < arr.length; i++){
            d += arr[i].doubleValue();
        }
        return d;
    }

    public boolean sameAvg(BoxWithNumbers<?> another){
        return Math.abs(this.avg() - another.avg()) < 0.001;
    }
}
