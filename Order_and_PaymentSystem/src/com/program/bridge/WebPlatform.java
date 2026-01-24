package com.program.bridge;

public class WebPlatform implements AppPlatform{
    @Override
    public String publish() {
        return "WEB";
    }
}
