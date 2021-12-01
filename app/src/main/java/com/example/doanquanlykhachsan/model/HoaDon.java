package com.example.doanquanlykhachsan.model;

import java.io.Serializable;

public class HoaDon implements Serializable {
    int stt;
    String maFB;
    String maKH;
    String maNV;
    String ngaylap;
    float tongTien;

    public HoaDon() {
    }

    public HoaDon(int stt, String maFB, String maKH, String maNV, String ngaylap, float tongTien) {
        this.stt = stt;
        this.maFB = maFB;
        this.maKH = maKH;
        this.maNV = maNV;
        this.ngaylap = ngaylap;
        this.tongTien = tongTien;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getMaFB() {
        return maFB;
    }

    public void setMaFB(String maFB) {
        this.maFB = maFB;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getNgaylap() {
        return ngaylap;
    }

    public void setNgaylap(String ngaylap) {
        this.ngaylap = ngaylap;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }
}
