package com.example.doanquanlykhachsan.model;

public class nvtv_qlphong {
    String phong;
    boolean kiemtra;

    public nvtv_qlphong() {
    }

    public nvtv_qlphong(String phong, boolean kiemtra) {
        this.phong = phong;
        this.kiemtra = kiemtra;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public boolean getKiemtra() {
        return kiemtra;
    }

    public void setKiemtra(boolean kiemtra) {
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
