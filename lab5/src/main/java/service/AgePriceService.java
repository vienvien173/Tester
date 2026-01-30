package service;

public class AgePriceService {

    public int calculatePrice(int age, Gender gender) {

        if (age < 0) {
            throw new IllegalArgumentException("Age must be >= 0");
        }

        if (gender == null) {
            throw new IllegalArgumentException("Gender is required");
        }

        // CHILD
        if (gender == Gender.CHILD) {
            if (age <= 17) return 50;
            throw new IllegalArgumentException("Child age must be <= 17");
        }

        // MALE
        if (gender == Gender.MALE) {
            if (age < 18) throw new IllegalArgumentException("Male age must be >= 18");
            if (age <= 35) return 100;
            if (age <= 50) return 120;
            return 140;
        }

        // FEMALE
        if (gender == Gender.FEMALE) {
            if (age < 18) throw new IllegalArgumentException("Female age must be >= 18");
            if (age <= 35) return 80;
            if (age <= 50) return 110;
            return 140;
        }

        throw new IllegalArgumentException("Invalid input");
    }
}
