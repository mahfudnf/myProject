package com.program.factory;

import com.program.model.order.Delivery;

public class DeliveryFactory {
    public static Delivery createDelivery(String id,String address){
        return new Delivery(id,address);
    }
}
