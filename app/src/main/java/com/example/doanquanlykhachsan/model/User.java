package com.example.doanquanlykhachsan.model;

public class User {
    String id;
    String email;
    String Sdt;
    int role;

    public User(String id, String email, String sdt, int role) {
        this.id = id;
        this.email = email;
        Sdt = sdt;
        this.role = role;
    }
    public User(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
