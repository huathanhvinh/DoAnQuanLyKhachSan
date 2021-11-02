package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class AD_MenuAdmin extends AppCompatActivity{
    TextView tvDSP,tvDSDV,tvDSNV,tvThongKe,tvKhuyenMai,tvLuongNV,tvLichLV,tvDoiMK;
    Button btnDangXuat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_menuadmin);
        setControl();
        setEvent();
    }

    private void setEvent() {
        //
        tvDSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AD_HienThiDanhSachPhong.class));
            }
        });
        //
        tvDSDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AD_HienThiDanhSachDichVu.class));
            }
        });
        //
        tvDSNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AD_HienThiDanhSachNhanVien.class));
            }
        });
        //
        tvThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AD_ThongKe.class));
            }
        });
        //
        tvKhuyenMai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AD_HienThiDanhSachKhuyenMai.class));
            }
        });

        tvLuongNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AD_LuongNhanVien.class));
            }
        });
        //
        tvLichLV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AD_LichLamViec.class));
            }
        });
        //
        tvDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Quay về màn hình đổi mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });
        //
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Quay về màn hình Đăng Nhập", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setControl() {
        tvDSP = findViewById(R.id.tvDSP);
        tvDSDV = findViewById(R.id.tvDSDV);
        tvDSNV = findViewById(R.id.tvDSNV);
        tvThongKe = findViewById(R.id.tvThongKe);
        tvKhuyenMai = findViewById(R.id.tvKhuyenMai);
        tvLuongNV = findViewById(R.id.tvLuongNhanVien);
        tvLichLV = findViewById(R.id.tvLichLamViec);
        tvDoiMK = findViewById(R.id.tvDoiMatKhau);
        btnDangXuat = findViewById(R.id.btnDangXuat);
    }
}