package ru.geekbrains.java3.lesson3_io;

public class io_2_InputStream_OutputStream {
//    Для удобства нужно разбить все классы для работы с записью/чтением на 4 группы
//
//    InputStream   |   OutputStream
//    ------------------------------
//    Reader        |     Writer
//    Если видем Stream, то работа будет происходить чисто с байтами
//    В Reader и Writer работа идет с символьным представлением
//
//    Методы InputStream
//    int read() - возвращает один прочитанный байт в виде int
//    int read(byte[] b) - читает пачками. возвращает число прочитанных байт
//    int read(byte[] b, int off, int len) - смещение показывает позицию в массиве, куда будут записываться данные
//    skip(long n) - пропустить часть файла
//    available()
//    close()


//    Такие же методы, только write есть и у OutputStream

}
