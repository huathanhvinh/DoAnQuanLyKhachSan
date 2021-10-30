package com.example.doanquanlykhachsan.model;

public class RoomRented {
    String sMa;
    String sMaKH;
    String sMaPH;
    String sNgayNhan;
    String sNgayTra;
    String sManHinh;//phan biet man hinh theo ngay hay gio

    @Override
    public String toString() {
        return "RoomDaDat{" +
                "sMa='" + sMa + '\'' +
                ", sMaKH='" + sMaKH + '\'' +
                ", sMaPH='" + sMaPH + '\'' +
                ", sNgayNhan='" + sNgayNhan + '\'' +
                ", sNgayTra='" + sNgayTra + '\'' +
                ", sManHinh='" + sManHinh + '\'' +
                '}';
    }

    public String getsMa() {
        return sMa;
    }

    public void setsMa(String sMa) {
        this.sMa = sMa;
    }

    public String getsMaKH() {
        return sMaKH;
    }

    public void setsMaKH(String sMaKH) {
        this.sMaKH = sMaKH;
    }

    public String getsMaPH() {
        return sMaPH;
    }

    public void setsMaPH(String sMaPH) {
        this.sMaPH = sMaPH;
    }

    public String getsNgayNhan() {
        return sNgayNhan;
    }

    public void setsNgayNhan(String sNgayNhan) {
        this.sNgayNhan = sNgayNhan;
    }

    public String getsNgayTra() {
        return sNgayTra;
    }

    public void setsNgayTra(String sNgayTra) {
        this.sNgayTra = sNgayTra;
    }

    public String getsManHinh() {
        return sManHinh;
    }

    public void setsManHinh(String sManHinh) {
        this.sManHinh = sManHinh;
    }

    public RoomRented(String sMa, String sMaKH, String sMaPH, String sNgayNhan, String sNgayTra, String sManHinh) {
        this.sMa = sMa;
        this.sMaKH = sMaKH;
        this.sMaPH = sMaPH;
        this.sNgayNhan = sNgayNhan;
        this.sNgayTra = sNgayTra;
        this.sManHinh = sManHinh;
    }

    public RoomRented() {
    }
}
