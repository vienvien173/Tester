package service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AgePriceServiceTest {

    private AgePriceService service;

    @Before
    public void setUp() {
        service = new AgePriceService();
    }

    @Test
    public void testAgeUnder6() {
        assertEquals(0, service.calculatePrice(3));
    }

    @Test
    public void testAge6to18() {
        assertEquals(50000, service.calculatePrice(10));
    }

    @Test
    public void testAdult() {
        assertEquals(100000, service.calculatePrice(30));
    }

    @Test
    public void testSenior() {
        assertEquals(70000, service.calculatePrice(65));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeAge() {
        service.calculatePrice(-1);
    }
}
