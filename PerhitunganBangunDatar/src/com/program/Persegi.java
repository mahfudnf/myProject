package com.program;

public class Persegi extends BangunDatar{
    private double sisi;

    public Persegi(String namaBangun,double sisi) {
        super(namaBangun);
        this.sisi = sisi;
    }


    @Override
    public double hitungLuas() {
        return sisi*sisi;
    }

    @Override
    public double hitungKeliling() {
        return 4*sisi;
    }

    @Override
    public void tampilkanInfo(){
        super.tampilkanInfo();
        System.out.println("Sisi : " + sisi);
        System.out.println("Luas : " + hitungLuas());
        System.out.println("Keliling : " + hitungKeliling());
    }
}
