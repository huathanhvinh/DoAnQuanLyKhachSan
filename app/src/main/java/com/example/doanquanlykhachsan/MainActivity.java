package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.NhanVien;

public class MainActivity extends AppCompatActivity {
    //StaticConfig st = new StaticConfig();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        String key = StaticConfig.mNhanVien.push().getKey();
//        NhanVien nv = new NhanVien(4,key,"Trần Phi Du","0933123004","Đồng Nai","01/01/2003","1192102102","10,000,000","Tối","Quản Lý");
//        StaticConfig.mNhanVien.child(key).setValue(nv);

//        String key = StaticConfig.mKhachHang.push().getKey();
//        KhachHang kh = new KhachHang(4,key,"Dịp Tú Tèo","0933123008","Q9, HCM","346081888");
//        StaticConfig.mKhachHang.child(key).setValue(kh);
        startActivity(new Intent(getApplicationContext(), AD_HienThiDanhSachKhachHang.class));

    }

}