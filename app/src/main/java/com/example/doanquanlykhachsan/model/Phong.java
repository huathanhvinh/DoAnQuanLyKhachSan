package com.example.doanquanlykhachsan.model;

import java.io.Serializable;

public class Phong implements Serializable {
    String maFB,maPhong,tenPhong,trangThai,loai,moTa,soPhong;
    int lau,giaNgay,giaGio;

    public Phong() {
    }

    public Phong(String maFB, String maPhong, String tenPhong, String trangThai, String loai, String moTa, String soPhong, int lau, int giaNgay, int giaGio) {
        this.maFB = maFB;
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.trangThai = trangThai;
        this.loai = loai;
        this.moTa = moTa;
        this.soPhong = soPhong;
        this.lau = lau;
        this.giaNgay = giaNgay;
        this.giaGio = giaGio;
    }

    public String getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(String soPhong) {
        this.soPhong = soPhong;
    }

    public String getMaFB() {
        return maFB;
    }

    public void setMaFB(String maFB) {
        this.maFB = maFB;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getLau() {
        return lau;
    }

    public void setLau(int lau) {
        this.lau = lau;
    }

    public int getGiaNgay() {
        return giaNgay;
    }

    public void setGiaNgay(int giaNgay) {
        this.giaNgay = giaNgay;
    }

    public int getGiaGio() {
        return giaGio;
    }

    public void setGiaGio(int giaGio) {
        this.giaGio = giaGio;
    }
}
