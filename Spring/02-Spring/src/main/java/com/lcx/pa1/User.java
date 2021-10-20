package com.lcx.pa1;

public class User {
    private String name;
    private String psw;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", psw='" + psw + '\'' +
                '}';
    }

    public User() {
    }

    public User(String name, String psw) {
        this.name = name;
        this.psw = psw;
    }
}
