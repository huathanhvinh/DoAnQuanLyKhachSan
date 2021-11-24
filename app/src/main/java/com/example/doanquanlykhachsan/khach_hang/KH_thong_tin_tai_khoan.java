package com.example.doanquanlykhachsan.khach_hang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.khach_hang.menu_khachhang;
import com.example.doanquanlykhachsan.chung.*;
import com.example.doanquanlykhachsan.model.KhachHang;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class KH_thong_tin_tai_khoan extends AppCompatActivity {
    private Button btntrove, btnluu;
    private float Tongtien = 0;
    private ImageView penHoten, penSdt, penCmnd;
    private EditText etHoten, etSdt, etCmnd;
    private LinearLayout ChangeMK;
    String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_thong_tin_tai_khoan);
        setControl();
        setEvnet();
    }

    private void setEvnet() {
        ChangeMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Change_passwork.class));
            }
        });
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
                etCmnd.setEnabled(true);
                etCmnd.setFocusable(true);
            }
        });
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etHoten.getText().toString().isEmpty() ||
                        etSdt.getText().toString().isEmpty() ||
                        etCmnd.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui Lòng nhập đủ thông tin !!", Toast.LENGTH_SHORT).show();
                } else {
                    StaticConfig.mKhachHang.child(key).child("tenKH").setValue(etHoten.getText().toString());
                    StaticConfig.mKhachHang.child(key).child("sdtKH").setValue(etSdt.getText().toString());
                    StaticConfig.mKhachHang.child(key).child("cmnd").setValue(etCmnd.getText().toString());
                    StaticConfig.mUser.child(StaticConfig.currentuser).child("cmnd").setValue(etCmnd.getText().toString());
                    startActivity(new Intent(getApplicationContext(), KH_thong_tin_tai_khoan.class));
                }
            }
        });

        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), menu_khachhang.class));
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
        ChangeMK = findViewById(R.id.ChangeMK);
        khoitao();
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
                        key = kh.getMaFB();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}