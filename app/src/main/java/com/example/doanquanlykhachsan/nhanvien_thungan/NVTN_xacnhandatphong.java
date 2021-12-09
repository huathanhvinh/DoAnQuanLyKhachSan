package com.example.doanquanlykhachsan.nhanvien_thungan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.example.doanquanlykhachsan.model.PhongDaDat;
import com.example.doanquanlykhachsan.model.ThongBao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class NVTN_xacnhandatphong extends AppCompatActivity {
    Button btnXacnhan, btnHuy;
    PhongDaDat chitiet;
    TextView tvMaGiaoDich;
    String key;
    private int idthongbao;
    private String tennv;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_xacnhandatphong);
        setControl();
        setEvent();
        Log.e("dv ", chitiet.getMaDichVu());
    }

    private void setEvent() {
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < StaticConfig.arrayListTemporaryRoom.size(); i++) {
                    StaticConfig.mRoom.child(StaticConfig.arrayListTemporaryRoom.get(i).getMaFB()).child("trangThai").setValue("Trống");
                }
                StaticConfig.arrayListTemporaryService.clear();
                StaticConfig.arrayListTemporaryService.clear();
                StaticConfig.mRoomRented.child(id).removeValue();

                String key = StaticConfig.mThongBao.push().getKey();
                ThongBao tb = new ThongBao(idthongbao + 1, key, "Huỷ Phòng", "Chưa xác nhận", "", tennv,chitiet.getMaKH());
                StaticConfig.mThongBao.child(key).setValue(tb);

                startActivity(new Intent(getApplicationContext(), NV_chonphonglapphieuthue.class));
            }
        });
        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < StaticConfig.arrayListTemporaryRoom.size(); i++) {
                    StaticConfig.mRoom.child(StaticConfig.arrayListTemporaryRoom.get(i).getMaFB()).child("trangThai").setValue("Đã Đặt Phòng");
                }
                String key = StaticConfig.mThongBao.push().getKey();
                ThongBao tb = new ThongBao(idthongbao + 1, key, "Đặt Phòng", "Chưa xác nhận", "", tennv,chitiet.getMaKH());
                StaticConfig.mThongBao.child(key).setValue(tb);
                StaticConfig.arrayListTemporaryRoom.clear();
                StaticConfig.arrayListTemporaryService.clear();
                StaticConfig.mRoomRented.child(id).setValue(chitiet);
                startActivity(new Intent(getApplicationContext(), NV_chonphonglapphieuthue.class));
            }
        });
    }
    private void tenNhanvien() {
        StaticConfig.mNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    NhanVien nv = ds.getValue(NhanVien.class);
                    if (nv.getSoDienThoai().equals(StaticConfig.currentphone)) {
                        tennv = nv.getTenNV();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void setControl() {
        layid();
        tenNhanvien();
        chitiet = (PhongDaDat) getIntent().getSerializableExtra("chitiet");
        id = getIntent().getStringExtra("ma");
        btnHuy = findViewById(R.id.btnHuy);
        btnXacnhan = findViewById(R.id.btnXacNhan);
        tvMaGiaoDich = findViewById(R.id.mahd);
        if (chitiet.getStt() > 9) {
            tvMaGiaoDich.setText("HD" + chitiet.getStt());
        } else {
            tvMaGiaoDich.setText("HD0" + chitiet.getStt());
        }
    }

    private void layid() {
        StaticConfig.mThongBao.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    ThongBao tb = ds.getValue(ThongBao.class);
                    idthongbao = tb.getStt();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}