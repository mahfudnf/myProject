package com.program.Main;

import com.program.appConfig.Config;
import com.program.bridge.WebPlatform;
import com.program.decorator.DecoratorPayment;
import com.program.decorator.DiscountPayment;
import com.program.decorator.TaxPayment;
import com.program.factory.DeliveryFactory;
import com.program.factory.OrderItemFactory;
import com.program.factory.PaymentFactory;
import com.program.model.order.*;
import com.program.factory.OrderFactory;
import com.program.strategy.CashPayment;

public class Main {
    public static void main(String[] args) {
        // CONFIGURATION
        Config config = Config.getInstance();
        System.out.println("====="+config.getNamaApp()+"=====");

        // CREATE ORDER
        Customer customer = new Customer("003","eko","eko@email.com","089765");
        Order order = OrderFactory.createOrder("003",customer);

        // APP PLATFORM
        order.setPlatform(new WebPlatform());
        System.out.println(order.publishApp());

        System.out.println("Status Order : " + order.getStatusOrder());
        order.markOrder();
        System.out.println("Status Order : " + order.getStatusOrder());

        // ADD ITEM
        OrderItem orderItem = OrderItemFactory.createOrderItem("001","bakso",2,10000);
        System.out.println("Total Harga Awal: " + orderItem.getTotalHarga());
        order.addItem(orderItem);

        // HARGA + DECORATOR
        DecoratorPayment total = new TaxPayment(new DiscountPayment(order));
        System.out.println("Total harga + Discount + Tax : " + total.getTotalPrice());
        order.markValidItem();
        System.out.println("Status Order : " + order.getStatusOrder());

        // CREATE PEMBAYARAN
        Payment payment = PaymentFactory.createPayment("001",20000);
        order.setPayment(payment);
        order.setPaymentStrategy(new CashPayment());
        order.executePay(total.getTotalPrice());
        payment.markSuccess();
        order.markPay();
        System.out.println("Status Order : " + order.getStatusOrder());
        order.markValidPay();
        System.out.println("Status Order : " + order.getStatusOrder());

        // CREATE DELIVERY
        Delivery delivery = DeliveryFactory.createDelivery("001","Pandaan");
        order.setDelivery(delivery);
        order.markDel();
        System.out.println("Status Order : " + order.getStatusOrder());
        order.markValidDel();
        System.out.println("Status Order : " + order.getStatusOrder());

    }
}
