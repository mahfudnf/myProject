package com.perpustakaan;

public class BukuFiksi extends Buku{
    private String genreBuku;

    public BukuFiksi(String idBuku, String judulBuku, String penulisBuku, int tahunBuku, String genreBuku) {
        super(idBuku, judulBuku, penulisBuku, tahunBuku);
        this.genreBuku = genreBuku;
    }

    public String getGenreBuku() {
        return genreBuku;
    }

    public void setGenreBuku(String genreBuku) {
        this.genreBuku = genreBuku;
    }

    @Override
    public String toString(){
        System.out.println(super.toString());
        return "Genre : " + genreBuku;
    }

    @Override
    public void tampilkan(){
        super.tampilkan();
        System.out.println("Genre Buku : " + genreBuku);
    }

}
