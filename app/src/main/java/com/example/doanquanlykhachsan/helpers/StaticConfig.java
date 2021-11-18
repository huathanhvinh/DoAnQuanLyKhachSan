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
    public static String currentphone = "";


    public static DatabaseReference mUser = Database.getReference("User");

    //them du lieu phong
    public static DatabaseReference mRoom = Database.getReference("Phong");
    public static DatabaseReference mQLPhong = Database.getReference("QLPhong");
    public static DatabaseReference mDichVu = Database.getReference("DichVu");
    public static DatabaseReference mNhanVien = Database.getReference("NhanVien");
    public static DatabaseReference mNhanVien_Luong = Database.getReference("NhanVien_Luong");
    public static DatabaseReference mNhanVien_LichLamViec = Database.getReference("NhanVien_LichLamViec");
    public static DatabaseReference mLuong = Database.getReference("NhanVien_Luong");
    public static DatabaseReference mRoomRented = Database.getReference("RoomRented");
    public static DatabaseReference mSelectedService = Database.getReference("SelectedService");
    public static DatabaseReference mDichVuDaChon = Database.getReference("DichVuDaChon");


    public static final int PICK_IMAGE_REQUEST = 10;
    //timestamp
    public static Long tsLong = System.currentTimeMillis() / 1000;
    public static String timestamp = tsLong.toString();
}
