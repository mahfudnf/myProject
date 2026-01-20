package com.program.state.stateOrder;

import com.program.model.order.Order;
import com.program.model.orderEnum.StatusOrder;

public class CanceledState implements OrderState {
    @Override
    public void validateOrder(Order order) {
        throw new IllegalStateException("ORDER HAS BEEN CANCELLED");
    }

    @Override
    public void created(Order order) {
        throw new IllegalStateException("ORDER HAS BEEN CANCELLED");
    }

    @Override
    public void validateItem(Order order) {
        throw new IllegalStateException("ORDER HAS BEEN CANCELLED");
    }

    @Override
    public void payment(Order order) {
        throw new IllegalStateException("ORDER HAS BEEN CANCELLED");
    }

    @Override
    public void validatePayment(Order order) {
        throw new IllegalStateException("ORDER HAS BEEN CANCELLED");
    }

    @Override
    public void delivery(Order order) {
        throw new IllegalStateException("ORDER HAS BEEN CANCELLED");
    }

    @Override
    public void validateDelivery(Order order) {
        throw new IllegalStateException("ORDER HAS BEEN CANCELLED");
    }

    @Override
    public void cancel(Order order) {
        throw new IllegalStateException("ORDER HAS BEEN CANCELLED");
    }

    @Override
    public StatusOrder getStatus() {
        return StatusOrder.CANCELLED;
    }
}
