package com.example.doanquanlykhachsan.model;

public class DoiPhong {
    String maFB;
    String maPhongchon;
    String getMaPhong;
    String maPhieu;
    String lydo;

    public DoiPhong() {
    }

    public DoiPhong(String maFB, String maPhongchon, String getMaPhong, String maPhieu, String lydo) {
        this.maFB = maFB;
        this.maPhongchon = maPhongchon;
        this.getMaPhong = getMaPhong;
        this.maPhieu = maPhieu;
        this.lydo = lydo;
    }

    public String getLydo() {
        return lydo;
    }

    public void setLydo(String lydo) {
        this.lydo = lydo;
    }

    public String getMaFB() {
        return maFB;
    }

    public void setMaFB(String maFB) {
        this.maFB = maFB;
    }

    public String getMaPhongchon() {
        return maPhongchon;
    }

    public void setMaPhongchon(String maPhongchon) {
        this.maPhongchon = maPhongchon;
    }

    public String getGetMaPhong() {
        return getMaPhong;
    }

    public void setGetMaPhong(String getMaPhong) {
        this.getMaPhong = getMaPhong;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }
}
