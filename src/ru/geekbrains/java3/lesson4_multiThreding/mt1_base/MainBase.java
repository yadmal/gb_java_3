package ru.geekbrains.java3.lesson4_multiThreding.mt1_base;

public class MainBase {
    public static void main(String[] args) {
        // Создаем объект класса Thread и запускаем поток
        ThreadExampleClass t1 = new ThreadExampleClass();
        t1.start();

        // Создаем объект Thread на основе класса, реализующего интерфейс Runnable
        Thread t2 = new Thread(new RunnableExempleClass());
        t2.start();

        System.out.println("Begin");
        try {
            // Главный поток программы main переходит в режим ожидания (режим ожидания завершения потока), пока не дождется завершения t1, т.е.
            t1.join();
            System.out.println("t1 end");
            t2.join();
            System.out.println("t2 end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End");
    }
}
