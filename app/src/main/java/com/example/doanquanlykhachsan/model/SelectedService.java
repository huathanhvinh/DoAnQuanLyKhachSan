package com.example.doanquanlykhachsan.model;

public class SelectedService {
    String sMaFB;
    String sMaDV;
    String sTenDV;
    String sMaPH;

    @Override
    public String toString() {
        return "QuanLyDichVu{" +
                "sMaFB='" + sMaFB + '\'' +
                ", sMaDV='" + sMaDV + '\'' +
                ", sTenDV='" + sTenDV + '\'' +
                ", sMaPH='" + sMaPH + '\'' +
                '}';
    }

    public String getsMaFB() {
        return sMaFB;
    }

    public void setsMaFB(String sMaFB) {
        this.sMaFB = sMaFB;
    }

    public String getsMaDV() {
        return sMaDV;
    }

    public void setsMaDV(String sMaDV) {
        this.sMaDV = sMaDV;
    }

    public String getsTenDV() {
        return sTenDV;
    }

    public void setsTenDV(String sTenDV) {
        this.sTenDV = sTenDV;
    }

    public String getsMaPH() {
        return sMaPH;
    }

    public void setsMaPH(String sMaPH) {
        this.sMaPH = sMaPH;
    }

    public SelectedService() {
    }

    public SelectedService(String sMaFB, String sMaDV, String sTenDV, String sMaPH) {
        this.sMaFB = sMaFB;
        this.sMaDV = sMaDV;
        this.sTenDV = sTenDV;
        this.sMaPH = sMaPH;
    }
}
