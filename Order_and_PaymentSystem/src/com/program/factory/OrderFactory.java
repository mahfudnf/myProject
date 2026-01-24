package com.program.factory;

import com.program.model.order.Customer;
import com.program.model.order.Order;

public class OrderFactory {
    public static Order createOrder(String id, Customer customer){
        return new Order(id,customer);
    }
}
