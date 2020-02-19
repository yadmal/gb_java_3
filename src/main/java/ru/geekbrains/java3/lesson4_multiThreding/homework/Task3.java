package ru.geekbrains.java3.lesson4_multiThreding.homework;

public class Task3 {
    static class MFU{
        private Object printLock = new Object();
        private Object scanLock = new Object();

        public void print(String doc, int n){
            synchronized (printLock){
                System.out.println("Начало печати " + doc);
                for (int i = 0; i < n; i++){
                    System.out.println("Печатается страница " + i);
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Конец печати");
            }
        }

        public void scan(String doc, int n){
            synchronized (scanLock){
                System.out.println("Начало сканирования " + doc);
                for (int i = 0; i < n; i++){
                    System.out.println("Сканируется страница " + i);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Конец сканирования");
            }
        }
    }

    public static void main(String[] args) {
        MFU mfu = new MFU();
        new Thread(() -> {
            mfu.scan("IMG", 5);
        }).start();
        new Thread(() -> {
            mfu.print("TEXT", 5);
        }).start();

    }
}
