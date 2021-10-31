package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.DichVu;
import com.example.doanquanlykhachsan.model.KhachHang;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.example.doanquanlykhachsan.model.NhanVien_Luong;

public class MainActivity extends AppCompatActivity {
    //StaticConfig st = new StaticConfig();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        String key = StaticConfig.mNhanVien.push().getKey();
//        NhanVien nv = new NhanVien(4,key,"Trần Phi Du","0933123004","Đồng Nai","01/01/2003","1192102102","10,000,000","Tối","Quản Lý");
//        StaticConfig.mNhanVien.child(key).setValue(nv);
//
//        String key = StaticConfig.mKhachHang.push().getKey();
//        KhachHang kh = new KhachHang(4,key,"Dịp Tú Tèo","0933123008","Q9, HCM","346081888");
//        StaticConfig.mKhachHang.child(key).setValue(kh);

//        <item>Dịch vụ giặc ủi quần áo</item>
//        <item>Dịch vụ spa</item>
//        <item>Dịch vụ trông trẻ</item>
//        <item>Dịch vụ phục vụ phòng</item>
//        String key = StaticConfig.mDichVu.push().getKey();
//        DichVu dv = new DichVu(4,key,"Dịch vụ phục vụ phòng",50000,"Ngày",0,"Dọn phòng giúp bạn");
//        StaticConfig.mDichVu.child(key).setValue(dv);

//        String key = StaticConfig.mNhanVien_Luong.push().getKey();
//        NhanVien_Luong nv_l = new NhanVien_Luong(4,key,"Trần Phi Du","0933123004","Đồng Nai","01/01/2003","1192102102","10,000,000","Tối","Quản Lý","400,000","Thích là ghi");
//        StaticConfig.mNhanVien_Luong.child(key).setValue(nv_l);

       startActivity(new Intent(getApplicationContext(),AD_ThongTinKhachHang.class));

    }

}