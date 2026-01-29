package model;

import java.time.LocalDate;

public class Customer {
    private String customerId;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String password;
    private String confirmPassword;
    private LocalDate birthDate;
    private String gender;
    private boolean acceptedTerms;

    // ===== Constructor =====
    public Customer(String customerId, String fullName, String email,
                    String phone, String address,
                    String password, String confirmPassword,
                    LocalDate birthDate, String gender, boolean acceptedTerms) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.birthDate = birthDate;
        this.gender = gender;
        this.acceptedTerms = acceptedTerms;
    }

    // ===== Getter =====
    public String getCustomerId() { return customerId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getPassword() { return password; }
    public String getConfirmPassword() { return confirmPassword; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getGender() { return gender; }
    public boolean isAcceptedTerms() { return acceptedTerms; }

    // ===== Setter (THÊM CHO JUNIT) =====
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAcceptedTerms(boolean acceptedTerms) {
        this.acceptedTerms = acceptedTerms;
    }
}
