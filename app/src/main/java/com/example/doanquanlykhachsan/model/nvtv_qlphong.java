package com.example.doanquanlykhachsan.model;

public class nvtv_qlphong {
    String phong,kiemtra;

    public nvtv_qlphong() {
    }

    public nvtv_qlphong(String phong, String kiemtra) {
        this.phong = phong;
        this.kiemtra = kiemtra;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getKiemtra() {
        return kiemtra;
    }

    public void setKiemtra(String kiemtra) {
        this.kiemtra = kiemtra;
    }

    @Override
    public String toString() {
        return "nvtv_qlphong{" +
                "phong='" + phong + '\'' +
                ", kiemtra='" + kiemtra + '\'' +
                '}';
    }
}
