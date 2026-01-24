package com.program;

public class Lingkaran extends BangunDatar{
    private double jariJari;
    private final double phi = 3.14;

    public Lingkaran(String namaBangun,double jariJari) {
        super(namaBangun);
        this.jariJari = jariJari;
    }

    @Override
    public double hitungLuas() {
        return phi * jariJari * jariJari;
    }

    @Override
    public double hitungKeliling() {
        return 2 * phi * jariJari;
    }

    @Override
    public void tampilkanInfo(){
        super.tampilkanInfo();
        System.out.println("Jari jari : " + jariJari);
        System.out.println("Luas : " + hitungLuas());
        System.out.println("Keliling : " + hitungKeliling());
    }
}
