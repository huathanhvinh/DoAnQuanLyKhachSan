package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Nhanvientapvu_manhinhchinh extends AppCompatActivity {

    TextView tvThongTinTaiKhoan,tvQuanLyPhong,tvQuanLyDichVu,tvLuong,tvLichLamViec;
    Button btnDangXuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvientapvu_manhinhchinh);
        setControl();
        setEvent();


    }

    private void setEvent() {
        tvThongTinTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),nhanvientapvu_thongtintaikhoan.class));
            }
        });
        tvQuanLyPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),nhanvientapvu_quanlyphong.class));
            }
        });
        tvQuanLyDichVu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),nhanvientapvu_sudungdichvu.class));
            }
        });
        tvLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),nhanvientapvu_thongtinluong.class));
            }
        });
        tvLichLamViec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),nhanvientapvu_lichlamviec.class));
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),sign_in.class));
            }
        });

    }

    private void setControl() {
        tvThongTinTaiKhoan=findViewById(R.id.tvThongTinTaiKhoan);
        tvQuanLyPhong=findViewById(R.id.tvQuanLyPhong);
        tvQuanLyDichVu=findViewById(R.id.tvQuanLyDichVu);
        tvLuong=findViewById(R.id.tvLuong);
        tvLichLamViec=findViewById(R.id.tvLichLamViec);
        btnDangXuat=findViewById(R.id.btnDangXuat);

    }
}