package ru.geekbrains.java3.lesson4_multiThreding.mt7_interupt;

public class InterruptThread {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A");
            }
        });
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        t.stop();// так останавливать поток нельзя, потому что он может не завершить операцию
    }
}
