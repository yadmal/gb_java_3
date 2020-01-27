package ru.geekbrains.java3.lesson1_generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
//        BoxGen<Integer> bgi1 = new BoxGen<>(1);
//        BoxGen<Integer> bgi2 = new BoxGen<>(2);
//
//        BoxGen<String> bgs1 = new BoxGen<String>("Java");
//
//        int sum = bgi1.getObj() + bgi2.getObj();
//        System.out.println(sum);


        /* ОШИБКА ОКРУГЛЕНИЯ
        double a = 0.7;
        double b = 0.0;
        for (int i = 0; i < 70; i++){
            b += 0.01;
        }
        System.out.println(a == b);
        System.out.println(b);
        */

        BoxWithNumbers<Integer> bn1 = new BoxWithNumbers<Integer>(1, 2, 3);
        BoxWithNumbers<Integer> bn2 = new BoxWithNumbers<Integer>(3, 2, 1);
        BoxWithNumbers<Float> bn3 = new BoxWithNumbers<Float>(3f, 2f, 1f);
        System.out.println(bn1.sameAvg(bn3));

        List<Number> list = new ArrayList<>(Arrays.asList(2, 2f,3.0));
        System.out.println(sumOfElement(list));
    }

    public static double sumOfElement(List<Number> list){
        double d = 0.0f;
        for (Number o: list) {
            d += o.doubleValue();
        }
        return d;
    }
}
