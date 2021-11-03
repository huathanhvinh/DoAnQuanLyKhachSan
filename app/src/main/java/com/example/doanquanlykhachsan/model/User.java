package com.example.doanquanlykhachsan.model;

import com.example.doanquanlykhachsan.helpers.StaticConfig;

public class User {
    String id;
    String name;
    String sdt;
    String cmnd;
    String email;
    int role;

    public User(String id, String email, String sdt) {
        this.id = id;
        this.email = email;
        this.sdt = sdt;
        this.name = "";
        this.cmnd = "";
        this.role = 3;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
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


}
