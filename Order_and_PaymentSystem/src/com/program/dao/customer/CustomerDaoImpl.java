package com.program.dao.customer;

import com.program.model.order.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CustomerDaoImpl implements CustomerDao{

    private Connection connection;

    public CustomerDaoImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public Customer save(Customer customer) {
        String sql = "INSERT INTO customer(customerId,nama,email,phone)" +
                "VALUES (?,?,?,?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, customer.getCustomerId());
            ps.setString(2, customer.getNama());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPhone());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("gagal menyimpan customer",e);
        }
        return customer;
    }
}
