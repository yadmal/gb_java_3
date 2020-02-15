package ru.geekbrains.java3.lesson4_multiThreding.mt4_synch_double_lock;

public class SynchDoubleCounter {
    // В данном примере мы хотим добиться того, чтобы две переменные были потокобезопасными, но при этом не хотим,
    // чтобы при работе с одной из них работа с другой становилась невозможна. Если у методов прописать
    // "public synchronized void method(){}", монитором будет выступать объект и тогда одновременная работу
    // с двумя переменными станет невозможна, т.к. монитор один.
    // Для этого для каждой перемпенной создается по отдельному монитору,
    // который будет использоваться в методах в блоках synchronized(object){} для получения/установки значения переменной.
    private long c1 = 0;
    private long c2 = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public long value1() { return c1; }

    public long value2() { return c2; }

    public void inc1() {
        synchronized (lock1) {
            c1++;
        }
    }

    public void inc2() {
        synchronized (lock2) {
            c2++;
        }
    }

    public void dec1() {
        synchronized (lock1) {
            c1--;
        }
    }

    public void dec2() {
        synchronized (lock2) {
            c2--;
        }
    }
}
