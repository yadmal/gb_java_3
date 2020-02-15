package ru.geekbrains.java3.lesson4_multiThreding.mt3_blocks;

public class Example_SB_5_Integer {
    // В данном примере в качестве монитора используется имутабельный объект Integer, а это означает,
    // что в случае изменения его значения будет создан новый объект. Используя данный объект в качестве монитора,
    // мы хотели добиться последовательного выполнения, но если внутри потоков изменять значения Integer,
    // то мы получим параллельное выполение, т.к. будут создаваться новые объекты и для каждого потока получим
    // свой монитор, вместо одного на все потоки

    private static Integer n = new Integer(300);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (n) {
                    System.out.println("X");
//                    System.out.println(n);
                    n++;
//                    System.out.println(n);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (n) {
                    System.out.println("X");
//                    System.out.println(n);
                    n++;
//                    System.out.println(n);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (n) {
                    System.out.println("X");
//                    System.out.println(n);
                    n++;
//                    System.out.println(n);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
