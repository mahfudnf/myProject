package com.program.state.stateOrder;

import com.program.model.order.Order;
import com.program.model.orderEnum.StatusOrder;

public interface OrderState {
    void validateOrder(Order order);
    void created(Order order);
    void validateItem(Order order);
    void payment(Order order);
    void validatePayment(Order order);
    void delivery(Order order);
    void validateDelivery(Order order);
    void cancel(Order order);
    StatusOrder getStatus();
}
