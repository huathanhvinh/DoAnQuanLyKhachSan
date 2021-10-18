package com.example.doanquanlykhachsan.model;

public class Room {
    String ma;
    String ten;
    String loai;
    String tinhtrang;
    float giangay;
    float giagio;

    public Room(String ma, String ten, String loai, String tinhtrang, float giangay, float giagio) {
        this.ma = ma;
        this.ten = ten;
        this.loai = loai;
        this.tinhtrang = tinhtrang;
        this.giangay = giangay;
        this.giagio = giagio;
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

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public float getGiangay() {
        return giangay;
    }

    public void setGiangay(float giangay) {
        this.giangay = giangay;
    }

    public float getGiagio() {
        return giagio;
    }

    public void setGiagio(float giagio) {
        this.giagio = giagio;
    }
}
