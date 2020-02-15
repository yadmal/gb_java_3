package ru.geekbrains.java3.lesson4.mt.p7_wait_and_notify;

public class Test {
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.print("A");
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.print("B");
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.print("C");
            }
        }).start();
    }
}
