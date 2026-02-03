import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AirthematicTest {

    @Test
    public void testDivideNormal() {
        JunitMessage jm = new JunitMessage();
        int result = jm.divide(10, 2);
        assertEquals(5, result);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        JunitMessage jm = new JunitMessage();
        jm.divide(10, 0);
    }
}
