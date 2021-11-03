package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class Nhanvientapvu_manhinhchinh extends AppCompatActivity {

    TextView tvThongTinTaiKhoan,tvNhanVien,tvQuanLyPhong,tvQuanLyDichVu,tvLuong,tvLichLamViec,tvThongBao;
    Button btnDangXuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvientapvu_manhinhchinh);
        setControl();
        setEvent();
        Phone();


    }
    //Lấy sđt từ tài khoản trên firebase để lấy thông tin nhân viên
    private void Phone() {
        StaticConfig.mUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("id").getValue(String.class).equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        StaticConfig.currentphone=ds.child("sdt").getValue(String.class);
                        StaticConfig.mNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot ds : snapshot.getChildren())
                                {
                                    NhanVien nv = ds.getValue(NhanVien.class);
                                    if(nv.getSoDienThoai().toString().equals(StaticConfig.currentphone))
                                    {
                                        tvNhanVien.setText(nv.getTenNV());
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
        tvThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),nhanvientapvu_thongbao.class));
            }
        });

    }

    private void setControl() {
        tvNhanVien = findViewById(R.id.tvNhanVien);
        tvThongTinTaiKhoan=findViewById(R.id.tvThongTinTaiKhoan);
        tvQuanLyPhong=findViewById(R.id.tvQuanLyPhong);
        tvQuanLyDichVu=findViewById(R.id.tvQuanLyDichVu);
        tvLuong=findViewById(R.id.tvLuong);
        tvLichLamViec=findViewById(R.id.tvLichLamViec);
        tvThongBao= findViewById(R.id.tvThongBao);
        btnDangXuat=findViewById(R.id.btnDangXuat);

    }
}