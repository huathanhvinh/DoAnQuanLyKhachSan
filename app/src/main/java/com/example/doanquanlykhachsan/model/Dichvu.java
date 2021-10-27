package com.example.doanquanlykhachsan.model;

public class Dichvu {
    String ma;
    String ten;
    float gia;

    public Dichvu(String ma, String ten, float gia) {
        this.ma = ma;
        this.ten = ten;
        this.gia = gia;
    }

    public Dichvu() {
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
