package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.NVTN_HienThiDSKH;

public class NVTN_Thongtinkhachhang extends AppCompatActivity {
    Button btnTroVe;
    TextView tvSuaKH,tvXoaKH,tvMaKH,tvTenKH,tvDiachi,tvCMND,tvHoiVien;
    NVTN_HienThiDSKH kh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_thongtinkhachhang);
        setControl();
        KhoiTao();
    }

    private void setEvent() {
        tvXoaKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                StaticConfig.mKhachHang.child("TenKhachHang").setValue(null);
//                StaticConfig.mKhachHang.child("DiaChi").setValue(null);
//                StaticConfig.mKhachHang.child("SoDT").setValue(null);
//                StaticConfig.mKhachHang.child("CMND").setValue(null);
//                StaticConfig.mKhachHang.child("HoiVien").setValue(null);
//                Toast.makeText(getApplicationContext(), "Bạn Đã xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        tvSuaKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NVTN_SuaKhachHang.class));
            }
        });
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void KhoiTao() {
        kh = (NVTN_HienThiDSKH) getIntent().getSerializableExtra("ThongTinKhachHang");
        tvMaKH.setText(kh.getMaKH()+"");
        tvTenKH.setText(kh.getTenKH()+"");
        tvDiachi.setText(kh.getDiaChi()+"");
        tvCMND.setText(kh.getChungminhND()+"");
        tvHoiVien.setText(kh.getHoiVien()+"");
    }

    private void setControl() {
        tvHoiVien=findViewById(R.id.tvHoiVien);
        tvCMND = findViewById(R.id.tvCMND);
        tvDiachi = findViewById(R.id.tvDiachi);
        tvTenKH = findViewById(R.id.tvTenKH);
        tvMaKH = findViewById(R.id.tvMaKH);
        tvSuaKH = findViewById(R.id.tvSuaKH);
        tvXoaKH = findViewById(R.id.tvXoaKH);
        btnTroVe = findViewById(R.id.btnTroVe);
    }
}