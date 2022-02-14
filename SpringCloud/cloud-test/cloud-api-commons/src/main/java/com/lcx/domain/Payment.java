package com.lcx.domain;


public class Payment {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Payment(Integer id, String serial) {
        this.id = id;
        this.serial = serial;
    }

    public Payment() {
    }

    private Integer id;
    private String serial;
}
