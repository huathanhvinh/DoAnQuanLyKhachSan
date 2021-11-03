package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class NVTN_MenuNhanVienThuNgan extends AppCompatActivity {
    private TextView tvTTTaiKhoan , tvLichLamViec,tvQLKhachHang,tvLapPhieuThue,tvLapHoaDon,tvThongBao,tvLuong,tvDoiMK,tvTenNV;
    private Button btnDangXuat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_menu_nhan_vien_thu_ngan);
        setConTrol();
        setEvent();
        sdtHienTai();
    }

    private void sdtHienTai() {
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
                                        tvTenNV.setText(nv.getTenNV());
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
                startActivity(new Intent(getApplicationContext(),sign_in.class));
            }
        });
    }

    private void setConTrol() {
        tvTenNV=findViewById(R.id.tvTenNV);
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