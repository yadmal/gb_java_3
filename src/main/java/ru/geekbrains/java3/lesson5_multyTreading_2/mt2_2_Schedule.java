package ru.geekbrains.java3.lesson5_multyTreading_2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class mt2_2_Schedule {
    public static void main(String[] args) {
        // Если есть задача, чтобы через каждые 10 сек соединяться с сайтом и получать какую-нибудь информацию,
        // то можно запустить поток, который будет делать sleep на нужное время, потом просыпаться и снимать данные.
        // Как и обычно мы можем создать пулл на несколько потоков, но различия заключаются в вариантах исполнения. schedule
        ScheduledExecutorService serv = Executors.newScheduledThreadPool(4);
        serv.scheduleAtFixedRate(() -> {}, 5, 500,  TimeUnit.SECONDS); // задача будет выполняться какждые 5 сек, 10 сек и т.д., указанное время проходит между стартами
        serv.scheduleWithFixedDelay(() -> {}, 5, 500, TimeUnit.SECONDS); // тоже самое, только указанное время ожидается после завершения задачи, т.е. это зазор между концом одной задачи и началом следующей, время между исполнением задач
        serv.schedule(() -> {}, 500, TimeUnit.SECONDS); // запуск задачи через определенное время
    }

}
