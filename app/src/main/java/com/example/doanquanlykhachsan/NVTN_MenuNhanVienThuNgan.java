package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NVTN_MenuNhanVienThuNgan extends AppCompatActivity {
    private TextView tvTTTaiKhoan , tvLichLamViec,tvQLKhachHang,tvLapPhieuThue,tvLapHoaDon,tvThongBao,tvLuong,tvDoiMK;
    private Button btnDangXuat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_menu_nhan_vien_thu_ngan);
        setConTrol();
        setEvent();
    }

    private void setEvent() {
        tvLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NVTN_Thongtinluong.class));
            }
        });
        tvLichLamViec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NVTN_lichlamviec.class));
            }
        });
        tvTTTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NVTN_Thongtintaikhoan.class));
            }
        });
        tvQLKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NVTN_HienThiDanhSachKhachHang.class));
            }
        });
        tvLapHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NVTN_LapHoaDon.class));
            }
        });
        tvThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NVTN_Thongbao.class));
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setConTrol() {
        tvTTTaiKhoan=findViewById(R.id.tvTTTaiKhoan);
        tvLichLamViec=findViewById(R.id.tvLichLamViec);
        tvQLKhachHang=findViewById(R.id.tvQLKhachHang);
        tvLapPhieuThue=findViewById(R.id.tvLapPhieuThue);
        tvLapHoaDon=findViewById(R.id.tvLapHoaDon);
        tvThongBao=findViewById(R.id.tvThongBao);
        tvLuong=findViewById(R.id.tvLuong);
        tvDoiMK=findViewById(R.id.tvDoiMK);
        btnDangXuat=findViewById(R.id.btnDangXuat);
    }
}