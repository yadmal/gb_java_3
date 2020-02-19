import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.geekbrains.java3.lesson6_logging_testing.testing.hwLess6_1;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestHW {
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList( new Object[][]{
                {new int[]{1,2,3}, new int[]{1,2,3,5,6,4,1,2,3}},
                {new int[]{2,3}, new int[]{8,7,6,9,2,3,4,1,5,6,4,2,3}},
                {new int[]{3,5}, new int[]{25,4,8,9,6,7,4,2,5,4,3,5}},
                {new int[]{0}, new int[]{25,4,8,9,6,7,4,2,5,4,3,4,5}}
        });
    }

    private hwLess6_1 t;
    private int[] a;
    private int[] b;

    public TestHW(int[] a, int[] b){
        this.a = a;
        this.b = b;
    }

    @Before
    public void init(){
        t = new hwLess6_1();
    }

    @Test
    public void testTask(){
        Assert.assertTrue("arrays are not equals", Arrays.equals(a, t.doTask(b)));
    }
}
