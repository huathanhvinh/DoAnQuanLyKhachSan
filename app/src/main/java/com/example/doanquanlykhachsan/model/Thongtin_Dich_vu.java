package com.example.doanquanlykhachsan.model;

public class Thongtin_Dich_vu {
    String madv;
    String makh;
    String maphong;

    public Thongtin_Dich_vu() {
    }
    public Thongtin_Dich_vu(String madv, String makh, String maphong) {
        this.madv = madv;
        this.makh = makh;
        this.maphong = maphong;
    }

    public String getMaphong() {
        return maphong;
    }

    public void setMaphong(String maphong) {
        this.maphong = maphong;
    }

    public String getMadv() {
        return madv;
    }

    public void setMadv(String madv) {
        this.madv = madv;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }
}
