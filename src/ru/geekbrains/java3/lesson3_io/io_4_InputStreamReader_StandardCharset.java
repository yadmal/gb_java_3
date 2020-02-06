package ru.geekbrains.java3.lesson3_io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class io_4_InputStreamReader_StandardCharset {
    public static void main(String[] args) {
        // При работе с InputStreamReader в его конструктор нужно отдать какой-нибудь входящий поток
        try(InputStreamReader isr = new InputStreamReader(new FileInputStream("src/ru/geekbrains/java3/lesson3_io/1.txt"), "UTF-8")){
            int x;
            // чтение одного байта в int и т.к. в него помещается до 4байт,
            // то за один раз в него может поместиться символ из 2 байт(такой как русские буквы)
            while ((x = isr.read()) != -1){
                System.out.print((char)x);
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
