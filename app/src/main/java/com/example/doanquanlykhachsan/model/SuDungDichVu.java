package com.example.doanquanlykhachsan.model;

public class SuDungDichVu {
    String phong,HuyDichVu;

    public SuDungDichVu() {
    }

    public SuDungDichVu(String phong, String huyDichVu) {
        this.phong = phong;
        HuyDichVu = huyDichVu;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getHuyDichVu() {
        return HuyDichVu;
    }

    public void setHuyDichVu(String huyDichVu) {
        HuyDichVu = huyDichVu;
    }

    @Override
    public String toString() {
        return "SuDungDichVu{" +
                "phong='" + phong + '\'' +
                ", HuyDichVu='" + HuyDichVu + '\'' +
                '}';
    }
}
