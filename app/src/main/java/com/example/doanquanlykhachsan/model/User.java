package com.example.doanquanlykhachsan.model;

public class User {
    String id;
    String email;
    String Sdt;

    public User(String id, String email, String sdt) {
        this.id = id;
        this.email = email;
        Sdt = sdt;

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

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }
}
