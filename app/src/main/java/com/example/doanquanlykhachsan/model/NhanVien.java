package com.example.doanquanlykhachsan.model;

import java.io.Serializable;

public class NhanVien implements Serializable {
    int stt;
    String maFB;
    String tenNV;
    String diaChi;
    String ngaySinh;
    String cmnd;
    String luong;
    String chucVu;

    public NhanVien() {
    }

    public NhanVien(int stt, String maFB, String tenNV, String diaChi, String ngaySinh, String cmnd, String luong, String chucVu) {
        this.stt = stt;
        this.maFB = maFB;
        this.tenNV = tenNV;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.cmnd = cmnd;
        this.luong = luong;
        this.chucVu = chucVu;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getMaFB() {
        return maFB;
    }

    public void setMaFB(String maFB) {
        this.maFB = maFB;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getLuong() {
        return luong;
    }

    public void setLuong(String luong) {
        this.luong = luong;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
}
