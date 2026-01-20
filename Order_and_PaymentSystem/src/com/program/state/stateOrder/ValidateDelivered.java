package com.program.state.stateOrder;

import com.program.model.order.Order;
import com.program.model.orderEnum.StatusOrder;

public class ValidateDelivered implements OrderState{
    @Override
    public void validateOrder(Order order) {
        throw new IllegalStateException("ORDER DELIVERY SUCCESS");
    }

    @Override
    public void created(Order order) {
        throw new IllegalStateException("ORDER DELIVERY SUCCESS");
    }

    @Override
    public void validateItem(Order order) {
        throw new IllegalStateException("ORDER DELIVERY SUCCESS");
    }

    @Override
    public void payment(Order order) {
        throw new IllegalStateException("ORDER DELIVERY SUCCESS");
    }

    @Override
    public void validatePayment(Order order) {
        throw new IllegalStateException("ORDER DELIVERY SUCCESS");
    }

    @Override
    public void delivery(Order order) {
        throw new IllegalStateException("ORDER DELIVERY SUCCESS");
    }

    @Override
    public void validateDelivery(Order order) {
      throw new IllegalStateException("DELIVERY SUCCESSFULLY VALIDATED");
    }

    @Override
    public void cancel(Order order) {
        throw new IllegalStateException("VALIDATION ORDER DELIVERY SUCCESS,CANNOT BE CANCELLED");
    }

    @Override
    public StatusOrder getStatus() {
        return StatusOrder.VALIDATION_DELIVERED;
    }
}
