package ru.geekbrains.java3.lesson4_multiThreding.mt4_synch_double_lock;

public class MainDoubleSynch {
    public static void main(String[] args) {
        SynchDoubleCounter sdc = new SynchDoubleCounter();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sdc.inc1();
                System.out.println("sdc.value1: " + sdc.value1());
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sdc.dec1();
                System.out.println("sdc.value1: " + sdc.value1());
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sdc.inc2();
                System.out.println("sdc.value2: " + sdc.value2());
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sdc.dec2();
                System.out.println("sdc.value2: " + sdc.value2());
            }
        }).start();
    }
}
