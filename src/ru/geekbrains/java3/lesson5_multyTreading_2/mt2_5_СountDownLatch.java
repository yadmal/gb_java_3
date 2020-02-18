package ru.geekbrains.java3.lesson5_multyTreading_2;

import java.util.concurrent.CountDownLatch;

public class mt2_5_СountDownLatch {
    public static void main(String[] args) throws Exception{
        // Если имеется множество потоков, раскиданных по программе и нам нужно их все заджоинить.
        // Применяются так называемые защелки. В конструкторе указывают число щелчков и для их подсчета
        // используется метод cdl.countDown(). В том месте, где мы ожидаем завершения выполнения всех потоков
        // вызывается метод cdl.await() который скажет потоку дождаться выполения всех потоков (когда счетчик засчелок
        // будет равен 0, на всякий случай можно поставить таймаут, чтобы не ждать вечно),
        // которые считаются при помощи cdl.countDown() - значение защелки уменьшается на 1. Нет возможности выставить
        // состояние счетчика в исходное положение, чтобы он стал считать с указанного числа - нужно перезапустить программу.
        CountDownLatch cdl = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            int w = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(w + " - 1");
                    try {
                        Thread.sleep((int) Math.random() * 5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(w + " - 2");
                    cdl.countDown(); //<************
                }
            }).start();
        }
        System.out.println("Before end");
        cdl.await();  //<************
        System.out.println("end");
    }
}
