package com.program;

public class PersegiPanjang extends BangunDatar{
    private double panjang;
    private double lebar;

    public PersegiPanjang(String namaBangun,double panjang,double lebar) {
        super(namaBangun);
        this.panjang = panjang;
        this.lebar = lebar;
    }


    @Override
    public double hitungLuas() {
        return panjang*lebar;
    }

    @Override
    public double hitungKeliling() {
        return 2*(panjang + lebar);
    }

    @Override
    public void tampilkanInfo(){
        super.tampilkanInfo();
        System.out.println("Panjang : " + panjang);
        System.out.println("Lebar : " + lebar);
        System.out.println("Luas : " + hitungLuas());
        System.out.println("Keliling : " + hitungKeliling());
    }
}
