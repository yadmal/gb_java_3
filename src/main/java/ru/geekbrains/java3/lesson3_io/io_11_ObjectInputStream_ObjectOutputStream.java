package ru.geekbrains.java3.lesson3_io;

import java.io.*;

//Предположим стоит задача сохранения объекта какого-нибудь класса.
// Можно устроить перебор полей объекта и записывать их в файл.
// Сереализация же позволяет удобно записывать объекты в виде набора байтов.
public class io_11_ObjectInputStream_ObjectOutputStream {
    public static void main(String[] args) throws Exception{



        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("stud.ser"));
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("stud.ser"));
        Student s1 = new Student(1, "Ivan");
        Student s2 = new Student(2, "Bob");

        Book jungleBook = new Book("Jungle book");

        s1.book = jungleBook;
        s2.book = jungleBook;

        // Когда проходит сереализация, то записанные объекты помечаются и не записываются второй раз,
        // поэтому книга будет записана один раз и оба s1 s2 будут ссылаться на один и тот же объект.
        // Чтобы снять метки нужно использовать функцию reset
        s1.book = jungleBook;
        out.writeObject(s1);
        out.reset();
        jungleBook.title = "Jungle book Part 2";
        out.writeObject(s2);
        out.close();

        Student s1o = (Student) in.readObject();
        Student s2o = (Student) in.readObject();
        in.close();
        System.out.println(s1o.book.title);
        System.out.println(s2o.book.title);



        // При чтении (десереализации) объекта из файла его конструктор не вызывается.

    }

//    Чтобы иметь возможность сереализации объектов, класс должен подключить интерфейс Serializable (в нем нет методов, просто маркер)
    // Если какой-то класс наследуется от другого класса не помеченого как Serializable, то у этого родительского класса
    // обязательно должен быть конструктор по умолчанию (дефолтный, без параметров), потому что он будет вызван при
    // восстановлении объекта. Если же родитель тоже сереализуем, то таких ограничений нет

    static class Student extends Human implements Serializable {
        int id;
        String name;
        // если класс содержит поле в виде другого класса, то этот класс тоже должен быть сереализован
        Book book;
//        transient Book book;  -  в том случае, если мы не хотим сереализовать поле (пароль, соединение с бд и т.д.)


        public Student(int id, String name) {
//            System.out.println("Stud constr");
            this.id = id;
            this.name = name;
        }

        public void info(){
            System.out.println(this.name + " " + this.id);
        }
    }

    static class Human {
        public Human(){
//            System.out.println("Default human constr");
        }

        public Human(int i){
            System.out.println(i);
        }
    }

    static class Book implements Serializable{
        String title;

        public Book(String title) {
            this.title = title;
        }
    }
}
