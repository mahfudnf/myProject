package com.program.factory;

import com.program.model.order.OrderItem;

public class OrderItemFactory {
    public static OrderItem createOrderItem(String id,String name,int quatity,double price){
        return new OrderItem(id,name,quatity,price);
    }
}
