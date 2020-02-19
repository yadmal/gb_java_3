import org.junit.*;
import ru.geekbrains.java3.lesson6_logging_testing.testing.Calculator;

public class Test1 {
    Calculator calc;

//    @BeforeClass // подготовительная работа перед классом Test1
//    public void initClass(){
//        System.out.println("initClass");
//    }

//    @AfterClass // выполняется после класса Test1
//    public void shutdown(){
//        System.out.println("endClass");
//    }

    @Before // подготовительная работа перед каждым тестом, чтобы не выполнять эти действия в каждой проверке
    public void initTest(){
        System.out.println("initTest");
        calc = new Calculator();
    }

    @After // выполняется после каждого теста
    public void shutdownTest(){
        System.out.println("endTest");
    }



    // Класс Assert предоставляет различные проверки
    // Assert.assertEquals(long expected, long actual) ожидает в качестве параметров два числа,
    // ожидаемое и реальное, которое выдал калькулятор
    // Если значения совпадут, то тест будет пройден, иначе нет
    // Разновидности методов класса Assert:
//        Assert.assertEquals();
//        Assert.assertArrayEquals(); // сравнивает массивы по длине и содержимому
//        Assert.assertFalse(String message, boolean condition); // ожидает возвращения false и какое сообщение будет показано в случае провала теста
//        Assert.assertFalse(boolean condition); // ожидает возвращения false
//        Assert.assertTrue(boolean condition);
//        Assert.assertNotEquals(); // проверка на неравенство элементов
//        Assert.assertNotNull(); // существует ли ссылка
//        Assert.assertNotSame(); // ссылка одна и та же или разная
//        Assert.assertSame(); // две ссылки равны
//        Assert.fail(); // самостоятельно провалить тест


    // Если тело теста содержит несколько проверок и две из них (или больше) провалятся,
    // то мы узнаем только об одной, о первой найденой ошибке. Лучше делать несколько мелких тестов
    @Test
    public void test1(){
        Assert.assertEquals(4, calc.add(2,2));
    }

    @Test
    public void test2(){
        Assert.assertEquals(8, calc.add(4,4));
    }

    @Test
    public void test3(){
        Assert.assertEquals(8, calc.add(4,4));
    }

    // ожидаем появления исключения и если оно будет, то тест будет пройден,
    // а если нет, то будет провален
    @Test(expected = ArithmeticException.class)
    public void test4(){
        calc.div(10,0);
    }

    // ожидаем прохождения теста за указанное время. Если превысим, то тест провалится
    @Test(timeout = 100)
    @Ignore
    public void test5(){
        calc.div(10,0);
    }

    @Test
    @Ignore(value = "не нужно тратить время") // позволяет пропустить указанный тест
    public void test6(){
        calc.div(10,1);
    }

}
