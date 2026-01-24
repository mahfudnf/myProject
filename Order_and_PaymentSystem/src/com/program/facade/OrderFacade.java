package com.program.facade;

import com.program.bridge.AppPlatform;
import com.program.bridge.MobilePlatform;
import com.program.bridge.WebPlatform;
import com.program.dao.order.OrderDao;
import com.program.dao.order.OrderDaoImpl;
import com.program.decorator.BasePayment;
import com.program.decorator.DecoratorPayment;
import com.program.decorator.DiscountPayment;
import com.program.decorator.TaxPayment;
import com.program.factory.DeliveryFactory;
import com.program.factory.OrderFactory;
import com.program.factory.OrderItemFactory;
import com.program.factory.PaymentFactory;
import com.program.model.order.*;
import com.program.model.payment.Payment;
import com.program.service.OrderService;
import com.program.strategy.CashPayment;
import com.program.strategy.EWalletPayment;
import com.program.strategy.TransferPayment;

import java.util.ArrayList;
import java.util.List;

public class OrderFacade {

    private Order currentOrder;
    private Payment payment;
    private AppPlatform appPlatform;
    private OrderService orderService = new OrderService();

    //  CHANGE PLATFORM
    public void platform(String platform){
        if (platform.equalsIgnoreCase("WEB")){
            appPlatform = new WebPlatform();
        } else if (platform.equalsIgnoreCase("MOBILE")) {
            appPlatform = new MobilePlatform();
        }
        System.out.println("APP BERJALAN DI " + appPlatform.publish());
    }

    //  CREATE ORDER
    public void createOrder(String id, Customer customer){
        if (appPlatform == null){
            System.out.println("Pilih platform terlebih dahulu");
            return;
        }
        currentOrder = OrderFactory.createOrder(id,customer);
        currentOrder.setPlatform(appPlatform);
        currentOrder.markOrder();
        System.out.println("Status Order : " + currentOrder.getStatusOrder());
    }

    //  ADD ITEM ORDER
    public void addItem(String id,String nama,int qty,double price){
        if (currentOrder == null){
            System.out.println("Buat order terlebih dahulu");
            return;
        }
        OrderItem item = OrderItemFactory.createOrderItem(id,nama,qty,price);
        item.setOrder(currentOrder);
        currentOrder.getItems().add(item);
        System.out.println("Total harga : " + currentOrder.getTotalPrice());
    }

    // FINAL PRICE
    public void finalPrice(){
        if (currentOrder == null){
            System.out.println("Buat order terlebih dahulu");
            return;
        }
        DecoratorPayment decorator = new DiscountPayment(new TaxPayment(new BasePayment(currentOrder)));
        System.out.println("Total harga + diskon + pajak : " + decorator.getTotalPrice());
        currentOrder.setFinalPrice(decorator.getTotalPrice());
        currentOrder.markValidItem();
        System.out.println("Status Order : " + currentOrder.getStatusOrder());
    }

    //  PAYMENT ORDER
    public void pay(String id,double amount,String tipe){
        if (currentOrder == null){
            System.out.println("Buat order terlebih dahulu");
            return;
        }
        Payment payment = PaymentFactory.createPayment(id,amount);
        currentOrder.setPayment(payment);

        if (!changePay(tipe)){
            return;
        }
        currentOrder.executePay(amount);
        payment.markSuccess();
        System.out.println("Status Payment : " + payment.getStatusPayment());
        currentOrder.markPay();
        System.out.println("Status Order : " + currentOrder.getStatusOrder());
        currentOrder.markValidPay();
        System.out.println("Status Order : " + currentOrder.getStatusOrder());
    }

    //  DELIVERY ORDER
    public void deliver(String id,String address){
        if (currentOrder == null){
            System.out.println("Buat order terlebih dahulu");
            return;
        }
        Delivery delivery = DeliveryFactory.createDelivery(id,address);
        currentOrder.setDelivery(delivery);
        currentOrder.markDel();
        System.out.println("Status Order : " + currentOrder.getStatusOrder());
        currentOrder.markValidDel();
        System.out.println("Status Order : " + currentOrder.getStatusOrder());
    }

    //  SIMPAN KE DATABASE
    public void finishOrder(){
        if (currentOrder == null){
            System.out.println("Buat order terlebih dahulu");
            return;
        }
        orderService.saveFullOrder(currentOrder);
        currentOrder = null;
        System.out.println("Order selesai masuk ke database");
    }

    // CANCEL
    public void cancel(){
        if (currentOrder.getStatusOrder() != StatusOrder.DELIVERED){
            currentOrder.markCanc();
            System.out.println("Status Order : " + currentOrder.getStatusOrder());
            currentOrder = null;
        }
    }

    // TAMPILKAN ORDER
    public void tampilkan(String keywords){
        orderService.tampilkanOrder(keywords);
    }

    // HELPER
    public boolean changePay(String tipe){
        if (tipe.equalsIgnoreCase("CASH")){
            currentOrder.setPaymentStrategy(new CashPayment());
            return true;
        } else if (tipe.equalsIgnoreCase("TRANSFER")) {
            currentOrder.setPaymentStrategy(new TransferPayment());
            return true;
        } else if (tipe.equalsIgnoreCase("EWALLET")) {
            currentOrder.setPaymentStrategy(new EWalletPayment());
            return true;
        }else {
            System.out.println("Tipe pembayaran salah");
            return false;
        }
    }

}
