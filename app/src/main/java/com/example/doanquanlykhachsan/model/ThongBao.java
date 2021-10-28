package com.example.doanquanlykhachsan.model;

public class ThongBao {
    String ID,NoiDung,Gio,Ngay,DangKyDichVu,ChiTiet,XacNhan;

    public ThongBao() {
    }

    public ThongBao(String ID, String noiDung, String gio, String ngay, String dangKyDichVu, String chiTiet, String xacNhan) {
        this.ID = ID;
        NoiDung = noiDung;
        Gio = gio;
        Ngay = ngay;
        DangKyDichVu = dangKyDichVu;
        ChiTiet = chiTiet;
        XacNhan = xacNhan;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getGio() {
        return Gio;
    }

    public void setGio(String gio) {
        Gio = gio;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }

    public String getDangKyDichVu() {
        return DangKyDichVu;
    }

    public void setDangKyDichVu(String dangKyDichVu) {
        DangKyDichVu = dangKyDichVu;
    }

    public String getChiTiet() {
        return ChiTiet;
    }

    public void setChiTiet(String chiTiet) {
        ChiTiet = chiTiet;
    }

    public String getXacNhan() {
        return XacNhan;
    }

    public void setXacNhan(String xacNhan) {
        XacNhan = xacNhan;
    }

    @Override
    public String toString() {
        return "ThongBao{" +
                "ID='" + ID + '\'' +
                ", NoiDung='" + NoiDung + '\'' +
                ", Gio='" + Gio + '\'' +
                ", Ngay='" + Ngay + '\'' +
                ", DangKyDichVu='" + DangKyDichVu + '\'' +
                ", ChiTiet='" + ChiTiet + '\'' +
                ", XacNhan='" + XacNhan + '\'' +
                '}';
    }
}
