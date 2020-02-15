package ru.geekbrains.java3.lesson4_multiThreding.mt7_interrupt;

public class InterruptThread {
    public static void main(String[] args) {
        // Нельзя останавливать потоки при помощи метода stop(). Для этих целей существует метод interrupt.
        // Если во время выполнения у потока вызвать метод t1.interrupt(), то он установит соответствующий
        // флаг в положение true, а проверить это можно при помощи метода Thread.currentThread.isInterrupted().
        // Но останавливать выполение потока нужно немного иначе. Механизм таков, что если в то время, пока поток
        // спит кто-нибудь вызовет метод interrupt, то будет выброжено исключение. Его то мы и должны
        // поймать (и в рамках нашего примера выйти из бесконечного цикла while(true)).
        // В пулах потоков, которые реализуются при помощи ExecutorServer, для остановки потоков есть
        // методы shutdown и shutdownNow. shutdown - просто перестает принимать заказы, а shutdownNow
        // дополнительно вызывает метод interrupt, и если при написании кода была реализована логика завершения
        // потока с перехватом исключения и завершения работы, то потоки остановятся практически мгновенно.
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (Thread.currentThread().isInterrupted()){
                        break;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                        break;
                    }
                    System.out.println("A");
                }
            }
        });
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();
//        t.stop();// так останавливать поток нельзя, потому что он может не завершить операцию
    }
}
