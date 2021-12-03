package com.example.doanquanlykhachsan.model;

import java.io.Serializable;

public class PhongDaDat implements Serializable {
    String maFB;
    String maKH;
    String maPhong;
    String maDichVu;
    String thoiGianNhanPH;
    String thoiGianTraPH;
    String manHinh;//phan biet man hinh theo ngay hay gio
    String ghiChuKH;
    String xacnhan;
    String ngaybatdau;
    String sdt;
    int stt;

    public PhongDaDat() {
    }

    public PhongDaDat(String maFB, String maKH, String maPhong, String maDichVu, String thoiGianNhanPH, String thoiGianTraPH, String manHinh, String ghiChuKH, String xacnhan, String ngaybatdau, String sdt, int stt) {
        this.maFB = maFB;
        this.maKH = maKH;
        this.maPhong = maPhong;
        this.maDichVu = maDichVu;
        this.thoiGianNhanPH = thoiGianNhanPH;
        this.thoiGianTraPH = thoiGianTraPH;
        this.manHinh = manHinh;
        this.ghiChuKH = ghiChuKH;
        this.xacnhan = xacnhan;
        this.ngaybatdau = ngaybatdau;
        this.sdt = sdt;
        this.stt = stt;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(String ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(String maDichVu) {
        this.maDichVu = maDichVu;
    }

    public String getXacnhan() {
        return xacnhan;
    }

    public void setXacnhan(String xacnhan) {
        this.xacnhan = xacnhan;
    }

    public String getMaFB() {
        return maFB;
    }

    public void setMaFB(String maFB) {
        this.maFB = maFB;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getThoiGianNhanPH() {
        return thoiGianNhanPH;
    }

    public void setThoiGianNhanPH(String thoiGianNhanPH) {
        this.thoiGianNhanPH = thoiGianNhanPH;
    }

    public String getThoiGianTraPH() {
        return thoiGianTraPH;
    }

    public void setThoiGianTraPH(String thoiGianTraPH) {
        this.thoiGianTraPH = thoiGianTraPH;
    }

    public String getManHinh() {
        return manHinh;
    }

    public void setManHinh(String manHinh) {
        this.manHinh = manHinh;
    }

    public String getGhiChuKH() {
        return ghiChuKH;
    }

    public void setGhiChuKH(String ghiChuKH) {
        this.ghiChuKH = ghiChuKH;
    }



}
