package ru.geekbrains.java3.lesson1_generics.hw;

import java.util.ArrayList;
import java.util.Arrays;

public class MainHomeWork {
    public static void main(String[] args) {
        Box<Fruit> fruitBox = new Box<>();

        Box<Apple> appleBox = new Box<>();
        appleBox.addFruits(new Apple());
        appleBox.addFruits(new Apple());
        appleBox.addFruits(new Apple());
        appleBox.addFruits(new Apple());
        appleBox.addFruits(new Apple());

        System.out.println(appleBox.getWeight());
        appleBox.transferTo(fruitBox);
        System.out.println(appleBox.getWeight());
        System.out.println(fruitBox.getWeight());

    }

    //Поменять местами два элемента любого массива
    public static void swap(Object[] arr, int x1, int x2){
        Object temp = arr[x1];
        arr[x1] = arr[x2];
        arr[x2] = temp;
    }

    //Преобразовать массив в ArrayList
    public static <T> ArrayList<T> toArrayList(T[] array){
        return new ArrayList<T>(Arrays.asList(array));
    }
}
