package service;

public class AgePriceService {

    public int calculatePrice(int age) {

        if (age < 0) {
            throw new IllegalArgumentException("Tuổi không hợp lệ");
        }

        if (age < 6) {
            return 0;
        } else if (age <= 18) {
            return 50000;
        } else if (age <= 60) {
            return 100000;
        } else {
            return 70000;
        }
    }
}
