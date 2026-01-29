package service;

import dao.CustomerDAO;
import model.Customer;

import java.time.LocalDate;
import java.time.Period;

public class CustomerValidator {

    private final CustomerDAO dao = new CustomerDAO();

    public String validate(Customer c) throws Exception {

        if (c.getCustomerId() == null || !c.getCustomerId().matches("^[a-zA-Z0-9]{6,10}$"))
            return "Mã KH không hợp lệ";

        if (dao.existsCustomerId(c.getCustomerId()))
            return "Mã KH đã tồn tại";

        if (c.getFullName() == null || !c.getFullName().matches("^[a-zA-ZÀ-ỹ\\s]{5,50}$"))
            return "Họ tên không hợp lệ";

        if (c.getEmail() == null ||
                !c.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
            return "Email không hợp lệ";

        if (dao.existsEmail(c.getEmail()))
            return "Email đã tồn tại";

        if (!c.getPhone().matches("^0\\d{9,11}$"))
            return "SĐT không hợp lệ";

        if (c.getAddress() == null || c.getAddress().isBlank() || c.getAddress().length() > 255)
            return "Địa chỉ không hợp lệ";

        if (c.getPassword().length() < 8)
            return "Mật khẩu tối thiểu 8 ký tự";

        if (!c.getPassword().equals(c.getConfirmPassword()))
            return "Mật khẩu xác nhận không khớp";

        if (c.getBirthDate() != null) {
            if (Period.between(c.getBirthDate(), LocalDate.now()).getYears() < 18)
                return "Chưa đủ 18 tuổi";
        }

        if (!c.isAcceptedTerms())
            return "Phải đồng ý điều khoản";

        return "OK";
    }
}
