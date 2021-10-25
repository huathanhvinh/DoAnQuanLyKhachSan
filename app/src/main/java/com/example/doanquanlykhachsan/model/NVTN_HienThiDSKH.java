package com.example.doanquanlykhachsan.model;

public class NVTN_HienThiDSKH {
    private String maKH , tenKH ;

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public NVTN_HienThiDSKH(String maKH, String tenKH) {
        this.maKH = maKH;
        this.tenKH = tenKH;
    }

    public NVTN_HienThiDSKH() {
    }
}
