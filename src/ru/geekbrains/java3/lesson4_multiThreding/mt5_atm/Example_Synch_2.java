package ru.geekbrains.java3.lesson4.mt.p5_synch_atm;

public class Example_Synch_2 {

    // Пример АТМ с синхронизацией

    public static void main(String[] args) {
        ATM atm = new ATM(100);
        User user1 = new User("#1");
        User user2 = new User("#2");
        User user3 = new User("#3");

        atm.info();
        Thread t1 = new Thread((Runnable) () -> atm.getSynchMoney(user1, 50));
        Thread t2 = new Thread((Runnable) () -> atm.getSynchMoney(user2, 50));
        Thread t3 = new Thread((Runnable) () -> atm.getSynchMoney(user3, 50));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        atm.info();
    }
}
