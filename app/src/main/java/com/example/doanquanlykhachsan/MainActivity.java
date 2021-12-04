package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.doanquanlykhachsan.admin.AD_HienThiDanhSachPhong;
import com.example.doanquanlykhachsan.admin.AD_MenuAdmin;
import com.example.doanquanlykhachsan.chung.*;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.*;
import com.example.doanquanlykhachsan.nhanvien_tapvu.*;
import com.example.doanquanlykhachsan.nhanvien_thungan.*;
import com.example.doanquanlykhachsan.khach_hang.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    //StaticConfig st = new StaticConfig();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String key = StaticConfig.mNhanVien.push().getKey();
//        NhanVien nv = new NhanVien(4,key,"Trần Phi Du","0933123004","Đồng Nai","01/01/2003","1192102102","10,000,000","Trưa","Quản Lý");
//        StaticConfig.mNhanVien.child(key).setValue(nv);

//        String key = StaticConfig.mKhachHang.push().getKey();
//        KhachHang kh = new KhachHang(6,key,"le hoang duy","12345678","sadas","0859300312");
//        StaticConfig.mKhachHang.child(key).setValue(kh);

//        String key = StaticConfig.mKhachHang.push().getKey();
//        KhachHang kh = new KhachHang(4,key,"Nguyễn Văn Duy","0933123008","Q9, HCM","346081888");
//        StaticConfig.mKhachHang.child(key).setValue(kh);

//        String key = StaticConfig.mDichVu.push().getKey();
//        DichVu dv = new DichVu(4,key,"Dịch vụ phục vụ phòng",50000,"Ngày",0,"Dọn phòng giúp bạn");
//        StaticConfig.mDichVu.child(key).setValue(dv);

//        String key = StaticConfig.mNhanVien_Luong.push().getKey();
//        NhanVien_Luong nv_l = new NhanVien_Luong(4,key,"Trần Phi Du","0933123004","Đồng Nai","01/01/2003","1192102102","10,000,000","Tối","Quản Lý","400,000","Thích là ghi");
//        StaticConfig.mNhanVien_Luong.child(key).setValue(nv_l);

//        String key = StaticConfig.mNhanVien_LichLamViec.push().getKey();
//        NhanVien_LichLamViec nv_lv = new NhanVien_LichLamViec(4,key,"Trần Phi Du","0933123004","Q9, HCM","01/01/2003","1192102102","10,000,000","Tối","Quản Lý","Thích là ghi");
//        StaticConfig.mNhanVien_LichLamViec.child(key).setValue(nv_lv);

//        String key3 = StaticConfig.mKhuyenMai.push().getKey();
//        KhuyenMai km3 = new KhuyenMai(key3,"00dkanxa","Lễ Khánh Thành","01/01/2021","03/01/2021",5,"Khánh thành","Đã Kết Thúc");
//        StaticConfig.mKhuyenMai.child(key3).setValue(km3);
//
//        String key = StaticConfig.mPhong.push().getKey();
//        Phong p = new Phong(key,"p10t1","Phòng 10_Tầng 1","trống","2","Phòng đẹp","10",1,200000,80000);
//        StaticConfig.mPhong.child(key).setValue(p);

//        startActivity(new Intent(getApplicationContext(), AD_HienThiDanhSachNhanVien.class));

        if (StaticConfig.fAuth.getCurrentUser() != null) {
            StaticConfig.mUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String maFB = ds.child("maFB").getValue().toString();
                        if (maFB.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                            User user = ds.getValue(User.class);
                            StaticConfig.currentphone = user.getSdt();
                            StaticConfig.currentuser = FirebaseAuth.getInstance().getUid();
                            StaticConfig.role = user.getRole();
                            StaticConfig.currentCmnd = user.getCmnd();
                            int role = user.getRole();
                            if (role == 1) {
                                startActivity(new Intent(getApplicationContext(), AD_MenuAdmin.class));
                            }
                            if (role == 2) {
                                startActivity(new Intent(getApplicationContext(), NVTN_MenuNhanVienThuNgan.class));
                            }
                            if (role == 3) {
                                startActivity(new Intent(getApplicationContext(), Nhanvientapvu_manhinhchinh.class));
                            }
                            if (role == 4) {
                                startActivity(new Intent(getApplicationContext(), menu_khachhang.class));
                            }

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            startActivity(new Intent(getApplicationContext(), NVTN_LapHoaDon.class));
        }

    }

}