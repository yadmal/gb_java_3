package ru.geekbrains.java3.lesson2_db;

import java.sql.*;

public class MainClassDB {
//    5 Для открытия соединения с БД
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement psInsert;

    public static void main(String[] args) {
        try {
            connect();
            clearTableEx();
            transactionEx();
            batchEx();
            preparedStmtEx();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }
//        1 Скачать SQLiteStudio, создать БД и сохранить в корень проекта
//        2 Скачать JDBC драйвер с maven в виде JAR библиотеки и скопировать в проект
//        3 Добавить библиотеку в проект Файд-Структура проекта-Библиотеки-Добавить библиотеку и указать скачанный jar
//        4 Подключаемся к базе
    public static void connect() throws Exception{
        // загружает класс с таким именем в память.
        // В этом классе статический блок инициализации который регистрирует драйвер в драйвер менеджере.
        // В новых версиях эти действия не нужны, о способах подключения читать на сайте или в документации
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
        stmt = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void selectEx() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM students;");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString(2) + " " + rs.getInt(3));
        }
    }

    // Отличие executeUpdate от executeQuery заключается в том, что executeQuery возвращает ResultSet и мы можем прочитать данные,
    // а executeUpdate вернет int, которое покажет сколько строк было изменено.
    // executeUpdate принято вызывать каждый раз, когда меняются записи: удаление, добавление, изменение
    private static void insertEx() throws SQLException {
        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob4', 100);");
    }

    private static void updateEx() throws SQLException {
        stmt.executeUpdate("UPDATE students SET score = 90 WHERE id = 1;");
    }

    private static void deleteOneEntryEx() throws SQLException {
        stmt.executeUpdate("DELETE FROM students WHERE id = 5;");
    }

    // Очистка всей таблицы
    // Для удаления старого мусора и сжатия файла нужно использовать команду VACUUM;
    private static void clearTableEx() throws SQLException {
        stmt.executeUpdate("DELETE FROM students;");
    }

    private static void dropTableEx() throws SQLException {
        stmt.executeUpdate("DROP TABLE IF EXISTS students;");
    }

    private static void createTableEx() throws SQLException {
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS students (\n" +
                "    id    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name  TEXT,\n" +
                "    score INTEGER\n" +
                ");");
    }

    // По умолчанию, когда мы открываем соединение с базой, у нас стоит autoCommit, т.е. посылали запрос и подтверждали его каждый раз.
    // При транзакциях посылается множество запросов и подтверждается один раз.
    // Если при транзакции хотя бы один из запросов будет не выполнен, то все изменения отменятся
    private static void transactionEx() throws SQLException {
        connection.setAutoCommit(false); //
        long t = System.currentTimeMillis();
        for (int i = 0; i < 400000; i++) {
            stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('unknown', 100);");
        }
        System.out.println(System.currentTimeMillis() - t);
        connection.setAutoCommit(true); //
    }

    // Запросы накапливаются в пакет. Если при executeUpdate("sql") каждый запрос летит в БД, т.е. по сети постоянно гоняются данные,
    // то при addBatch составляется пакет запросов и затем весь этот пакет отсылается в БД
    private static void batchEx() throws SQLException {
        connection.setAutoCommit(false);
        long t = System.currentTimeMillis();
        for (int i = 0; i < 400000; i++) {
            stmt.addBatch("INSERT INTO students (name, score) VALUES ('unknown', 100);");
        }
        stmt.executeBatch();
        connection.setAutoCommit(true);
        System.out.println(System.currentTimeMillis() - t);
    }

    //
    private static void preparedStmtEx() throws SQLException {
        long t = System.currentTimeMillis();
        connection.setAutoCommit(false);
        psInsert = connection.prepareStatement("INSERT INTO students (name, score) VALUES (?, ?);");
        for (int i = 0; i < 400000; i++) {
            psInsert.setString(1, "Bob" + (i + 1));
            psInsert.setInt(2, 20 + (i * 10) % 90);
            psInsert.addBatch();
        }
        psInsert.executeBatch();
        connection.setAutoCommit(true);
        System.out.println(System.currentTimeMillis() - t);
    }

    private static void rollbackEx() throws SQLException {
        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob1', 10);");
        // БД сохраняет свое состояние, при этом setAutoCommit равен false,
        // дальнейший код не будет отправляться, пока не включим autoCommit, при этом Bob1 уйдет в БД
        Savepoint sp1 = connection.setSavepoint();
        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob2', 20);");
        connection.rollback(sp1);
        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob3', 30);");
        connection.setAutoCommit(true); // подключаем ранее отключенный commit и изменения улетают в базе
    }
}
