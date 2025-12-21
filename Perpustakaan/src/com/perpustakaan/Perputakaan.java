package com.perpustakaan;

import java.util.ArrayList;
import java.util.Scanner;

public class Perputakaan {
    ArrayList<Buku> listBuku = new ArrayList<>();
    ArrayList<BukuFiksi> bukuFiksi = new ArrayList<>();
    ArrayList<BukuNonFiksi> bukuNonFiksi = new ArrayList<>();
    ArrayList<BukuPendidikan> bukuPendidikan = new ArrayList<>();

    // method tambah
    public void tambah(Buku buku){
        if (!isCheck(buku.idBuku)){
            System.out.println("Buku gagal ditambahkan");
            System.out.println("Data buku sudah ada");
        }else {
            listBuku.add(buku);
            System.out.println(buku);
            System.out.println("Buku berhasil ditambahkan");
        }
    }

    // Overloading 1 Method tampilkan semua data
    public void tampilkan(){
        if (listBuku.isEmpty()){
            System.out.println("data kosong");
        }else {
            for (Buku buku : listBuku) {
                buku.tampilkan();
            }
        }
        }

    // Overloading 2 Method tampilkan data buku berdasarkan idBuku
    public Buku tampilkan(String idBuku){
        for (Buku b : listBuku) {
            if (b.getIdBuku().equals(idBuku)) {
                if (b instanceof BukuFiksi) {
                    bukuFiksi.add((BukuFiksi) b);
                    System.out.println("---> Buku Fiksi");
                    b.tampilkan();
                    return b;
                } else if (b instanceof BukuNonFiksi) {
                    bukuNonFiksi.add((BukuNonFiksi) b);
                    System.out.println("---> Buku NonFiksi");
                    b.tampilkan();
                    return b;
                } else if (b instanceof BukuPendidikan) {
                    bukuPendidikan.add((BukuPendidikan) b);
                    System.out.println("---> Buku Pendidikan");
                    b.tampilkan();
                    return b;
                }
            }
        }
        return null;
    }

    // Method delete
    public void delete(String idBuku){
        for (Buku b : listBuku){
            if (b.getIdBuku().equals(idBuku)){
                System.out.println(b);
                    listBuku.remove(b);
                    System.out.println("Berhasil dihapus");
                    return;
                }
        }
        if (!listBuku.remove(idBuku)){
            System.out.println("Gagal dihapus buku tidak ditemukan");
        }
    }

    // Method update
    public boolean update(){
        for (Buku b : listBuku) {
            if (b instanceof BukuFiksi){}
            else if (b instanceof BukuNonFiksi){}
            else if (b instanceof BukuPendidikan){}
        }
        return true;
    }

    // Method Check data buku berdasarkan idBuku
    public boolean isCheck(String idBuku){
        for (Buku b : listBuku){
            if (b.getIdBuku().equals(idBuku)){
                return false;
            }
        }
        return true;
    }

    // Method getYesOrNo
    public static boolean getYesOrNo(String massage){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n"+massage+" y/n ? ");
        String pilihanUser = scanner.next();

        while (!pilihanUser.equalsIgnoreCase("y") && !pilihanUser.equalsIgnoreCase("n")){
            System.out.println("Pilihan anda bukan y/n");
            System.out.println("\n"+massage+" y/n ? ");
            pilihanUser = scanner.next();
        }
        return pilihanUser.equalsIgnoreCase("y");
    }

    }





