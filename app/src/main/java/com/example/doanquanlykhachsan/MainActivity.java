package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.KhuyenMai;
import com.example.doanquanlykhachsan.model.NhanVien;

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
//        KhachHang kh = new KhachHang(4,key,"Nguyễn Văn Duy","0933123008","Q9, HCM","346081888");
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

//        String key = StaticConfig.mNhanVien_LichLamViec.push().getKey();
//        NhanVien_LichLamViec nv_lv = new NhanVien_LichLamViec(4,key,"Trần Phi Du","0933123004","Q9, HCM","01/01/2003","1192102102","10,000,000","Tối","Quản Lý","Thích là ghi");
//        StaticConfig.mNhanVien_LichLamViec.child(key).setValue(nv_lv);

//        String key3 = StaticConfig.mKhuyenMai.push().getKey();
//        KhuyenMai km3 = new KhuyenMai(key3,"00dkanxa","Lễ Khánh Thành","01/01/2021","03/01/2021",5,"Khánh thành","Đã Kết Thúc");
//        StaticConfig.mKhuyenMai.child(key3).setValue(km3);


        startActivity(new Intent(getApplicationContext(), AD_HienThiDanhSachKhuyenMai.class));
    }

    @NonNull
    private StringBuilder getStringBuilder() {
        String ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(8);
        for(int i=0;i<8;i++)
        {
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        }
        return sb;
    }

}