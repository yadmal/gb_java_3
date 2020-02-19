package ru.geekbrains.java3.lesson4_multiThreding.mt8_deamon;

public class MainDeamon {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(() -> {

            Thread tt = new Thread(() -> {
                while (true) {
                    System.out.println("A");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            tt.setDaemon(true);
            tt.start();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        while (true) {
        }

    }
}
