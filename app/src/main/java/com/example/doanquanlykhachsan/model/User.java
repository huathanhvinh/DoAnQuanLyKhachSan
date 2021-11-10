package com.example.doanquanlykhachsan.model;

public class User {
    String id;
    String email;
    String Sdt;
    String password;
    int role;

    public User(String id, String email, String sdt, String password, int role) {
        this.id = id;
        this.email = email;
        Sdt = sdt;
        this.password = password;
        this.role = role;
    }
    public User (){

    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
