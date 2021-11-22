package com.example.doanquanlykhachsan.model;

import java.io.Serializable;

public class Phong implements Serializable {
    String maFB;
    String maPhong;
    String tenPhong;
    String soPhong;
    int lau;
    String loai;
    String moTa;
    float giaNgay;
    float giaGio;
    String trangThai;

    @Override
    public String toString() {
        return "Phong{" +
                "maFB='" + maFB + '\'' +
                ", maPhong='" + maPhong + '\'' +
                ", tenPhong='" + tenPhong + '\'' +
                ", soPhong='" + soPhong + '\'' +
                ", lau=" + lau +
                ", loai='" + loai + '\'' +
                ", moTa='" + moTa + '\'' +
                ", giaNgay=" + giaNgay +
                ", giaGio=" + giaGio +
                ", trangThai='" + trangThai + '\'' +
                '}';
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

    public String getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(String soPhong) {
        this.soPhong = soPhong;
    }

    public int getLau() {
        return lau;
    }

    public void setLau(int lau) {
        this.lau = lau;
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

    public float getGiaNgay() {
        return giaNgay;
    }

    public void setGiaNgay(float giaNgay) {
        this.giaNgay = giaNgay;
    }

    public float getGiaGio() {
        return giaGio;
    }

    public void setGiaGio(float giaGio) {
        this.giaGio = giaGio;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Phong(String maFB, String maPhong, String tenPhong, String soPhong, int lau, String loai, String moTa, float giaNgay, float giaGio, String trangThai) {
        this.maFB = maFB;
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.soPhong = soPhong;
        this.lau = lau;
        this.loai = loai;
        this.moTa = moTa;
        this.giaNgay = giaNgay;
        this.giaGio = giaGio;
        this.trangThai = trangThai;
    }

    public Phong() {
    }
}
