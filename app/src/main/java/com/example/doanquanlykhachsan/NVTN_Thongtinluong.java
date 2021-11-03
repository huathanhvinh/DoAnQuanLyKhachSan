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

public class NVTN_Thongtinluong extends AppCompatActivity {
    TextView tvTenNV, tvLoaiNV, tvLuong, tvTienThuong;
    Button btnTroVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_thongtinluong);
        setconTrol();
        setEvent();
        KhoiTao();
    }

    private void KhoiTao() {
        StaticConfig.mNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    NhanVien nv = ds.getValue(NhanVien.class);
                    if (nv.getSoDienThoai().toString().equals(StaticConfig.currentphone)) {
                        tvLuong.setText(nv.getLuong());
                        tvLoaiNV.setText(nv.getChucVu());
                        tvTenNV.setText(nv.getTenNV());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void setEvent() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setconTrol() {
        btnTroVe =findViewById(R.id.btnTroVe);
        tvTenNV = findViewById(R.id.tvTenNV);
        tvLoaiNV = findViewById(R.id.tvLoaiNV);
        tvLuong = findViewById(R.id.tvLuong);
        tvTienThuong = findViewById(R.id.tvTienThuong);

    }
}