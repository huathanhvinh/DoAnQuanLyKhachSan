package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.example.doanquanlykhachsan.model.NhanVien_LichLamViec;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class NVTN_lichlamviec extends AppCompatActivity {
    TextView tvTenNV,tvCaLam,tvGhiChu;
    Button btnTroVe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_lichlamviec);
        setConTrol();
        setEvent();

    }

    private void setEvent() {
        KhoiTao();
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void KhoiTao() {
        //qq
        StaticConfig.mNhanVien_LichLamViec.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    NhanVien_LichLamViec nvtn = ds.getValue(NhanVien_LichLamViec.class);
                    if (nvtn.getSoDienThoai().equals(StaticConfig.currentphone)) {
                        tvTenNV.setText(nvtn.getTenNV());
                        tvCaLam.setText(nvtn.getCaLam());
                        tvGhiChu.setText(nvtn.getGhiChu());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Log.e("0933123003",StaticConfig.currentphone);
    }

    private void setConTrol() {
        tvGhiChu=findViewById(R.id.tvGhiChuLich);
        tvTenNV =findViewById(R.id.tvTenNVLich);
        tvCaLam =findViewById(R.id.tvCaLamLich);
        btnTroVe =findViewById(R.id.btnTroVe);
    }
}