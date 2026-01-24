package com.program.decorator;

import com.program.model.order.Order;

public class BasePayment implements DecoratorPayment{

    private Order order;

    public BasePayment(Order order){
        this.order = order;
    }
    @Override
    public double getTotalPrice() {
        return order.getTotalPrice();
    }
}
