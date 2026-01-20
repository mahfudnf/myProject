package com.program.model.order;

import com.program.bridge.AppPlatform;
import com.program.decorator.DecoratorPayment;
import com.program.model.orderEnum.StatusOrder;
import com.program.state.stateOrder.OrderState;
import com.program.state.stateOrder.ValidateOrder;
import com.program.strategy.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;

public class Order implements DecoratorPayment {
    private String orderId;
    private Customer customer;
    private List<OrderItem> items;
    private Payment payment;
    private Delivery delivery;
    private StatusOrder statusOrder;
    private OrderState orderState;
    private PaymentStrategy paymentStrategy;
    private AppPlatform platform;

    public Order(String orderId,Customer customer){
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        setOrderState(new ValidateOrder());
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
        this.statusOrder = orderState.getStatus();
    }

    public void markOrder(){
        orderState.created(this);
    }

    public void markValidItem(){
        orderState.validateItem(this);
    }

    public void markPay(){
        orderState.payment(this);
    }

    public void markValidPay(){
        orderState.validatePayment(this);
    }

    public void markDel(){
        orderState.delivery(this);
    }

    public void markValidDel(){
        orderState.validateDelivery(this);
    }

    public void markCanc(){
        orderState.cancel(this);
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Payment getPayment() {
        return payment;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    // ORDER ITEM
    public void addItem(OrderItem orderItem){
        this.items.add(orderItem);
    }

    // PAYMENT STRATEGY
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePay(double amount){
        if (paymentStrategy == null){
            throw new IllegalStateException("Metode pembayaran belum dipilih");
        }
        paymentStrategy.pay(amount);
    }

    // DECORATOR
    @Override
    public double getTotalPrice() {
        double total = 0;
        for (OrderItem item : items){
            total += item.getTotalHarga();
        }
        return total;
    }

    // BRIDGE
    public String publishApp() {
        if (platform == null){
            throw new IllegalStateException("Platform belum ditentukan");
        }
        return "APP DIJALANKAN DI " + platform.publish();
    }

    public void setPlatform(AppPlatform platform) {
        this.platform = platform;
    }

}
