package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.KhachHang;
import com.example.doanquanlykhachsan.model.KhuyenMai;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.example.doanquanlykhachsan.model.Phong;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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

        //Công việc cho ngày 17.11 --
        //Thêm dịch vụ
        //fix lỗi vietkey tại các edittext hiển thị
        //làm thêm nút refresh tại cách màn hình danh sách

        startActivity(new Intent(getApplicationContext(),AD_MenuAdmin.class));

    }

}