package ru.geekbrains.java3.lesson3_io;

import java.io.ByteArrayInputStream;

//Чтение и запись в байтовый массив
public class io_7_ByteArrayInputStream_ByteArrayOutputStream {
    public static void main(String[] args) {
        byte[] arr = {1, 2, 3, 4, 5, 6};
        ByteArrayInputStream bais = new ByteArrayInputStream(arr);
        int x;
        while ((x = bais.read()) != -1){
            System.out.println(x);
        }
    }
}
