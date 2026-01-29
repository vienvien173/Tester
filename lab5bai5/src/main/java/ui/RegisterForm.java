package ui;

import model.Customer;
import service.CustomerValidator;
import dao.CustomerDAO;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class RegisterForm extends JFrame {

    JTextField txtId = new JTextField();
    JTextField txtName = new JTextField();
    JTextField txtEmail = new JTextField();
    JTextField txtPhone = new JTextField();
    JTextField txtAddress = new JTextField();
    JTextField txtBirth = new JTextField("yyyy-mm-dd");
    JPasswordField txtPass = new JPasswordField();
    JPasswordField txtConfirm = new JPasswordField();

    JRadioButton rNam = new JRadioButton("Nam");
    JRadioButton rNu = new JRadioButton("Nữ");
    JRadioButton rKhac = new JRadioButton("Khác");

    JCheckBox chk = new JCheckBox("Tôi đồng ý điều khoản");

    public RegisterForm() {
        setTitle("Đăng ký tài khoản");
        setSize(450, 500);
        setLayout(new GridLayout(11, 2));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ButtonGroup g = new ButtonGroup();
        g.add(rNam); g.add(rNu); g.add(rKhac);

        add(new JLabel("Mã KH")); add(txtId);
        add(new JLabel("Họ tên")); add(txtName);
        add(new JLabel("Email")); add(txtEmail);
        add(new JLabel("SĐT")); add(txtPhone);
        add(new JLabel("Địa chỉ")); add(txtAddress);
        add(new JLabel("Mật khẩu")); add(txtPass);
        add(new JLabel("Xác nhận")); add(txtConfirm);
        add(new JLabel("Ngày sinh")); add(txtBirth);

        JPanel p = new JPanel();
        p.add(rNam); p.add(rNu); p.add(rKhac);
        add(new JLabel("Giới tính")); add(p);

        add(chk); add(new JLabel());

        JButton btn = new JButton("Đăng ký");
        add(btn);

        btn.addActionListener(e -> submit());
    }

    void submit() {
        try {
            LocalDate birth = txtBirth.getText().isBlank()
                    ? null : LocalDate.parse(txtBirth.getText());

            String gender = rNam.isSelected() ? "Nam" :
                    rNu.isSelected() ? "Nu" :
                            rKhac.isSelected() ? "Khac" : null;

            Customer c = new Customer(
                    txtId.getText(),
                    txtName.getText(),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    txtAddress.getText(),
                    new String(txtPass.getPassword()),
                    new String(txtConfirm.getPassword()),
                    birth,
                    gender,
                    chk.isSelected()
            );

            CustomerValidator v = new CustomerValidator();
            String rs = v.validate(c);

            if (rs.equals("OK")) {
                CustomerDAO dao = new CustomerDAO();
                dao.insert(c);

                JOptionPane.showMessageDialog(this,
                        "Đăng ký tài khoản thành công!");
            } else {
                JOptionPane.showMessageDialog(this, rs);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Lỗi hệ thống",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        new RegisterForm().setVisible(true);
    }
}
