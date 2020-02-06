package ru.geekbrains.java3.lesson3_io;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;

// Позволяет создать несколько потоков чтения файлов и объеденить их в один.
public class io_9_SequenceInputStream {
    public static void main(String[] args) throws Exception{
        ArrayList<InputStream> ali = new ArrayList<>();
        ali.add(new FileInputStream("1.txt"));
        ali.add(new FileInputStream("2.txt"));
        ali.add(new FileInputStream("3.txt"));
        ali.add(new FileInputStream("4.txt"));
        ali.add(new FileInputStream("5.txt"));

        SequenceInputStream sis = new SequenceInputStream(Collections.enumeration(ali));
        int x;
        while ((x = sis.read()) != -1){
            System.out.println((char)x);
        }
        sis.close();
    }
}
