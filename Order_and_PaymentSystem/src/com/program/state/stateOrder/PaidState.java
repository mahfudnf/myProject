package com.program.state.stateOrder;

import com.program.model.order.Order;
import com.program.model.order.StatusOrder;

public class PaidState implements OrderState {
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
       throw new IllegalStateException("ORDER HAS BEEN PAID");
    }

    @Override
    public void validatePayment(Order order) {
        System.out.println("PAYMENT SUCCESSFULLY VALIDATED");
        order.setOrderState(new ValidatePaid());
    }

    @Override
    public void delivery(Order order) {
        throw new IllegalStateException("ORDER CANNOT BE SENT");
    }

    @Override
    public void validateDelivery(Order order) {
        throw new IllegalStateException("ORDER CANNOT BE SENT");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("ORDER CANCELED");
        order.setOrderState(new CanceledState());
    }

    @Override
    public StatusOrder getStatus() {
        return StatusOrder.PAID;
    }
}
