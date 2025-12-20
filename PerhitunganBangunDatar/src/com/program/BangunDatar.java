package com.program;

public abstract class BangunDatar {
    protected String namaBangun;

    public BangunDatar(String namaBangun){
        this.namaBangun = namaBangun;
    }

    public abstract double hitungLuas();
    public abstract double hitungKeliling();

    public void tampilkanInfo(){
        System.out.println("Nama Bangun : " + namaBangun);
    }

}
