package ru.geekbrains.java3.lesson5_multyTreading_2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class mt2_7_Locks {
    public static void main(String[] args) {
        // ***  ReentrantLock  ***

        // если какой-то поток подходит к блоки synchronized, то ему некуда деваться и он останавливается в этом месте
        // до тех пор, пока не освободится монитор. Блок synchronized нельзя обойти. Но есть так называемые замки,
        // у которых есть методы lock и unlock. Они равносильны входу и выходу из блока синхронизации.
        // Особенностью данного способа является то, что в отличии от блока синхронизации, который мы никак не можем
        // разорвать и использовать одновременно в двух местах, тут можно захватить замок в одном месте и освободить
        // в совершенно любом другом. Хорошей практикой считается размещение освобождение замка в блоке finally.
        ReentrantLock rl = new ReentrantLock();
/*
            new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    rl.lock();
                    System.out.println("1");
                    Thread.sleep(2000);
                    System.out.println("2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    rl.unlock();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    rl.lock();
                    System.out.println("3");
                    Thread.sleep(2000);
                    System.out.println("4");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    rl.unlock();
                }
            }
        }).start();
*/

        // Помимо указанных отличий от блоков синхронизации у этой конструкции есть метод tryLock. Это попытка засинхронинзироваться. Если замок свободен - то мы его занимаем и возвращаем true, иначе  вернется false. Также можно установить таймаут, что является своеобразной защитой от дэдЛока
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (rl.tryLock()) { // <***********************
                    System.out.println("In first tryLock");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("End first tryLock");
                    rl.unlock(); // <***********************
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (rl.tryLock(1800, TimeUnit.MILLISECONDS)) {// <***********************
                        System.out.println("In second tryLock");
                        Thread.sleep(1500);
                        System.out.println("End second tryLock");
                        rl.unlock();// <***********************
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // *** ReentrantReadWriteLock ***
        // Внутри данного замка живет несколько замком. Это readLock        //
        // readLock.lock()
        // readLock.unlock()
        //
        // и writeLock
        // writeLock.lock()
        // writeLock.unlock()
        // Мы пользуемся только этими двумя замками - захватываем их или освобождаем.
        // Несколько потоков могут безпрепятственно считывать данные. Если в момент чтения данных какой-то поток
        // решит записать данные, то он будет остановлен до тех пор, пока потоки в очереди ранее не завершат чтение.
        // Если ситуация обратная, что какой-то поток записывае данные, то никакой другой поток не сможет их читать до
        // тех пор, пока запись не прекратится. Если в очереди два потока на запись, то в момент времени
        // может записывать только один поток.
        ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    }
}
