package com.program.state.stateOrder;

import com.program.model.order.Order;
import com.program.model.orderEnum.StatusOrder;
import com.program.model.orderEnum.StatusPayment;

public class ValidatePaid implements OrderState{
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
       throw new IllegalStateException("PAYMENT HAS BEEN VALIDATED");
    }

    @Override
    public void delivery(Order order) {
        System.out.println("ORDER SENT SUCCESSFULLY");
        order.setOrderState(new DeliveredState());
    }

    @Override
    public void validateDelivery(Order order) {
        throw new IllegalStateException("VALIDATION DELIVERY FAILED");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("ORDER CANCELED");
        order.setOrderState(new CanceledState());
    }

    @Override
    public StatusOrder getStatus() {
        return StatusOrder.VALIDATED_PAID;
    }
}
