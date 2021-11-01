package com.example.doanquanlykhachsan.model;

import java.io.Serializable;

public class NVTN_HoaDon implements Serializable {
   private String tenKH;

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public NVTN_HoaDon() {
    }

    public NVTN_HoaDon(String tenKH) {
        this.tenKH = tenKH;
    }
}
