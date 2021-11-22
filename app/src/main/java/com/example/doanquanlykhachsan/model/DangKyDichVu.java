package com.example.doanquanlykhachsan.model;

public class DangKyDichVu {
    String tenDichVu,xemPhong;

    public DangKyDichVu() {
    }

    public DangKyDichVu(String tenDichVu, String xemPhong) {
        this.tenDichVu = tenDichVu;
        this.xemPhong = xemPhong;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public String getXemPhong() {
        return xemPhong;
    }

    public void setXemPhong(String xemPhong) {
        this.xemPhong = xemPhong;
    }

    @Override
    public String toString() {
        return "DangKyDichVu{" +
                "tenDichVu='" + tenDichVu + '\'' +
                ", xemPhong='" + xemPhong + '\'' +
                '}';
    }
}
