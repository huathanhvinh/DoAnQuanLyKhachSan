package com.example.doanquanlykhachsan.model;

import java.io.Serializable;

public class NhanVien_LichLamViec extends NhanVien implements Serializable {
    public NhanVien_LichLamViec(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public NhanVien_LichLamViec(int stt, String maFB, String tenNV, String soDienThoai, String diaChi, String ngaySinh, String cmnd, String luong, String caLam, String chucVu, String ghiChu) {
        super(stt, maFB, tenNV, soDienThoai, diaChi, ngaySinh, cmnd, luong, caLam, chucVu);
        this.ghiChu = ghiChu;
    }

    String ghiChu;

    public NhanVien_LichLamViec() {
    }
}
