package com.example.doanquanlykhachsan.khach_hang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.*;
import com.example.doanquanlykhachsan.adapter.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class KH_doi_phong extends AppCompatActivity {
    private TextView chinhsach, tvtenphong, tvGia;
    private EditText lydo;
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
        StaticConfig.sXacNhan = "Đổi Phòng";
    }

    private void setEvnet() {
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(KH_doi_phong.this)
                        .setTitle("Xác Nhận Đổi Phòng Phòng")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
//
                                DoiPhong dp = new DoiPhong(StaticConfig.chon.getMaPhong(), StaticConfig.chon.getMaPhong(), chitiet.getMaPhong(), StaticConfig.mathue,lydo.getText().toString());
                                StaticConfig.mDoiPhong.child(StaticConfig.chon.getMaPhong()).setValue(dp);
                                startActivity(new Intent(getApplicationContext(),menu_khachhang.class));
                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
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
        lydo=findViewById(R.id.lydo);
        btntrove = findViewById(R.id.btntrove);
        chinhsach = findViewById(R.id.chinhsach);
        btnxacnhan = findViewById(R.id.btnxacnhan);
        tvtenphong = findViewById(R.id.tvTenPhong);
        gridView = findViewById(R.id.gv_dv);
        tvGia = findViewById(R.id.tvGia);
        tvtenphong.setText("Phòng " + chitiet.getSoPhong());
        DecimalFormat toTheFormat = new DecimalFormat("###,###,###.#");
        if (StaticConfig.Loai.equals("ngay")) {
            tvGia.setText(toTheFormat.format(chitiet.getGiaNgay()) + " VNĐ");
        }
        if (StaticConfig.Loai.equals("gio")) {
            tvGia.setText(toTheFormat.format(chitiet.getGiaGio()) + " VNĐ");
        }

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