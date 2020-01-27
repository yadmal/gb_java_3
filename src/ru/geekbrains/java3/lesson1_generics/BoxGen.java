package ru.geekbrains.java3.lesson1_generics;

public class BoxGen<T> {
/*
    E - element    K - key
    N - number     T - type
    V - value
*/

    private T obj;
//    public static T obj;  -  нельзя

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public BoxGen(T obj) {
        this.obj = obj;
    }

    public void doSmth(){
//        T obj = new T();  -  нельзя создавать объекты, т.к. не знаем что это за тип и какие у него конструкторы
//        T[] obj = new T[10];  -  тоже нельзя создавать, но можно передать через параметры
    }
}
