package ru.geekbrains.java3.lesson4_multiThreding.mt3_blocks;

public class Example_SB_2 {

    // В роли монитора может выступать абсолютно любой внешний объект.
    // В этом случае уже используется synchronized блок
    private Object lock1 = new Object();

    public static void main(String[] args) {
        Example_SB_2 e2 = new Example_SB_2();
        new Thread(() -> e2.method1()).start();
        new Thread(() -> e2.method1()).start();
        new Thread(() -> e2.method1()).start();
    }

    // данный метод вызывается тремя потоками одновременно. Внутри него есть блок синхронизации,
    // который привязан к объекту-монитору и одновременно выполнять его может только один поток.
    // Соответственно, часть кода до блока будет выполнена потоками параллельно, а блок - последовательно каждым потоком.
    public void method1() {
        try {
            System.out.println("NonSynch-Begin " + Thread.currentThread().getName());
            for (int i = 0; i < 3; i++) {
                System.out.println('.');
                Thread.sleep(200);
            }
            System.out.println("NonSynch-End " + Thread.currentThread().getName());
            synchronized (lock1) {
                System.out.println("Synch-Begin " + Thread.currentThread().getName());
                for (int i = 0; i < 5; i++) {
                    System.out.println('.');
                    Thread.sleep(1000);
                }
                System.out.println("Synch-End " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
