package com.example.doanquanlykhachsan.model;

import java.io.Serializable;

public class KhachHang implements Serializable {
    int stt;
    String maFB;
    String tenKH;
    String sdtKH;
    String diaChi;
    String cmnd;

    public KhachHang() {
    }

    public KhachHang(int stt, String maFB, String tenKH, String sdtKH, String diaChi, String cmnd) {
        this.stt = stt;
        this.maFB = maFB;
        this.tenKH = tenKH;
        this.sdtKH = sdtKH;
        this.diaChi = diaChi;
        this.cmnd = cmnd;
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

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "stt=" + stt +
                ", maFB='" + maFB + '\'' +
                ", tenKH='" + tenKH + '\'' +
                ", sdtKH='" + sdtKH + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", cmnd='" + cmnd + '\'' +
                '}';
    }
}