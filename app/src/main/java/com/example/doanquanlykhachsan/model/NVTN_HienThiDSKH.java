package com.example.doanquanlykhachsan.model;

import java.io.Serializable;

public class NVTN_HienThiDSKH implements Serializable {
    private String maKH , tenKH,diaChi,chungminhND,sdtLienLac,hoiVien;

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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getChungminhND() {
        return chungminhND;
    }

    public void setChungminhND(String chungminhND) {
        this.chungminhND = chungminhND;
    }

    public String getSdtLienLac() {
        return sdtLienLac;
    }

    public void setSdtLienLac(String sdtLienLac) {
        this.sdtLienLac = sdtLienLac;
    }

    public String getHoiVien() {
        return hoiVien;
    }

    public void setHoiVien(String hoiVien) {
        this.hoiVien = hoiVien;
    }

    public NVTN_HienThiDSKH(String maKH, String tenKH, String diaChi, String chungminhND, String sdtLienLac, String hoiVien) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.chungminhND = chungminhND;
        this.sdtLienLac = sdtLienLac;
        this.hoiVien = hoiVien;
    }

    public NVTN_HienThiDSKH() {
    }
}
