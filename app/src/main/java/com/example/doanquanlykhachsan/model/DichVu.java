package com.example.doanquanlykhachsan.model;

public class DichVu {
    String maDichVu;
    String tenDichVu;
    float giaDichVu;
    String motaDichVu;


    public DichVu(String maDichVu, String tenDichVu, float giaDichVu, String motaDichVu) {
        this.maDichVu = maDichVu;
        this.tenDichVu = tenDichVu;
        this.giaDichVu = giaDichVu;
        this.motaDichVu = motaDichVu;
    }
    public DichVu(){

    }

    public String getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(String maDichVu) {
        this.maDichVu = maDichVu;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public float getGiaDichVu() {
        return giaDichVu;
    }

    public void setGiaDichVu(float giaDichVu) {
        this.giaDichVu = giaDichVu;
    }

    public String getMotaDichVu() {
        return motaDichVu;
    }

    public void setMotaDichVu(String motaDichVu) {
        this.motaDichVu = motaDichVu;
    }
}
