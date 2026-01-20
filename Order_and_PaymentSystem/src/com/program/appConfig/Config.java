package com.program.appConfig;

public class Config {
    private static Config instance;
    private String namaApp;

    private Config(){
        this.namaApp = "APP ORDER and PAYMENT";
    }

    public static Config getInstance(){
        if (instance == null){
            instance = new Config();
        }
        return instance;
    }

    public String getNamaApp() {
        return namaApp;
    }
}
