import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.geekbrains.java3.lesson6_logging_testing.testing.hwLess6_2;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestHW2 {
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList( new Object[][]{
                {true, new int[]{1,4,1,4,1,4,4,4,1,4,1,1,1}},
                {true, new int[]{4,4,4,4,4,4,1,4,1,4}},
                {true, new int[]{1,1,1,1,1,1,4}},
                {true, new int[]{4,4,4,4,4,1}}

        });
    }

    private hwLess6_2 t;
    private boolean a;
    private int[] b;

    public TestHW2(boolean a, int[] b){
        this.a = a;
        this.b = b;
    }

    @Before
    public void init(){
        t = new hwLess6_2();
    }

    @Test
    public void testTask(){
        Assert.assertEquals(a, t.doTask(b));
    }
}
