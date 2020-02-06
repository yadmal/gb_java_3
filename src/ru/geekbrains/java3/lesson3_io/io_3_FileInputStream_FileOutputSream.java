package ru.geekbrains.java3.lesson3_io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class io_3_FileInputStream_FileOutputSream {
    public static void main(String[] args) {
//       ПОБАЙТОВОЕ ЧТЕНИЕ
//       Если в файле будут любые символы кроме латиницы, то они будут отображаться некорректно.
//       FileInputStream работает корректно с латиницей
//        try(FileInputStream in = new FileInputStream("src/ru/geekbrains/java3/lesson3_io/1.txt")) {
//            int x;
//            while((x = in.read()) != -1){
//                System.out.print((char)x);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ----------------------------------------------------------------
//        ЧТЕНИЕ БАЙТОВЫМ МАССИВОМ
//        русские символы при этом выводятся.
//        Кусками читать быстрее, чем побайтово
        try(FileInputStream in = new FileInputStream("src/ru/geekbrains/java3/lesson3_io/1.txt")) {
            byte[] arr = new byte[512];
            int n;
            while((n = in.read(arr)) > 0){
                //Ести не указать число символов, то могут появиться пустые
                //символы в конце строки из-за неполного массива arr
                System.out.print(new String(arr, 0, n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
