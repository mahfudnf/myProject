package com.program.model.order;

public class Delivery {
    private final String deliveryId;
    private final String address;

    public Delivery(String deliveryId, String address) {
        this.deliveryId = deliveryId;
        this.address = address;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public String getAddress() {
        return address;
    }
}
