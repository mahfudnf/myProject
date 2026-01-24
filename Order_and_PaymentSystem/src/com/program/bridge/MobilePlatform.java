package com.program.bridge;

public class MobilePlatform implements AppPlatform{
    @Override
    public String publish() {
        return "MOBILE";
    }
}
