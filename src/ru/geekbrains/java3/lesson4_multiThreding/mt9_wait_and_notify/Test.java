package ru.geekbrains.java3.lesson4_multiThreding.mt9_wait_and_notify;

public class Test {
    // будут распечатываться как АААААВВВВВССССС, потому что для процессора это слишком мелкая задача и проще отпечатать все сразу
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
