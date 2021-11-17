package com.example.doanquanlykhachsan.model;

public class PhongDaDat {
    String maFB;
    String maKH;
    String maPhong;
    String thoiGianNhanPH;
    String thoiGianTraPH;
    String manHinh;//phan biet man hinh theo ngay hay gio
    String ghiChuKH;

    @Override
    public String toString() {
        return "PhongDaDat{" +
                "maFB='" + maFB + '\'' +
                ", maKH='" + maKH + '\'' +
                ", maPhong='" + maPhong + '\'' +
                ", thoiGianNhanPH='" + thoiGianNhanPH + '\'' +
                ", thoiGianTraPH='" + thoiGianTraPH + '\'' +
                ", manHinh='" + manHinh + '\'' +
                ", ghiChuKH='" + ghiChuKH + '\'' +
                '}';
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

    public PhongDaDat(String maFB, String maKH, String maPhong, String thoiGianNhanPH, String thoiGianTraPH, String manHinh, String ghiChuKH) {
        this.maFB = maFB;
        this.maKH = maKH;
        this.maPhong = maPhong;
        this.thoiGianNhanPH = thoiGianNhanPH;
        this.thoiGianTraPH = thoiGianTraPH;
        this.manHinh = manHinh;
        this.ghiChuKH = ghiChuKH;
    }

    public PhongDaDat() {
    }
}
