package com.example.doanquanlykhachsan.helpers;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class StaticConfig {
    public static FirebaseDatabase Database = FirebaseDatabase.getInstance();
    public static FirebaseAuth fAuth = FirebaseAuth.getInstance();
    public static FirebaseStorage storage = FirebaseStorage.getInstance();
    public static StorageReference storageReference = storage.getReference();
    public static String currentuser = "";
    public static DatabaseReference mUser = Database.getReference("User");
    //them du lieu khách hàng
    public static DatabaseReference mKhachHang = Database.getReference("KhachHang");
    //thêm dữ liệu hóa đơn
    public static DatabaseReference mHoaDon = Database.getReference("HoaDon");
    //them du lieu phong
    public static DatabaseReference mRoom = Database.getReference("Room");
    //nhan vien
    public static DatabaseReference mNhanVien = Database.getReference("NhanVien");

    public static DatabaseReference mNhanVien_Luong = Database.getReference("NhanVien_Luong");

    public static DatabaseReference mNhanVien_LichLamViec = Database.getReference("NhanVien_LichLamviec");
    //sdt cua user hien tai
    public static String currentphone = "";
    public static final int PICK_IMAGE_REQUEST = 10;
    //timestamp
    public static Long tsLong = System.currentTimeMillis() / 1000;
    public static String timestamp = tsLong.toString();
}
