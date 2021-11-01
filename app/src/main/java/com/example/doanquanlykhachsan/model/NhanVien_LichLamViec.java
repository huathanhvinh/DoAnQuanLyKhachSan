package com.example.doanquanlykhachsan.model;

import java.io.Serializable;

public class NhanVien_LichLamViec extends NhanVien implements Serializable {
    String ghiChu;

    public NhanVien_LichLamViec() {
    }

    public NhanVien_LichLamViec(int stt, String maFB, String tenNV, String soDienThoai, String diaChi, String ngaySinh, String cmnd, String luong, String caLam, String chucVu) {
        super(stt, maFB, tenNV, soDienThoai, diaChi, ngaySinh, cmnd, luong, caLam, chucVu);
    }

    public NhanVien_LichLamViec(int stt, String maFB, String tenNV, String soDienThoai, String diaChi, String ngaySinh, String cmnd, String luong, String caLam, String chucVu, String ghiChu) {
        super(stt, maFB, tenNV, soDienThoai, diaChi, ngaySinh, cmnd, luong, caLam, chucVu);
        this.ghiChu = ghiChu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "NhanVien_LichLamViec{" +
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