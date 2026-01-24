package com.program.state.stateOrder;

import com.program.model.order.Order;
import com.program.model.order.StatusOrder;

public class DeliveredState implements OrderState{
    @Override
    public void validateOrder(Order order) {
        throw new IllegalStateException("ORDER HAS BEEN VALIDATED");
    }

    @Override
    public void created(Order order) {
        throw new IllegalStateException("ORDER HAS BEEN MADE");
    }

    @Override
    public void validateItem(Order order) {
        throw new IllegalStateException("ITEM HAS BEEN VALIDATED");
    }

    @Override
    public void payment(Order order) {
        throw new IllegalStateException("ORDER HAS BEEN PAID");    }

    @Override
    public void validatePayment(Order order) {
        throw new IllegalStateException("PAYMENT HAS BEEN VALIDATED");
    }

    @Override
    public void delivery(Order order) {
       throw new IllegalStateException("ORDER SENT SUCCESSFULLY");
    }

    @Override
    public void validateDelivery(Order order) {
        System.out.println("DELIVERY SUCCESSFULLY VALIDATED");
        order.setOrderState(new ValidateDelivered());
    }

    @Override
    public void cancel(Order order) {
        throw new IllegalStateException("ORDER DELIVERY SUCCESS,CANNOT BE CANCELLED");
    }

    @Override
    public StatusOrder getStatus() {
        return StatusOrder.DELIVERED;
    }
}
