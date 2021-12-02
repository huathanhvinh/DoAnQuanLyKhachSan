package com.example.doanquanlykhachsan.nhanvien_tapvu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Phong;
import com.example.doanquanlykhachsan.model.nvtv_qlphong;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class nhanvientapvu_xacnhantrangthaiphong extends AppCompatActivity {
    TextView tvMaPhong, tvLoaiPhong, tvTrangThai;
    Button btnXacNhan, btnTroVe;
    Phong chitiet;
    CheckBox ckDonPhong, ckVatDung, ckRuou, ckMaKM, ckTuLanh, ckThucAn;
    boolean check = false;
    boolean thucAn = false;
    boolean ruou = false;
    boolean maKM = false;
    boolean tuLanh = false;
    boolean vatDung = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvientapvu_xacnhantrangthaiphong);
        setControl();
        setEvent();
        khoiTao();
    }

    private void setEvent() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Khi bấn xác nhận phòng thì lưu checkbox xem phòng đã dọn hay chưa
                StaticConfig.mQLPhong.child(chitiet.getMaFB()).setValue(new nvtv_qlphong(chitiet.getMaFB(), check, thucAn, tuLanh, ruou,vatDung, maKM));
                new AlertDialog.Builder(nhanvientapvu_xacnhantrangthaiphong.this)
                        .setTitle("Xác nhận  trạng thái ")
                        .setMessage("Thông tin đã được lưu")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                startActivity(new Intent(getApplicationContext(),nhanvientapvu_quanlyphong.class));
            }
        });
        ckDonPhong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (ckDonPhong.isChecked()) {
                    check = true;
                } else {
                    check = false;
                }
            }
        });
        ckMaKM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (ckMaKM.isChecked()) {
                    maKM = true;
                } else {
                    maKM = false;
                }
            }
        });
        ckVatDung.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (ckVatDung.isChecked()) {
                    vatDung = true;
                } else {
                    vatDung = false;
                }
            }
        });
        ckRuou.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ckRuou.isChecked()) {
                    ruou = true;
                } else {
                    ruou = false;
                }
            }
        });
        ckThucAn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (ckThucAn.isChecked()) {
                    thucAn = true;
                } else {
                    thucAn = false;
                }
            }
        });


        ckTuLanh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (ckTuLanh.isChecked()) {
                    tuLanh = true;
                } else {
                    tuLanh = false;
                }
            }
        });

    }

    private void khoiTao() {
        //
        chitiet = (Phong) getIntent().getSerializableExtra("Room");//Lấy thông tin từ customAdapter
        //Gán dữ liệu
        tvMaPhong.setText(chitiet.getMaFB() + "");
        tvLoaiPhong.setText(chitiet.getLoai() + "");
        tvTrangThai.setText(chitiet.getTrangThai() + "");

        //Truy xuất từ bản QLPHong
        StaticConfig.mQLPhong.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    //Kiểm tra chi tiết phòng hiện tại
                    if (chitiet.getMaFB().equals(ds.child("phong").getValue(String.class))) {
                        //Kiểm tra true ,false
                        if (ds.child("kiemtra").getValue(boolean.class).equals(true)) {
                            ckDonPhong.setChecked(true);
                        } else if (ds.child("kiemtra").getValue(boolean.class).equals(false)) {
                            ckDonPhong.setChecked(false);
                        }
                        if (ds.child("thucAn").getValue(boolean.class).equals(true)) {
                            ckThucAn.setChecked(true);
                        } else if (ds.child("thucAn").getValue(boolean.class).equals(false)) {
                            ckThucAn.setChecked(false);
                        }
                        if (ds.child("tuLanh").getValue(boolean.class).equals(true)) {
                            ckTuLanh.setChecked(true);
                        } else if (ds.child("tuLanh").getValue(boolean.class).equals(false)) {
                            ckTuLanh.setChecked(false);
                        }
                        if (ds.child("ruou").getValue(boolean.class).equals(true)) {
                            ckRuou.setChecked(true);
                        } else  if (ds.child("ruou").getValue(boolean.class).equals(false)){
                            ckRuou.setChecked(false);
                        }
                        if (ds.child("vatDung").getValue(boolean.class).equals(true)) {
                            ckVatDung.setChecked(true);
                        } else   if (ds.child("vatDung").getValue(boolean.class).equals(false)){
                            ckVatDung.setChecked(false);
                        }
                        if (ds.child("maKM").getValue(boolean.class).equals(true)) {
                            ckMaKM.setChecked(true);
                        } else  if (ds.child("maKM").getValue(boolean.class).equals(false)){
                            ckMaKM.setChecked(false);
                        }

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void setControl() {
        tvMaPhong = findViewById(R.id.tvMaPhong);
        tvLoaiPhong = findViewById(R.id.tvLoaiPhong);
        tvTrangThai = findViewById(R.id.tvTrangThai);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        btnTroVe = findViewById(R.id.btnTroVe);
        ckDonPhong = findViewById(R.id.ckDonPhong);
        ckThucAn = findViewById(R.id.ckThucAn);
        ckTuLanh = findViewById(R.id.ckTuLanh);
        ckMaKM = findViewById(R.id.ckMaKM);
        ckVatDung = findViewById(R.id.ckVatDung);
        ckRuou = findViewById(R.id.ckRuou);


    }
}