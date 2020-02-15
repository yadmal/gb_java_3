package ru.geekbrains.java3.lesson4_multiThreding.mt3_blocks;

public class Example_SB_3 {

    public static void main(String[] args) {
        Example_SB_3 e = new Example_SB_3();
        System.out.println("Start");
        // Два этих потока будут испоняться параллельно, потому что у каждого потока свой монитор - в первом случае это класс,
        // т.к. данный метод static synchronized, а во втором - объект, т.к. метод синхронизирован, но не статичен
        new Thread(() -> method()).start();
        new Thread(() -> e.abc()).start();
    }

    // Если синхронизация установлена на статический метод, то в качестве монитора выступает класс
    public synchronized static void method() { // синхронизация по классу
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void abc() {

    }

    public static void method2() { // синхронизация по классу
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
