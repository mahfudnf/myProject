package com.program.model.order;

public class OrderItem {
    private final String productId;
    private final String name;
    private final int quantity;
    private final double price;
    private Order order;

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
