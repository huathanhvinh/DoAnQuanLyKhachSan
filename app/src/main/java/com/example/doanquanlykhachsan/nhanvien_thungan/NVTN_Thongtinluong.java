package com.example.doanquanlykhachsan.nhanvien_thungan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.NhanVien_Luong;
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
        StaticConfig.mNhanVien_Luong.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    NhanVien_Luong nv = ds.getValue(NhanVien_Luong.class);
                    if (nv.getSoDienThoai().toString().equals(StaticConfig.currentphone)) {
                        tvLuong.setText(nv.getLuong());
                        tvLoaiNV.setText(nv.getChucVu());
                        tvTenNV.setText(nv.getTenNV());
                        tvTienThuong.setText(nv.getTienThuong());
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