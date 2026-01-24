package com.perpustakaan;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Perputakaan perputakaan = new Perputakaan();

    public static void main(String[] args) {
        String pilihanUser;
        boolean running = true;

        while (running) {
            System.out.println("=================");
            System.out.println("Menu Perpustakaan");
            System.out.println("=================");
            System.out.println("1. Tambah buku");
            System.out.println("2. Tampilkan buku");
            System.out.println("3. Cari buku");
            System.out.println("4. Update buku");
            System.out.println("5. Delete buku");
            System.out.println("6. Keluar");
            System.out.print("Masukkan pilihan anda (1-6) : ");
            pilihanUser = scanner.next();

            switch (pilihanUser) {
                case "1":
                    System.out.println("===========");
                    System.out.println("Tambah Buku");
                    System.out.println("===========");
                    tambahBuku();
                    break;
                case "2":
                    System.out.println("==============");
                    System.out.println("Tampilkan Buku");
                    System.out.println("==============");
                    tampilkanBuku();
                    break;
                case "3":
                    System.out.println("=========");
                    System.out.println("Cari Buku");
                    System.out.println("=========");
                    cariBuku();
                    break;
                case "4":
                    System.out.println("===========");
                    System.out.println("Update Buku");
                    System.out.println("===========");
                    updateBuku();
                    break;
                case "5":
                    System.out.println("===========");
                    System.out.println("Delete Buku");
                    System.out.println("===========");
                    deleteBuku();
                    break;
                case "6":
                    System.out.println("Terima kasih");
                    running = false;
                default:
                    System.out.println("pilihan anda salah");
            }
        }

    }

    private static String id, judul, penulis, genre, kategori, type;
    private static int tahun;

    public static void tambahBuku() {
        System.out.println("Masukkan id buku : ");
        id = scanner.next();
        System.out.println("Masukkan judul buku : ");
        judul = scanner.next();
        System.out.println("Masukkan penulis buku : ");
        penulis = scanner.next();
        System.out.println("Masukkan tahun buku : ");
        tahun = Integer.parseInt(scanner.next());

        System.out.println("Pilih jenis");
        System.out.println("1. Buku fiksi");
        System.out.println("2. Buku nonfiksi");
        System.out.println("3. Buku pendidikan");

        String pilihanJenis = scanner.next();
            switch (pilihanJenis) {
                case "1":
                    System.out.println("Masukkan genre buku : ");
                    genre = scanner.next();
                    if (validasiInput()) {
                        perputakaan.tambah(new BukuFiksi(id, judul, penulis, tahun, genre));
                    }else {
                        System.out.println("Input data harus terisi");
                    }
                    break;
                case "2":
                    System.out.println("Masukkan kategori buku :");
                    kategori = scanner.next();
                    if (validasiInput()) {
                        perputakaan.tambah(new BukuNonFiksi(id, judul, penulis, tahun, kategori));
                    }else {
                        System.out.println("Input data harus terisi");
                    }
                    break;
                case "3":
                    System.out.println("Masukkan type buku :");
                    type = scanner.next();
                    if (validasiInput()) {
                        perputakaan.tambah(new BukuPendidikan(id, judul, penulis, tahun, type));
                    }else {
                        System.out.println("Input data harus terisi");
                    }
                    break;
                default:
                    System.out.println("pilihan tidak ditemukan");
            }
        }

    public static void tampilkanBuku(){
        perputakaan.tampilkan();
    }

    public static void cariBuku(){
        System.out.println("Masukkan kata kunci id buku :");
        id = scanner.next();
        perputakaan.tampilkan(id);
    }

    public static void deleteBuku(){
        System.out.println("Masukkan kata kunci id buku :");
        id = scanner.next();
        perputakaan.tampilkan(id);
        boolean isDelete = perputakaan.getYesOrNo("Apakah anda yakin ingin delete buku ini?");
        if (isDelete) {
            perputakaan.delete(id);
        }
    }

    public static boolean validasiInput(){
        if (id == "" || judul == "" || penulis == "" || genre == "" || kategori == "" ||
        type == "" || tahun == 0){
            return false;
        } else {
            return true;
        }
    }

    public static void updateBuku() {
        System.out.println("Masukkan kata kunci id buku :");
        id = scanner.next();
        Buku buku = perputakaan.tampilkan(id);
        boolean isUpdate = perputakaan.getYesOrNo("Apakah anda ingin update buku ini?");

        if (isUpdate) {
            System.out.println("Masukkan judul buku baru :");
            buku.setJudulBuku(scanner.next());
            System.out.println("Masukkan penulis buku baru :");
            buku.setPenulisBuku(scanner.next());
            System.out.println("Masukkan tahun buku baru :");
            buku.setTahunBuku(scanner.nextInt());

            genre = null;
            kategori = null;
            type = null;

            if (buku instanceof BukuFiksi) {
                System.out.println("Masukkan genre buku baru :");
                ((BukuFiksi) buku).setGenreBuku(scanner.next());
            } else if (buku instanceof BukuNonFiksi) {
                System.out.println("Masukkan kategori buku baru :");
                ((BukuNonFiksi) buku).setKategoriBuku(scanner.next());
            } else if (buku instanceof BukuPendidikan) {
                System.out.println("Masukkan type buku baru :");
                ((BukuPendidikan) buku).setTypeBuku(scanner.next());
            }

            boolean hasil = perputakaan.update();
            if (hasil) {
                System.out.println("Update berhasil");
            } else {
                System.out.println("Update gagal");
            }
        }
    }

    }
