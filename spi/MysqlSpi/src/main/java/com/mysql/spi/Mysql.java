package com.mysql.spi;

public class Mysql implements com.lcx.spi.SpiInterface{
    @Override
    public void baseUrl() {
        System.out.println("Mysql spi");
    }
}
