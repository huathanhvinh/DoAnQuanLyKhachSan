package com.example.doanquanlykhachsan.model;

public class User {
    String maFB;
    String email;
    String sdt;

    String cmnd;
    int role;

    public User(String maFB, String email, String sdt, String cmnd, int role) {
        this.maFB = maFB;
        this.email = email;
        this.sdt = sdt;
        this.cmnd = cmnd;
        this.role = role;
    }

    public User() {
    }

    public String getMaFB() {
        return maFB;
    }

    public void setMaFB(String maFB) {
        this.maFB = maFB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }



    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}