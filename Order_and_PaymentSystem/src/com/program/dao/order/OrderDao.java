package com.program.dao.order;

import com.program.model.order.Order;

import java.util.List;

public interface OrderDao {
    Order save(Order order);
    List<Order> search(Order order);
}
