package com.example.doanquanlykhachsan.model;

public class DichVuDaChon {
    String maFB;
    String maDV;
    String tenDV;
    String maPhong;

    @Override
    public String toString() {
        return "QuanLyDichVu{" +
                "sMaFB='" + maFB + '\'' +
                ", sMaDV='" + maDV + '\'' +
                ", sTenDV='" + tenDV + '\'' +
                ", sMaPH='" + maPhong + '\'' +
                '}';
    }

    public String getMaFB() {
        return maFB;
    }

    public void setMaFB(String maFB) {
        this.maFB = maFB;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public DichVuDaChon() {
    }

    public DichVuDaChon(String maFB, String maDV, String tenDV, String maPhong) {
        this.maFB = maFB;
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.maPhong = maPhong;
    }
}