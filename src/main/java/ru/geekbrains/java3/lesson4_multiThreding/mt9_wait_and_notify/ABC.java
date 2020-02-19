package ru.geekbrains.java3.lesson4_multiThreding.mt9_wait_and_notify;

public class ABC {
    // Хотим добиться распечатывания в виде АВСАВСАВС...
    // Все потоки синхронизируются по одному монитору. Если поток видит, что сейчас не его время, то он уходит в режим
    // ожидания по монитору mon.wait() и освобождает его. Если его - то,
    // печатает букву и будит остальные потоки mon.notifyAll().
    //
    // Таким образом решается типичная задача производитель/потребитель

    static Object mon = new Object();
    static volatile char currentLetter = 'A';

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (mon) {
                        while (currentLetter != 'A') {
                            mon.wait();
                        }
                        System.out.print("A");
                        currentLetter = 'B';
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (mon) {
                        while (currentLetter != 'B') {
                            mon.wait();
                        }
                        System.out.print("B");
                        currentLetter = 'C';
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (mon) {
                        while (currentLetter != 'C') {
                            mon.wait();
                        }
                        System.out.print("C");
                        currentLetter = 'A';
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
