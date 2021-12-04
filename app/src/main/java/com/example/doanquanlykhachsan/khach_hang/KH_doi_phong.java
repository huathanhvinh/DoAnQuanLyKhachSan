package com.example.doanquanlykhachsan.khach_hang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.*;
import com.example.doanquanlykhachsan.adapter.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KH_doi_phong extends AppCompatActivity {
    private TextView chinhsach, tvtenphong;
    private Button btntrove, btnxacnhan;
    Phong chitiet;
    private ArrayList<DichVu> data = new ArrayList<>();
    KH_CustomDichvu customDichvu;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_doi_phong);
        chitiet = (Phong) getIntent().getSerializableExtra("chitiet");
        SetControl();
        setEvnet();
    }

    private void setEvnet() {
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticConfig.mRoomRented.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            PhongDaDat da = ds.getValue(PhongDaDat.class);
                            if (!StaticConfig.chon.equals("")) {
                                if (da.getMaKH().equals(StaticConfig.currentuser)) {
                                    StaticConfig.mRoom.child(StaticConfig.chon.getMaFB()).child("trangThai").setValue("Trống");
                                    StaticConfig.mRoom.child(chitiet.getMaFB()).child("trangThai").setValue("Đã Đặt Phòng");
                                    String str = da.getMaPhong();
                                    String replacedStr = str.replaceAll(StaticConfig.chon.getMaPhong(), chitiet.getMaPhong());
                                    StaticConfig.mRoomRented.child(StaticConfig.mathue).child("maPhong").setValue(replacedStr);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                startActivity(new Intent(getApplicationContext(), menu_khachhang.class));

            }
        });
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        chinhsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), KH_chinh_sach.class));
            }
        });
    }

    private void SetControl() {
        btntrove = findViewById(R.id.btntrove);
        chinhsach = findViewById(R.id.chinhsach);
        btnxacnhan = findViewById(R.id.btnxacnhan);
        tvtenphong = findViewById(R.id.tvTenPhong);
        gridView = findViewById(R.id.gv_dv);
        tvtenphong.setText("Phòng " + chitiet.getSoPhong());

        customDichvu = new KH_CustomDichvu(getApplicationContext(), R.layout.kh_item_ds_dich_vu, data);
        gridView.setAdapter(customDichvu);
        khoitao();
    }

    private void khoitao() {
        StaticConfig.mDichVu.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    DichVu dv = ds.getValue(DichVu.class);
                    data.add(dv);
                }
                customDichvu.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}