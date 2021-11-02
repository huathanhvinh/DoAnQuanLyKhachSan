package com.example.doanquanlykhachsan.model;

import java.io.Serializable;

public class KhuyenMai implements Serializable {
    String maFB;
    String maKM;
    String tenKM;
    String ngayBatDau;
    String ngayKetThuc;
    int mucGiamGia;
    String noiDung;
    String trangThai;

    public KhuyenMai() {
    }

    public KhuyenMai(String maFB, String maKM, String tenKM, String ngayBatDau, String ngayKetThuc, int mucGiamGia, String noiDung, String trangThai) {
        this.maFB = maFB;
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.mucGiamGia = mucGiamGia;
        this.noiDung = noiDung;
        this.trangThai = trangThai;
    }

    public String getMaFB() {
        return maFB;
    }

    public void setMaFB(String maFB) {
        this.maFB = maFB;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getMucGiamGia() {
        return mucGiamGia;
    }

    public void setMucGiamGia(int mucGiamGia) {
        this.mucGiamGia = mucGiamGia;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    private String CheckTrangThai(String ngayBatDau, String ngayKetThuc, String ngayHienTai)
    {
        return "";
    }
}
