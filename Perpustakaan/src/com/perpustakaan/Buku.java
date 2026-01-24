package com.perpustakaan;

public class Buku {
    protected String idBuku;
    protected String judulBuku;
    protected String penulisBuku;
    protected int tahunBuku;

    public Buku(String idBuku, String judulBuku, String penulisBuku, int tahunBuku) {
        this.idBuku = idBuku;
        this.judulBuku = judulBuku;
        this.penulisBuku = penulisBuku;
        this.tahunBuku = tahunBuku;
    }

    public void setIdBuku(String idBuku) {
        this.idBuku = idBuku;
    }

    public void setJudulBuku(String judulBuku) {
        this.judulBuku = judulBuku;
    }

    public void setPenulisBuku(String penulisBuku) {
        this.penulisBuku = penulisBuku;
    }

    public void setTahunBuku(int tahunBuku) {
        this.tahunBuku = tahunBuku;
    }


    public String getIdBuku() {
        return idBuku;
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public String getPenulisBuku() {
        return penulisBuku;
    }

    public int getTahunBuku() {
        return tahunBuku;
    }


    @Override
    public String toString(){
        return "Id buku : " + idBuku + "\n" +
                "Judul buku : " + judulBuku + "\n" +
                "Penulis buku : " + penulisBuku + "\n" +
                "Tahun buku : " + tahunBuku;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if (!(obj instanceof Buku)){
            return false;
        }

        Buku buku = (Buku) obj;
        return this.idBuku.equals(buku.idBuku);
    }

    public void tampilkan(){
        System.out.println("===DATA SEMUA BUKU===");
        System.out.println("Id Buku : " + idBuku);
        System.out.println("Judul Buku : " + judulBuku);
        System.out.println("Penulis Buku : " + penulisBuku) ;
        System.out.println("Tahun Buku : " + tahunBuku);
    }

}
