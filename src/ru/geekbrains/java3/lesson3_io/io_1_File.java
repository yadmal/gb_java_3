package ru.geekbrains.java3.lesson3_io;

import java.io.File;

public class io_1_File {
    File file = new File("src/ru/geekbrains/java3/lesson3_io/1.txt"); // описывает путь к файлу, файла может не быть
//        System.out.println(file.getName());
//        file.mkdir();  - если в пути на конце будет указана папка, то она создастся
//        file.mkdirs();  - создадутся все папки по указанному пути



//        file.list(); - если путь указывает на каталог, то вернет список файлов в нем. Так же есть версия с фильтром

//        String[] files = file.list();
//        for (String str : files){
//            System.out.println(str);
//        }

//        String[] files = file.list(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.startsWith("1");
//            }
//        });
//        for (String str : files){
//            System.out.println(str);
//        }

//        file.createNewFile();
//        file.delete();
//        file.deleteOnExit();  - файл помечается и удаляется при завершении программы
//        file.exists(); - существует ли
//        file.isDirectory();
//        file.isFile();
//        file.renameTo(); - переименование/перенос файла
}
