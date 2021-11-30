package com.example.doanquanlykhachsan.khach_hang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.doanquanlykhachsan.MainActivity;
import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.khach_hang.menu_khachhang;
import com.example.doanquanlykhachsan.chung.*;
import com.example.doanquanlykhachsan.model.KhachHang;
import com.example.doanquanlykhachsan.model.User;
import com.example.doanquanlykhachsan.nhanvien_tapvu.nhanvientapvu_thongtintaikhoan;
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
    boolean iscmnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_thong_tin_tai_khoan);
        setControl();
        setEvnet();
    }

    private void setEvnet() {
        etSdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),update_phonenumber_1.class));
            }
        });
        etCmnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                StaticConfig.mUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            User user = ds.getValue(User.class);

                            if (etCmnd.getText().toString().equals(user.getCmnd().toString())) {
                                iscmnd = false;
                                break;
                            } else {
                                iscmnd = true;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
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
                    new AlertDialog.Builder(KH_thong_tin_tai_khoan.this)
                            .setTitle("Trả Phòng ")
                            .setMessage("Vui Lòng nhập đủ thông tin !! ")
                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else {
                    StaticConfig.mKhachHang.child(key).child("tenKH").setValue(etHoten.getText().toString());
                    if(iscmnd==true) {
                        StaticConfig.mKhachHang.child(key).child("cmnd").setValue(etCmnd.getText().toString());
                        StaticConfig.mUser.child(StaticConfig.currentuser).child("cmnd").setValue(etCmnd.getText().toString());
                        new AlertDialog.Builder(KH_thong_tin_tai_khoan.this)
                                .setTitle("Thông báo ")
                                .setMessage("Cập Nhận Thành công")
                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                // The dialog is automatically dismissed when a dialog button is clicked.
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                        startActivity(new Intent(getApplicationContext(), KH_thong_tin_tai_khoan.class));
                    }
                    else {
                        new AlertDialog.Builder(KH_thong_tin_tai_khoan.this)
                                .setTitle("Thông báo ")
                                .setMessage("CMND đã có")
                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                // The dialog is automatically dismissed when a dialog button is clicked.
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }

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