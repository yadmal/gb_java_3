package ru.geekbrains.java3.lesson4_multiThreding.homework;

public class Task1 {
    static volatile char currentSymbol = 'A';
    static Object monitor = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor){
                    try {
                        for (int i = 0; i < 5; i++){
                            while (currentSymbol != 'A'){
                                monitor.wait();
                            }
                            System.out.print('A');
                            currentSymbol = 'B';
                            monitor.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor){
                    try {
                        for (int i = 0; i < 5; i++){
                            while (currentSymbol != 'B'){
                                monitor.wait();
                            }
                            System.out.print('B');
                            currentSymbol = 'C';
                            monitor.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor){
                    try {
                        for (int i = 0; i < 5; i++){
                            while (currentSymbol != 'C'){
                                monitor.wait();
                            }
                            System.out.print('C');
                            currentSymbol = 'A';
                            monitor.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
