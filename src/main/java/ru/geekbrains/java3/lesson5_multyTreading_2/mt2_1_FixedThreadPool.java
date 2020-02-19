package ru.geekbrains.java3.lesson5_multyTreading_2;

import java.util.concurrent.*;

public class mt2_1_FixedThreadPool {
    public static void main(String[] args) throws Exception{
        // Потоки можно создавать либо наследуюясь от Thread, либо реализовывая интерфейс Runnable.
        // Но также можно пользоваться средствами ExecutorService.
        // Например Executors.newFixedThreadPool(4) будет создавать очередь из 4 потоков, которым мы можем отдавать
        // задачи на выполнение и елси этих потоков будет нехватать, то задачи будут становиться
        // в очередь и выполняться по мере освобождения потоков. Потоки будут активны до тех пор, пока мы не вызовем shutdown.

        // Вопрос: в какой момент они создаются - в момент создания или в тот момент, когда приходит новая задача. У Executors.newFixedThreadPool() есть два конструктора: в одном просто указывается число потоков, а во втором можно передать ThreadFactory. Когда пулу потоков потребуется новый поток, он будет использовать эту фабрику для его создания.
        ExecutorService pool1 = Executors.newFixedThreadPool(4);
        pool1.shutdown();

        ExecutorService pool = Executors.newFixedThreadPool(4, new ThreadFactory() {
            // на вход приходит задача и наша цель вернуть поток, который будет выполнять эту задачу и самый простой
            // вариант вернуть новый Thread и завернуть в него r (задачу). Но можно для всех потоков, которые будут
            // создаваться в этой фабрике, задать то, что они будут являться демонами и обладать определенным приоритетом
            @Override
            public Thread newThread(Runnable r) {
                System.out.println("new thread");
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.setPriority(8);
                return t;
            }
        });
        pool.execute(() -> System.out.println("1"));
        pool.execute(() -> System.out.println("1"));
        pool.execute(() -> System.out.println("1"));

        // используется для назначения задачи. Нужно передать объект типа Runnable, который он отдаст рабочему потоку
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable");
            }
        });

        // Помимо execute() есть метод submit(), который может принимать в качестве параметров объекты типов Callable и
        // Runnable. Если execute просто выполняет задачу, то submit еще и возвращает результатю
        // Этот метод используется тогда, когда мы хотим забрать из потока какой-нибудь результат выполнения.
        // Метод submit возвращает ссылку на объект типа Future, который тоже является дженериком
        Future<String> out = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                // если метод будет выполняться долго, то главный поток программы не продолжит выполнение, пока не будет получен результат
                Thread.sleep(5000);
                return "Java";
            }
        });
//        out.cancel(); // отменить задачу, если это возможно.
        out.isCancelled(); // проверяем отменен поток или нет
        out.isDone(); // завершена задача или нет
        System.out.println(out.get());
        System.out.println("----");


        // является ли данный пул уничтоженным и без активных потоков
        // pool.isTerminated();

        // если мы хотим подождать, чтобы работу прекратил один поток, то мы вызываем join, а когда нужно дождаться завершения работы всех потоков из пула, то вызывается awaitTermination
        // pool.awaitTermination(1000, TimeUnit.SECONDS);

        // елси имеется список задач, которые нужно исполнять, то их можно передать в методы invokeAll или invokeAny
        // как только сервис находится в состояние shutdown и все потоки у него выключились, он переходим в режим terminated и уничтожается
        pool.shutdown();



        //
        // Также существует SingleThreadPool, в котором существует один рабочий поток и если ему дать несколько задач,
        // то он будет выполнять их последовательно.
        //
        // CachedThreadPool() может подготовить заранее нужное число потоков, но если их будет не хватать,
        // то он будет добавлять новые, но при этом, елси число задачь снизится, то число потоков нет,
        // т.е. он может разрастись.
    }
}
