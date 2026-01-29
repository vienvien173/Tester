package service;

import dao.CustomerDAO;
import model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerValidatorTest {

    CustomerValidator validator = new CustomerValidator();
    CustomerDAO dao = new CustomerDAO();

    // ===== Customer hợp lệ dùng cho test =====
    private Customer validCustomer() {
        return new Customer(
                "KH9999",
                "Nguyễn Văn Test",
                "test9999@email.com",
                "0123456789",
                "Hà Nội",
                "12345678",
                "12345678",
                LocalDate.of(2000, 1, 1),
                "Nam",
                true
        );
    }

    // ===== Dọn dữ liệu test trước mỗi test case =====
    @BeforeEach
    void setup() throws Exception {
        dao.deleteByCustomerId("KH9999");
        dao.deleteByCustomerId("KH8888");
        dao.deleteByEmail("test9999@email.com");
    }


    // ================= TEST CASE =================

    // TC01: Đăng ký hợp lệ
    @Test
    void TC01_validRegister() throws Exception {
        assertEquals("OK", validator.validate(validCustomer()));
    }

    // TC02: Mã KH rỗng
    @Test
    void TC02_emptyCustomerId() throws Exception {
        Customer c = validCustomer();
        c.setCustomerId("");
        assertNotEquals("OK", validator.validate(c));
    }

    // TC03: Mã KH quá ngắn
    @Test
    void TC03_customerIdTooShort() throws Exception {
        Customer c = validCustomer();
        c.setCustomerId("KH1");
        assertNotEquals("OK", validator.validate(c));
    }

    // TC04: Mã KH chứa ký tự đặc biệt
    @Test
    void TC04_customerIdSpecialChar() throws Exception {
        Customer c = validCustomer();
        c.setCustomerId("KH@123");
        assertNotEquals("OK", validator.validate(c));
    }

    // TC05: Họ tên quá ngắn
    @Test
    void TC05_fullNameTooShort() throws Exception {
        Customer c = validCustomer();
        c.setFullName("An");
        assertNotEquals("OK", validator.validate(c));
    }

    // TC06: Email sai định dạng
    @Test
    void TC06_invalidEmail() throws Exception {
        Customer c = validCustomer();
        c.setEmail("abc.com");
        assertNotEquals("OK", validator.validate(c));
    }

    // TC07: SĐT không hợp lệ
    @Test
    void TC07_invalidPhone() throws Exception {
        Customer c = validCustomer();
        c.setPhone("912345678");
        assertNotEquals("OK", validator.validate(c));
    }

    // TC08: Mật khẩu quá ngắn
    @Test
    void TC08_passwordTooShort() throws Exception {
        Customer c = validCustomer();
        c.setPassword("123456");
        c.setConfirmPassword("123456");
        assertNotEquals("OK", validator.validate(c));
    }

    // TC09: Xác nhận mật khẩu không khớp
    @Test
    void TC09_passwordMismatch() throws Exception {
        Customer c = validCustomer();
        c.setPassword("12345678");
        c.setConfirmPassword("87654321");
        assertNotEquals("OK", validator.validate(c));
    }

    // TC10: Chưa đủ 18 tuổi
    @Test
    void TC10_under18YearsOld() throws Exception {
        Customer c = validCustomer();
        c.setBirthDate(LocalDate.now().minusYears(17));
        assertNotEquals("OK", validator.validate(c));
    }

    // TC11: Không đồng ý điều khoản
    @Test
    void TC11_notAcceptedTerms() throws Exception {
        Customer c = validCustomer();
        c.setAcceptedTerms(false);
        assertNotEquals("OK", validator.validate(c));
    }

    // TC12:
    @Test
    void TC12_emailAlreadyExists() throws Exception {

        Customer existed = new Customer(
                "KH8888",
                "Nguyễn Văn A",
                "test9999@email.com",
                "0123456788",
                "Hà Nội",
                "12345678",
                "12345678",
                LocalDate.of(1995, 1, 1),
                "Nam",
                true
        );
        dao.deleteByCustomerId("KH8888");
        dao.deleteByEmail("test9999@email.com");
        dao.insert(existed);

        Customer c = validCustomer();

        String result = validator.validate(c);

        assertEquals("Email đã tồn tại", result);
    }

}
