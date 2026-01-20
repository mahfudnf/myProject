package com.program.state.stateOrder;

import com.program.model.order.Order;
import com.program.model.orderEnum.StatusOrder;

public class ValidateOrder implements OrderState{
    @Override
    public void validateOrder(Order order) {
       throw new IllegalStateException("ORDER HAS BEEN VALIDATED");
    }

    @Override
    public void created(Order order) {
        System.out.println("ORDER CREATED SUCCESSFULLY");
        order.setOrderState(new CreateState());
    }

    @Override
    public void validateItem(Order order) {
        throw new IllegalStateException("ADD ITEM FIRST");
    }

    @Override
    public void payment(Order order) {
        throw new IllegalStateException("ADD ITEM FIRST");
    }

    @Override
    public void validatePayment(Order order) {
        throw new IllegalStateException("ADD ITEM FIRST");
    }

    @Override
    public void delivery(Order order) {
        throw new IllegalStateException("ADD ITEM FIRST");
    }

    @Override
    public void validateDelivery(Order order) {
        throw new IllegalStateException("ADD ITEM FIRST");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("ORDER CANCELED");
        order.setOrderState(new CanceledState());
    }

    @Override
    public StatusOrder getStatus() {
        return StatusOrder.VALIDATED_ORDER;
    }
}
