package com.program;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<BangunDatar> arrayList = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);
        String pilihan;
        BangunDatar bangunDatar;
        boolean running = true;

        while (running){
            System.out.println("===APLIKASI BANGUN DATAR===");
            System.out.println("1.Persegi");
            System.out.println("2.Persegi panjang");
            System.out.println("3.lingkaran");
            System.out.println("4.Keluar");
            System.out.print("Masukkan pilihan anda :");
            pilihan = userInput.next();

            if (pilihan.equals("1")){
                 //Opsi pertama
                 //bangunDatar = new Persegi("Persegi",10);
                 //bangunDatar.tampilkanInfo();

                // Opsi kedua menggunakan input userc
                System.out.print("Masukkan sisi : ");
                double sisi = userInput.nextDouble();
                bangunDatar = new Persegi("Persegi",sisi);
                arrayList.add(bangunDatar);
                bangunDatar.tampilkanInfo();

            } else if (pilihan.equals("2")) {
                // Opsi pertama
                // bangunDatar = new PersegiPanjang("Persegi panjang",20,10);
                // bangunDatar.tampilkanInfo();

                // Opsi kedua
                System.out.print("Masukkan Panjang : ");
                double panjang = userInput.nextDouble();
                System.out.print("Masukkan lebar : ");
                double lebar = userInput.nextDouble();
                bangunDatar = new PersegiPanjang("Persegi panjang",panjang,lebar);
                arrayList.add(bangunDatar);
                bangunDatar.tampilkanInfo();

            } else if (pilihan.equals("3")) {
                // Opsi pertama
                // bangunDatar = new Lingkaran("Lingkaran",20);
                // bangunDatar.tampilkanInfo();

                // Opsi kedua
                System.out.print("Masukkan Jari jari : ");
                double jarijari = userInput.nextDouble();
                bangunDatar = new Lingkaran("Lingkaran",jarijari);
                arrayList.add(bangunDatar);
                bangunDatar.tampilkanInfo();
            }else if (pilihan.equals("4")){
                running = false;
                System.out.println("Terima kasih");
            } else {
                System.out.println("pilihan yang anda masukkan salah");
                return ;
            }
        }
    }
}
