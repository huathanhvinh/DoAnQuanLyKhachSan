package com.example.doanquanlykhachsan.model;

public class nvtv_qlphong {
    String phong;
    boolean kiemtra;
    boolean thucAn;
    boolean tuLanh;
    boolean ruou;
    boolean vatDung;
    boolean maKM;


    public nvtv_qlphong() {
    }

    public nvtv_qlphong(String phong, boolean kiemtra, boolean thucAn, boolean tuLanh, boolean ruou, boolean vatDung, boolean maKM) {
        this.phong = phong;
        this.kiemtra = kiemtra;
        this.thucAn = thucAn;
        this.tuLanh = tuLanh;
        this.ruou = ruou;
        this.vatDung = vatDung;
        this.maKM = maKM;
    }


    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public boolean isKiemtra() {
        return kiemtra;
    }

    public void setKiemtra(boolean kiemtra) {
        this.kiemtra = kiemtra;
    }

    public boolean isThucAn() {
        return thucAn;
    }

    public void setThucAn(boolean thucAn) {
        this.thucAn = thucAn;
    }

    public boolean isTuLanh() {
        return tuLanh;
    }

    public void setTuLanh(boolean tuLanh) {
        this.tuLanh = tuLanh;
    }

    public boolean isRuou() {
        return ruou;
    }

    public void setRuou(boolean ruou) {
        this.ruou = ruou;
    }

    public boolean isVatDung() {
        return vatDung;
    }

    public void setVatDung(boolean vatDung) {
        this.vatDung = vatDung;
    }

    public boolean isMaKM() {
        return maKM;
    }

    public void setMaKM(boolean maKM) {
        this.maKM = maKM;
    }

    @Override
    public String toString() {
        return "nvtv_qlphong{" +
                "phong='" + phong + '\'' +
                ", kiemtra=" + kiemtra +
                ", thucAn=" + thucAn +
                ", tuLanh=" + tuLanh +
                ", ruou=" + ruou +
                ", vatDung=" + vatDung +
                ", maKM=" + maKM +
                '}';
    }
}
