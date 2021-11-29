package com.example.doanquanlykhachsan.model;

import com.example.doanquanlykhachsan.helpers.StaticConfig;

public class ThongBao {
    int stt;
    String maFB;
    String loai;
    String trangThai;
    String mota;
    String nguoidang;
    long timestamp;

    public ThongBao(int stt, String maFB, String loai, String trangThai, String mota, String nguoidang) {
        this.stt = stt;
        this.maFB = maFB;
        this.loai = loai;
        this.trangThai = trangThai;
        this.mota = mota;
        this.nguoidang = nguoidang;
        this.timestamp = StaticConfig.timestamp;
    }

    public ThongBao() {
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNguoidang() {
        return nguoidang;
    }

    public void setNguoidang(String nguoidang) {
        this.nguoidang = nguoidang;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getMaFB() {
        return maFB;
    }

    public void setMaFB(String maFB) {
        this.maFB = maFB;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
