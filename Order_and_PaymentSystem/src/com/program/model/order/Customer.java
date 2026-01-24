package com.program.model.order;

public class Customer {
    private final String customerId;
    private final String nama;
    private final String email;
    private final String phone;

    public Customer(String customerId, String nama, String email, String phone) {
        this.customerId = customerId;
        this.nama = nama;
        this.email = email;
        this.phone = phone;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
