package com.example.doanquanlykhachsan.model;


import java.io.Serializable;

public class DichVu implements Serializable {
    int stt;
    String maFB;
    String tenDV;
    int giaDV;
    String dvt;
    int soLan;
    String mota;

    public DichVu() {
    }

    public DichVu(int stt, String maFB, String tenDV, int giaDV, String dvt, int soLan, String mota) {
        this.stt = stt;
        this.maFB = maFB;
        this.tenDV = tenDV;
        this.giaDV = giaDV;
        this.dvt = dvt;
        this.soLan = soLan;
        this.mota = mota;
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

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public int getGiaDV() {
        return giaDV;
    }

    public void setGiaDV(int giaDV) {
        this.giaDV = giaDV;
    }

    public String getDvt() {
        return dvt;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public int getSoLan() {
        return soLan;
    }

    public void setSoLan(int soLan) {
        this.soLan = soLan;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    private int TinhTien() {
        return this.soLan * this.giaDV;
    }

    @Override
    public String toString() {
        return "DichVu{" +
                "stt=" + stt +
                ", maFB='" + maFB + '\'' +
                ", tenDV='" + tenDV + '\'' +
                ", giaDV=" + giaDV +
                ", dvt='" + dvt + '\'' +
                ", soLan=" + soLan +
                ", mota='" + mota + '\'' +
                '}';
    }
}



