package com.example.doanquanlykhachsan.model;

import java.io.Serializable;

public class HoaDon implements Serializable {
    int stt;
    String maFB;
    String maKH;
    String maNV;
    String ngaylap;
    String ngayden;
    String ngaydi;
    String tongThoigian;
    String phanloai;
    float tongTien;

    public HoaDon() {
    }

    public HoaDon(int stt, String maFB, String maKH, String maNV, String ngaylap, String ngayden, String ngaydi, String tongThoigian, String phanloai, float tongTien) {
        this.stt = stt;
        this.maFB = maFB;
        this.maKH = maKH;
        this.maNV = maNV;
        this.ngaylap = ngaylap;
        this.ngayden = ngayden;
        this.ngaydi = ngaydi;
        this.tongThoigian = tongThoigian;
        this.phanloai = phanloai;
        this.tongTien = tongTien;
    }

    public String getNgayden() {
        return ngayden;
    }

    public void setNgayden(String ngayden) {
        this.ngayden = ngayden;
    }

    public String getNgaydi() {
        return ngaydi;
    }

    public void setNgaydi(String ngaydi) {
        this.ngaydi = ngaydi;
    }

    public String getTongThoigian() {
        return tongThoigian;
    }

    public void setTongThoigian(String tongThoigian) {
        this.tongThoigian = tongThoigian;
    }

    public String getPhanloai() {
        return phanloai;
    }

    public void setPhanloai(String phanloai) {
        this.phanloai = phanloai;
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
