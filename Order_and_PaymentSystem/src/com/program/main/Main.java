package com.program.main;

import com.program.facade.OrderFacade;
import com.program.model.order.Customer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int pilihan;
        boolean running = true;
        OrderFacade facade = new OrderFacade();

        while (running) {
            System.out.println("===APP ORDER AND PAYMENT===");
            System.out.println("1. PILIH PLATFORM");
            System.out.println("2. BUAT ORDER");
            System.out.println("3. TAMBAH ITEM");
            System.out.println("4. VALIDASI ITEM");
            System.out.println("5. PEMBAYARAN");
            System.out.println("6. PENGIRIMAN");
            System.out.println("7. CANCEL ORDER");
            System.out.println("8. FINISH ORDER");
            System.out.println("9. CARI DATA ORDER");
            System.out.println("0. KELUAR");
            System.out.println("MASUKKAN PILIHAN : ");
            pilihan = scanner.nextInt();

            switch (pilihan){
                case 1 :
                    System.out.println("PILIH WEB/MOBILE :");
                    String platform = scanner.next();
                    facade.platform(platform);
                    break;
                case 2 :
                    System.out.println("Isi data customer terlebih dahulu");
                    System.out.println("Id customer : ");
                    String idCustomer = scanner.next();
                    System.out.println("Nama customer : ");
                    String namaCustomer = scanner.next();
                    System.out.println("Email customer : ");
                    String emailCustomer = scanner.next();
                    System.out.println("No phone : ");
                    String phoneCustomer = scanner.next();
                    Customer customer = new Customer(idCustomer,namaCustomer,emailCustomer,phoneCustomer);
                    System.out.println("Id order : ");
                    String idOrder = scanner.next();
                    facade.createOrder(idOrder,customer);
                    break;
                case 3 :
                    System.out.println("Id item : ");
                    String idItem = scanner.next();
                    System.out.println("Nama item : ");
                    String namaItem = scanner.next();
                    System.out.println("Quantity item : ");
                    int qtyItem = scanner.nextInt();
                    System.out.println("Price Per item : ");
                    double priceItem = scanner.nextDouble();
                    facade.addItem(idItem,namaItem,qtyItem,priceItem);
                    break;
                case 4:
                    facade.finalPrice();
                    break;
                case 5 :
                    System.out.println("Id payment : ");
                    String idPay = scanner.next();
                    System.out.println("Amount : ");
                    double amount = scanner.nextDouble();
                    System.out.println("Tipe payment CASH/TRANSFER/E-WALLET : ");
                    String tipe = scanner.next();
                    facade.pay(idPay,amount,tipe);
                    break;
                case 6 :
                    System.out.println("Id delivery : ");
                    String idDeliver = scanner.next();
                    System.out.println("Address : ");
                    String address = scanner.next();
                    facade.deliver(idDeliver,address);
                    break;
                case 7:
                    facade.cancel();
                    break;
                case 8:
                    facade.finishOrder();
                    break;
                case 9:
                    System.out.println("Masukkan Order id :");
                    String keywords = scanner.next();
                    facade.tampilkan(keywords);
                    break;
                case 0 :
                    running = false;
                    System.out.println("TERIMA KASIH");
                    break;
                default:
                    System.out.println("PILIHAN SALAH");
            }
        }

    }
}
