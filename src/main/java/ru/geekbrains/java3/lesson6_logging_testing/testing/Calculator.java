package ru.geekbrains.java3.lesson6_logging_testing.testing;

public class Calculator {
    // Для создания тестов нужно подключить к проекту maven и подтянуть зависимость от junit
    public int add(int x, int y){
        return x+y;
    }

    public int div(int x, int y){
        return x/y;
    }
}
