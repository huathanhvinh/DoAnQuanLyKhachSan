package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class nhanvientapvu_thongtinluong extends AppCompatActivity {
    TextView tvTenNhanVien, tvLoaiNhanVien, tvLuong, tvTienThuong;

    Button btnTroVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvientapvu_thongtinluong);
        setControl();
        setEvent();
        khoiTao();


    }

    private void khoiTao() {
        {

            StaticConfig.mNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        NhanVien nv = ds.getValue(NhanVien.class);
                        if (nv.getSoDienThoai().toString().equals(StaticConfig.currentphone)) {
                            tvTenNhanVien.setText(nv.getTenNV());
                            tvLoaiNhanVien.setText(nv.getChucVu());
                            tvLuong.setText(nv.getLuong() + "");

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

    private void setControl() {
        btnTroVe = findViewById(R.id.btnTroVe);
        tvTenNhanVien = findViewById(R.id.tvTenNhanVien);
        tvLoaiNhanVien = findViewById(R.id.tvLoaiNhanVien);
        tvLuong = findViewById(R.id.tvLuong);
        tvTienThuong = findViewById(R.id.tvTienThuong);
    }

    private void setEvent() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


}