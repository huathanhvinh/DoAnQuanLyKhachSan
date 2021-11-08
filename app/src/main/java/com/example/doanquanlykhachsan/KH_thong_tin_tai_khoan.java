package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.KhachHang;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class KH_thong_tin_tai_khoan extends AppCompatActivity {
    private Button btntrove, btnluu;
    private float Tongtien = 0;
    private ImageView penHoten, penSdt, penCmnd;
    private EditText etHoten, etSdt, etCmnd;
    String key="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_thong_tin_tai_khoan);
        setControl();
        setEvnet();
    }

    private void setEvnet() {
        penHoten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etHoten.setEnabled(true);
                etHoten.setFocusable(true);
            }
        });
        penSdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSdt.setEnabled(true);
                etSdt.setFocusable(true);
            }
        });
        penCmnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSdt.setEnabled(true);
                etSdt.setFocusable(true);
            }
        });
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etHoten.getText().toString().isEmpty()||
                etSdt.getText().toString().isEmpty()||
                etCmnd.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Vui Lòng nhập đủ thông tin !!", Toast.LENGTH_SHORT).show();
                }
                else {
                    StaticConfig.mKhachHang.child(key).child("tenKH").setValue(etHoten.getText().toString());
                    StaticConfig.mKhachHang.child(key).child("sdtKH").setValue(etSdt.getText().toString());
                    StaticConfig.mKhachHang.child(key).child("cmnd").setValue(etCmnd.getText().toString());
                    startActivity(new Intent(getApplicationContext(),KH_thong_tin_tai_khoan.class));
                }
            }
        });

        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),menu_khachhang.class));
            }
        });
    }

    private void setControl() {
        Log.d("so dien thoai", StaticConfig.currentphone);
        btntrove = findViewById(R.id.btntrove);
        etHoten = findViewById(R.id.hoten);
        etSdt = findViewById(R.id.sdt);
        etCmnd = findViewById(R.id.cmnd);
        penHoten = findViewById(R.id.pen1);
        penSdt = findViewById(R.id.pen2);
        penCmnd = findViewById(R.id.pen3);
        btnluu = findViewById(R.id.btnluu);
        khoitao();
//        Dichvu dv = new Dichvu("DV2", "xe",100);
//        StaticConfig.mDichvu.child("DV2").setValue(dv);
//        Thongtin_Dich_vu thongtin = new Thongtin_Dich_vu("DV2","KH1","L01");
//        StaticConfig.mThongtinDv.child(StaticConfig.currentuser).child("DV2").setValue(thongtin);
//        StaticConfig.mThongtinDv.child(StaticConafig.currentuser).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    String madv = ds.child("madv").getValue(String.class);
//                    String maphong = ds.child("maphong").getValue(String.class);
//                    StaticConfig.mRoom.child(maphong).addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            Tongtien += snapshot.child("giagio").getValue(Float.class);
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//                    StaticConfig.mDichVu.child(madv).addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            Tongtien += snapshot.child("gia").getValue(Float.class);
//                            Log.d("Tong tien", Tongtien + "");
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });

    }

    private void khoitao() {
        StaticConfig.mKhachHang.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    KhachHang kh = ds.getValue(KhachHang.class);
                    if (kh.getSdtKH().toString().equals(StaticConfig.currentphone)) {
                        etHoten.setText(kh.getTenKH());
                        etSdt.setText(kh.getSdtKH() + "");
                        etCmnd.setText(kh.getCmnd() + "");
                        key=kh.getMaFB();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}