package ru.geekbrains.java3.lesson5_multyTreading_2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class mt2_6_CyclicBarrier {
    public static void main(String[] args) {
        // бывают ситуации, когда у нас есть несколько модулей и мы грузим их параллельно, но хотим,
        // чтобы они запустились после загрузки всех модулей. В данном примере попробуем добиться того, чтобы все
        // потоки одновременно отпечатали слово "end". Для этой цели будем использовать CyclicBarrier.
        // В конструкторе можно указать на сколько потоков он расчитан. Когда потоки доходят до cd.await() и
        // вызывают его, то они переходят в режим ожидания. Когда последний поток дойдет до cd.await() он сбросит
        // значение барьера в 0, все потоки которые находились по данному await отпускаются и идут дальше.
        // Стоит обратить внимание на слово Cyclic в названии: если счетчик сброшен и после этого снова будет
        // вызван await, то все отсчет начнется сначала.
        CyclicBarrier cb = new CyclicBarrier(10);
        for (int i = 0; i < 10; i++) {
            int w = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(w + " - start");
                    try {
                        Thread.sleep((int) (Math.random() * 5000));
                        System.out.println(w + " - ready");
                        cb.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(w + " - end");
                }
            }).start();
        }

    }
}
