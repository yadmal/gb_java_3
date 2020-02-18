package ru.geekbrains.java3.lesson5_multyTreading_2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class mt2_3_SyncCollections {
    public static void main(String[] args) throws Exception{
        // Коллекции бывают синхронизированные и нет. Если обратиться к ArrayList при помощи нескольких потоков,
        // то мы можем получить ConcurrentModificationException. Безопасный аналог ArrayList это Vector.

        // Блокирующая очередь ArrayBlockingQueue<>.
        // Данная коллекция идеально подходит для решения задачи производителей и потребителей.
        // При создании указать на сколько ячеек она рассчитана. Это аналог очереди, но вытащить/положить в нее объект можно 4 разными методами.
        ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<>(10);
        abq.add("str1"); // exception если нет мест
        abq.offer("str2"); // boolean
        abq.put("str3"); // если мест нет, то переходит в режим ожидания

        abq.remove(); // exception если нет объекта
        abq.poll(); // null если нет объекта
        abq.take(); // если объекта нет, то переходит в режим ожидания


        // ConcurrentHashMap.
        // Потокобезопасный аналог HashMap. Здесь довольно хитрая реализация: если какие-то потоки захотят
        // вытаскивать из этой коллекции данные, они будут делать это параллельно, даже если этих потоков 1000 штук,
        // т.е. чтение идет без блокировок. Но если какой-то поток захочет записать данные, то будет блокироваться
        // не вся коллекция, а лишь та корзина, в которую идет запись.
        ConcurrentHashMap<String, String> chm = new ConcurrentHashMap<>();

        // Collections.synchronizedMap(.....) Отличие от ConcurrentHashMap заключается в том,
        // что ConcurrentHashMap оптимизированный вариант, а если создавать коллекцию при
        // помощи Collections.synchronizedMap, то при считывании потоком данная коллекция блокируется полностью,
        // т.е. пока какой-нибудь поток читает данные, то никто не может читать/записывать данные в коллекцию.
        Map<String, String> mm = Collections.synchronizedMap(new HashMap<>());

        // CopyOnWriteArrayList. С этой коллекцией нужно быть аккуратнее. Любое число потоков могут без проблем
        // считывать данные с этого CopyOnWriteArrayList, но если какой то поток захотел в нее что-нибудь записать,
        // то он создает копию этого списка и записывает в нее.
        // Т.е. при любой модификации CopyOnWriteArrayList создается его копия
        CopyOnWriteArrayList<String> cow = new CopyOnWriteArrayList<>();

    }
}
