package com.example.doanquanlykhachsan.model;

public class Khuyenmai {
    String ma;
    String ten;
    String Ngaybatdau;
    String NgayKetthuc;

    public Khuyenmai(String ma, String ten, String ngaybatdau, String ngayKetthuc) {
        this.ma = ma;
        this.ten = ten;
        Ngaybatdau = ngaybatdau;
        NgayKetthuc = ngayKetthuc;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgaybatdau() {
        return Ngaybatdau;
    }

    public void setNgaybatdau(String ngaybatdau) {
        Ngaybatdau = ngaybatdau;
    }

    public String getNgayKetthuc() {
        return NgayKetthuc;
    }

    public void setNgayKetthuc(String ngayKetthuc) {
        NgayKetthuc = ngayKetthuc;
    }
}
