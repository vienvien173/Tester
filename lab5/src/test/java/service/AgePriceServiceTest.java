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

    // ===== TC01: Child, Age = 5 =====
    @Test
    public void test_TC01_Child_Age5() {
        int payment = service.calculatePrice(5, Gender.CHILD);
        assertEquals(50, payment);
    }

    // ===== TC02: Male, Age = 20 =====
    @Test
    public void test_TC02_Male_Age20() {
        int payment = service.calculatePrice(20, Gender.MALE);
        assertEquals(100, payment);
    }

    // ===== TC03: Male, Age = 40 =====
    @Test
    public void test_TC03_Male_Age40() {
        int payment = service.calculatePrice(40, Gender.MALE);
        assertEquals(120, payment);
    }

    // ===== TC04: Male, Age = 70 =====
    @Test
    public void test_TC04_Male_Age70() {
        int payment = service.calculatePrice(70, Gender.MALE);
        assertEquals(140, payment);
    }

    // ===== TC05: Female, Age = 25 =====
    @Test
    public void test_TC05_Female_Age25() {
        int payment = service.calculatePrice(25, Gender.FEMALE);
        assertEquals(80, payment);
    }

    // ===== TC06: Female, Age = 45 =====
    @Test
    public void test_TC06_Female_Age45() {
        int payment = service.calculatePrice(45, Gender.FEMALE);
        assertEquals(110, payment);
    }

    // ===== TC07: Female, Age = 60 =====
    @Test
    public void test_TC07_Female_Age60() {
        int payment = service.calculatePrice(60, Gender.FEMALE);
        assertEquals(140, payment);
    }

    // ===== TC08: Child, Age = 17 (biên trên) =====
    @Test
    public void test_TC08_Child_Age17() {
        int payment = service.calculatePrice(17, Gender.CHILD);
        assertEquals(50, payment);
    }

    // ===== TC09: Child, Age = 18 (không hợp lệ) =====
    @Test(expected = IllegalArgumentException.class)
    public void test_TC09_Child_Age18_Error() {
        service.calculatePrice(18, Gender.CHILD);
    }

    // ===== TC10: Male, Age = -1 (lỗi) =====
    @Test(expected = IllegalArgumentException.class)
    public void test_TC10_Male_NegativeAge_Error() {
        service.calculatePrice(-1, Gender.MALE);
    }
}
