package ru.geekbrains.java3.lesson5_multyTreading_2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class mt2_4_Semaphore {
    public static void main(String[] args) {
        // Когда в программе имеется множество потоков и они раскиданы непонятно где, нам иногда нужно их
        // засинхронизировать. Предположим у нас имеется несколько потоков, которые пытаются записать данные
        // на флэшку. Система будет постоянно переключаться между потоками, чтобы они могли записывать данные.
        // При большом числе потоков эффективность снизится. Т.е. иногда нужно ограничивать количество потоков,
        // которые могут обращаться к ресурсу. При помощи wait notify это сложно сделать, придется ставить счетчики,
        // отслеживать. Есть более удобный способ - semaphore. В момент его создания мы указываем, сколько доступов
        // он имеет. Для того, чтобы выделить участок кода, к которому ограничен доступ семафором используется метод
        // smp.acquire() (аналог блока синхронизации), т.е. когда поток захватывает семафор, то количество доступов
        // уменьшается на 1. Пока уровень числа доступов будет равен 0, то никакой поток не сможет захватить семафор.
        // Для того, чтобы отпустить семафор - smp.release()

        ExecutorService serv = Executors.newFixedThreadPool(10);
        Semaphore smp = new Semaphore(4);
        for (int i = 0; i < 10; i++) {
            serv.execute(() -> {
                try {
                    smp.acquire(); //<************
                    System.out.println("1");
                    Thread.sleep(2000);
                    System.out.println("2");  //<************
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    smp.release();
                }
            });
        }
        serv.shutdown();
    }
}
