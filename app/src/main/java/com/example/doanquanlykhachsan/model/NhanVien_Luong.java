package com.example.doanquanlykhachsan.model;

import java.io.Serializable;

public class NhanVien_Luong extends NhanVien implements Serializable {
    String tienThuong;
    String ghiChu;

    public NhanVien_Luong() {
    }

    public NhanVien_Luong(int stt, String maFB, String tenNV, String soDienThoai, String diaChi, String ngaySinh, String cmnd, String luong, String caLam, String chucVu, String tienThuong, String ghiChu) {
        super(stt, maFB, tenNV, soDienThoai, diaChi, ngaySinh, cmnd, luong, caLam, chucVu);
        this.tienThuong = tienThuong;
        this.ghiChu = ghiChu;
    }

    public String getTienThuong() {
        return tienThuong;
    }

    public void setTienThuong(String tienThuong) {
        this.tienThuong = tienThuong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "NhanVien_Luong{" +
                "stt=" + stt +
                ", maFB='" + maFB + '\'' +
                ", tenNV='" + tenNV + '\'' +
                ", SoDienThoai='" + SoDienThoai + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", ngaySinh='" + ngaySinh + '\'' +
                ", cmnd='" + cmnd + '\'' +
                ", luong='" + luong + '\'' +
                ", caLam='" + caLam + '\'' +
                ", chucVu='" + chucVu + '\'' +
                ", ghiChu='" + ghiChu + '\'' +
                '}';
    }
}
