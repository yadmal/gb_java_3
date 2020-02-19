package ru.geekbrains.java3.lesson3_io;

import java.io.RandomAccessFile;

//Позволяте открыть файл в режиме случайного доступа для того, чтобы прочитать/записать данные с любого места.
// В других классах чтения тоже можно пропускать часть текста при помощи функции skip(long len),
// но в RandomAccessFile при помощи функции seek(long len) можно возвращаться назад
public class io_10_RandomAccessFile {
    public static void main(String[] args) throws Exception{
        RandomAccessFile raf = new RandomAccessFile("1.txt", "rw");
        raf.seek(120); // указываем позицию
        raf.read();
    }
}
