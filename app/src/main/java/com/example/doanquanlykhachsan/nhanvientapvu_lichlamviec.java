package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.NhanVien_LichLamViec;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class nhanvientapvu_lichlamviec extends AppCompatActivity {
    Button btnTroVe;
    TextView tvTenNhanVien,tvCaLam,tvGhiChu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvientapvu_lichlamviec);
        setControl();
        setEvent();
        khoiTao();
    }
    //Lấy thông tim lịch làm việc của nhân viên
    private void khoiTao() {
        StaticConfig.mNhanVien_LichLamViec.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    NhanVien_LichLamViec nv = ds.getValue(NhanVien_LichLamViec.class);
                    if (nv.getSoDienThoai().toString().equals(StaticConfig.currentphone)) {
                        tvTenNhanVien.setText(nv.getTenNV());
                        tvCaLam.setText(nv.getCaLam());
                        tvGhiChu.setText(nv.getGhiChu());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setControl() {
        btnTroVe = findViewById(R.id.btnTroVe);
        tvTenNhanVien = findViewById(R.id.tvTenNhanVien);
        tvCaLam = findViewById(R.id.tvCaLam);
        tvGhiChu = findViewById(R.id.tvGhiChu);
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