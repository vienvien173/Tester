package ui;

import service.Gender;
import service.PaymentService;

import javax.swing.*;

public class PaymentForm extends JFrame {

    private JTextField txtAge;
    private JTextField txtPayment;

    private JRadioButton rbMale;
    private JRadioButton rbFemale;
    private JRadioButton rbChild;

    private PaymentService service = new PaymentService();

    public PaymentForm() {
        setTitle("Calculate the Payment for the Patient");
        setSize(450, 260);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Gender
        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        rbChild = new JRadioButton("Child (0 - 17 years)");

        rbMale.setBounds(30, 30, 100, 25);
        rbFemale.setBounds(140, 30, 100, 25);
        rbChild.setBounds(250, 30, 160, 25);

        ButtonGroup group = new ButtonGroup();
        group.add(rbMale);
        group.add(rbFemale);
        group.add(rbChild);

        add(rbMale);
        add(rbFemale);
        add(rbChild);

        // Age
        JLabel lblAge = new JLabel("Age (Years):");
        lblAge.setBounds(30, 80, 100, 25);
        add(lblAge);

        txtAge = new JTextField();
        txtAge.setBounds(130, 80, 120, 25);
        add(txtAge);

        // Button
        JButton btnCalculate = new JButton("Calculate");
        btnCalculate.setBounds(270, 80, 100, 25);
        add(btnCalculate);

        // Payment
        JLabel lblPayment = new JLabel("Payment is:");
        lblPayment.setBounds(30, 130, 100, 25);
        add(lblPayment);

        txtPayment = new JTextField();
        txtPayment.setBounds(130, 130, 120, 25);
        txtPayment.setEditable(false);
        add(txtPayment);

        JLabel lblEuro = new JLabel("euro €");
        lblEuro.setBounds(260, 130, 60, 25);
        add(lblEuro);

        btnCalculate.addActionListener(e -> calculate());
    }

    private void calculate() {
        try {
            if (!rbMale.isSelected() && !rbFemale.isSelected() && !rbChild.isSelected()) {
                throw new IllegalArgumentException("Vui lòng chọn giới tính");
            }

            int age = Integer.parseInt(txtAge.getText());

            Gender gender = rbMale.isSelected() ? Gender.MALE
                    : rbFemale.isSelected() ? Gender.FEMALE
                    : Gender.CHILD;

            int payment = service.calculatePayment(age, gender);
            txtPayment.setText(String.valueOf(payment));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tuổi phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new PaymentForm().setVisible(true);
    }
}
