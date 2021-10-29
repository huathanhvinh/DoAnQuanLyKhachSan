package com.example.doanquanlykhachsan.helpers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class StaticConfig {
    public static FirebaseDatabase Database = FirebaseDatabase.getInstance();
    public static FirebaseAuth fAuth = FirebaseAuth.getInstance();
    public static FirebaseStorage storage = FirebaseStorage.getInstance();
    public static StorageReference storageReference = storage.getReference();
    public static String currentuser = "";
    public static DatabaseReference mUser = Database.getReference("User");
    public static DatabaseReference mDangky = Database.getReference("Dangky");
    //them du lieu phong
    public static DatabaseReference mRoom = Database.getReference("Room");

    public static DatabaseReference mThongtinDv = Database.getReference("Thongtindv");
    public static DatabaseReference mDichvu = Database.getReference("Dichvu");
    public static String Anh_mac_dinh= "https://firebasestorage.googleapis.com/v0/b/doanquanlykhachsan.appspot.com/o/Default%20avatar%2Favatar.jpg?alt=media&token=8238b839-bbcf-4712-ba53-1730d28720a8";
    public static final int PICK_IMAGE_REQUEST = 10;
    //timestamp
    public static Long tsLong = System.currentTimeMillis() / 1000;
    public static String timestamp = tsLong.toString();
}