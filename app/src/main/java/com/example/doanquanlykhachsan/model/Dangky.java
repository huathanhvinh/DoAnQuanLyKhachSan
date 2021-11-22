package com.example.doanquanlykhachsan.model;

public class Dangky {
    String maphong;
    String makh;
    String ngaybatdau;

    public Dangky(String maphong, String makh, String ngaybatdau) {
        this.maphong = maphong;
        this.makh = makh;
        this.ngaybatdau = ngaybatdau;
    }

    public Dangky() {
    }

    public String getMaphong() {
        return maphong;
    }

    public void setMaphong(String maphong) {
        this.maphong = maphong;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(String ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }
}
