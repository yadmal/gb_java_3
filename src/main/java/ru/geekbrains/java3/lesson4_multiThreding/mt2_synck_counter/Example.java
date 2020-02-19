package ru.geekbrains.java3.lesson4_multiThreding.mt2_synck_counter;

public class Example {
    public static void main(String[] args) {
        // Проблема заключается в том, что у нас есть два потока, которые пытаются поработать с одной ячейкой памяти.
        // Для того, чтобы выполнять какие-либо операции с данными, всегда производятся два действия: сначала поток
        // берет данные из ячейки и смотрит что в ней лежит, изменяет значение и возвращает его обратно в ячейку памяти.
        // Но если появляется больше одного потока, то до момента возращения первым потоком нового значения, старое
        // может считать другой поток. В итоге первый поток запишет новое значение, а второй произведет рассчет на
        // основе устаревших данных и запишет неправильный результат поверх того, что записано первым потоком.
        Counter counter = new Counter();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++){
                    counter.inc();
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++){
                    counter.dec();
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        try {
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.value());
    }
}
