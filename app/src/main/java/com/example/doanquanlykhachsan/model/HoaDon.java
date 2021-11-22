package com.example.doanquanlykhachsan.model;

import java.io.Serializable;

public class HoaDon implements Serializable {
    public HoaDon(int stt, String tenKH, String tenNV, String ngayLap, String maFB) {
        this.stt = stt;
        this.tenKH = tenKH;
        this.tenNV = tenNV;
        NgayLap = ngayLap;
        this.maFB = maFB;
    }

    public HoaDon() {
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(String ngayLap) {
        NgayLap = ngayLap;
    }

    public String getMaFB() {
        return maFB;
    }

    public void setMaFB(String maFB) {
        this.maFB = maFB;
    }

    int stt;
    String tenKH;
    String tenNV;
    String NgayLap;
    String maFB;


}
