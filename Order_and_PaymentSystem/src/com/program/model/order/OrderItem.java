package com.program.model.order;

import com.program.decorator.DecoratorPayment;

public class OrderItem {
    private final String productId;
    private final String name;
    private final int quantity;
    private final double price;

    public OrderItem(String productId,String name, int quantity, double price) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalHarga(){
        return quantity * price;
    }

}
