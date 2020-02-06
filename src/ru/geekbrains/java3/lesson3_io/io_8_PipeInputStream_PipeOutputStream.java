package ru.geekbrains.java3.lesson3_io;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

//Эти классы объединяются в одно целое: если мы поместим что-то на вход, то получим это на выходе. Все что напишем в out будет поймано в in
public class io_8_PipeInputStream_PipeOutputStream {
    public static void main(String[] args) throws Exception{
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        out.write(10);
        System.out.println(in.read());
    }
}
