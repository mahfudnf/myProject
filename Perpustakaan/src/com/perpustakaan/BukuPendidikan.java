package com.perpustakaan;

public class BukuPendidikan extends Buku{
    private String typeBuku;

    public BukuPendidikan(String idBuku, String judulBuku, String penulisBuku, int tahunBuku,String typeBuku) {
        super(idBuku, judulBuku, penulisBuku, tahunBuku);
        this.typeBuku = typeBuku;
    }

    public String getTypeBuku() {
        return typeBuku;
    }

    public void setTypeBuku(String typeBuku) {
        this.typeBuku = typeBuku;
    }

    @Override
    public String toString(){
        System.out.println(super.toString());
        return "Type : " + typeBuku;
    }

    @Override
    public void tampilkan(){
        super.tampilkan();
        System.out.println("Type Buku : " + typeBuku);
    }
}
