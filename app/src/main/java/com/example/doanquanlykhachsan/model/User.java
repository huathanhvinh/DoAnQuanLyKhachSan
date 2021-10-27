package com.example.doanquanlykhachsan.model;

import com.example.doanquanlykhachsan.helpers.StaticConfig;

public class User {
    String id;

    String name;
    String sdt;
    String cmnd;
    String hoivien;
    String email;

    public User(String id, String email, String sdt) {
        this.id = id;
        this.email = email;
        this.sdt = sdt;
        this.name = "";
        this.cmnd = "";
        this.hoivien = "";
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getHoivien() {
        return hoivien;
    }

    public void setHoivien(String hoivien) {
        this.hoivien = hoivien;
    }
}
