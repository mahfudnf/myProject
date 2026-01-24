package com.perpustakaan;

public class BukuNonFiksi extends Buku{
    private String kategoriBuku;

    public BukuNonFiksi(String idBuku, String judulBuku, String penulisBuku, int tahunBuku, String kategoriBuku) {
        super(idBuku, judulBuku, penulisBuku, tahunBuku);
        this.kategoriBuku = kategoriBuku;
    }

    public String getKategoriBuku() {
        return kategoriBuku;
    }

    public void setKategoriBuku(String kategoriBuku) {
        this.kategoriBuku = kategoriBuku;
    }

    @Override
    public String toString(){
        System.out.println(super.toString());
        return "Kategori : " + kategoriBuku;
    }

    @Override
    public void tampilkan(){
        super.tampilkan();
        System.out.println("Kategori Buku : " + kategoriBuku);
    }
}
