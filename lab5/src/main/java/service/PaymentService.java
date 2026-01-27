package service;

public class PaymentService {

    public int calculatePayment(int age, Gender gender) {

        if (age < 0 || age > 145) {
            throw new IllegalArgumentException("Tuổi không hợp lệ");
        }


        if (gender == Gender.CHILD) {
            if (age <= 17) {
                return 50; // euro
            } else {
                throw new IllegalArgumentException("Child chỉ áp dụng cho tuổi 0-17");
            }
        }


        if (age < 18) {
            throw new IllegalArgumentException("Male/Female phải từ 18 tuổi trở lên");
        }


        if (gender == Gender.MALE) {
            if (age <= 35) return 100;
            if (age <= 50) return 120;
            return 140;
        }


        if (gender == Gender.FEMALE) {
            if (age <= 35) return 80;
            if (age <= 50) return 110;
            return 140;
        }

        throw new IllegalArgumentException("Giới tính không hợp lệ");
    }
}
