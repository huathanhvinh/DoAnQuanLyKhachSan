package com.example.doanquanlykhachsan.helpers;

import com.example.doanquanlykhachsan.model.DichVu;
import com.example.doanquanlykhachsan.model.Phong;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Date;

public class StaticConfig {
    public static FirebaseDatabase Database = FirebaseDatabase.getInstance();
    public static FirebaseAuth fAuth = FirebaseAuth.getInstance();
    public static FirebaseStorage storage = FirebaseStorage.getInstance();
    public static StorageReference storageReference = storage.getReference();
    public static String currentuser = "";
    public static String currentphone = "";
    public static int role;
    public static String currentCmnd = "";
    public static String Loai = "";
    public static String songay = "";
    public static ArrayList<String> loaiNgay = new ArrayList<>();
    public static ArrayList<String> loaiGio = new ArrayList<>();
    //so thong bao
    public static int sothongbao = 0;


    public static DatabaseReference mUser = Database.getReference("User");
    public static DatabaseReference mDangky = Database.getReference("Dangky");

    //KET NOI VOI NHANVIEN TREN FIREBASE
    public static DatabaseReference mNhanVien = Database.getReference("NhanVien");
    public static DatabaseReference mKhachHang = Database.getReference("KhachHang");
    public static DatabaseReference mDichVu = Database.getReference("DichVu");
    public static DatabaseReference mNhanVien_Luong = Database.getReference("NhanVien_Luong");
    public static DatabaseReference mNhanVien_LichLamViec = Database.getReference("NhanVien_LichLamViec");
    public static DatabaseReference mKhuyenMai = Database.getReference("KhuyenMai");
    public static DatabaseReference mPhong = Database.getReference("Phong");

    public static DatabaseReference mRoom = Database.getReference("Phong");
    public static DatabaseReference mRoomRented = Database.getReference("PhongDaDat");
    public static DatabaseReference mThongBao = Database.getReference("Thongbao");
    public static DatabaseReference mDoiPhong = Database.getReference("DoiPhong");
    //them du lieu kh??ch h??ng

    //th??m d??? li???u h??a ????n
    public static DatabaseReference mHoaDon = Database.getReference("HoaDon");
    //them du lieu phong

    public static DatabaseReference mQLPhong = Database.getReference("QLPhong");

    public static DatabaseReference mDichVuDaChon = Database.getReference("DichVuDaChon");


    //Danh s??ch ph??ng t???m th???i
    public static ArrayList<Phong> arrayListTemporaryRoom = new ArrayList<>();
    //Danh s??ch d???ch v??? t???m th???i
    public static ArrayList<DichVu> arrayListTemporaryService = new ArrayList<>();
    //phan biet man hinh theo ngay hay gio
    public static String sXacNhan = "";
    //ma thue
    public static String mathue = "";
    //T???ng ti???n
    public static float Tongtien;
    //check all
    public static boolean isCheckAll = false;
    //chon phong de doi
    public static Phong chon = new Phong();
    //ngay nhan phong tu man hinh chi tiet
    public static String NgayNhanXacNhanPhong = "";
    //ngay tra phong tu man hinh chi tiet
    public static String NgayNhanXacTraPhong = "";


    public static DatabaseReference mThongtinDv = Database.getReference("Thongtindv");

    public static String Anh_mac_dinh = "https://firebasestorage.googleapis.com/v0/b/doanquanlykhachsan.appspot.com/o/Default%20avatar%2Favatar.jpg?alt=media&token=8238b839-bbcf-4712-ba53-1730d28720a8";
    public static final int PICK_IMAGE_REQUEST = 10;
    //timestamp
    public static Date now = new Date();
    public static long timestamp = now.getTime();
}
