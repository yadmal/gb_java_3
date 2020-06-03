package ru.geekbrains.java3.lesson4_multiThreding.mt3_synchronized_blocks;

public class Example_SB_1 {
    // Существует такое понятие как монитор, в качестве которого может выступать абсолютно любой объект.
    // Когда потоки пытаются выполнить метод, они смотрят занят монитор или нет, захватывают его и выполняют метод
    // Если он занят, то ждут.
    // Если у метода указан synchronized, то его монитором будет являться объек, которому принадлежит метод
    public static void main(String[] args) {
        Example_SB_1 e1 = new Example_SB_1();
        Example_SB_1 e2 = new Example_SB_1();
        new Thread(() -> e1.method1()).start(); // Одновременно два потока выполнять метод не смогут, так как синхронизация идет по объекту e1
        new Thread(() -> e1.method2()).start(); // Одновременно два потока выполнять метод не смогут, так как синхронизация идет по объекту e1

        // Параллельное выполнение, метод 3 не синхронизирован, захват монитора не проверяется
        new Thread(() -> e1.method1()).start();
        new Thread(() -> e1.method3()).start();

        // Параллельное выполнение, т.к. методы вызываются на разных объектах и каждый из них имеет свой монитор
        new Thread(() -> e1.method1()).start();
        new Thread(() -> e2.method2()).start();
    }

    public synchronized void method1() {
        System.out.println("M1");
        for (int i = 0; i < 10; i++) {
            //System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("M2");
    }

    public synchronized void method2() {
        System.out.println("M1");
        for (int i = 0; i < 10; i++) {
            //System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("M2");
    }

    public void method3() {
        System.out.println("M3");
        for (int i = 0; i < 10; i++) {
            //System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("M4");
    }
}
