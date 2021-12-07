package com.example.doanquanlykhachsan.nhanvien_tapvu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.MainActivity;
import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.admin.AD_MenuAdmin;
import com.example.doanquanlykhachsan.chung.sign_in;
import com.example.doanquanlykhachsan.chung.*;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.example.doanquanlykhachsan.nhanvien_tapvu.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class Nhanvientapvu_manhinhchinh extends AppCompatActivity {

    TextView tvThongTinTaiKhoan, tvNhanVien, tvQuanLyPhong, tvQuanLyDichVu, tvLuong, tvLichLamViec;
    Button btnDangXuat;
    LinearLayout doiMK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvientapvu_manhinhchinh);
        setControl();
        setEvent();
        Phone();
    }

    //So sánh sđt từ tài khoản trên firebase để lấy thông tin nhân viên
    private void Phone() {
        StaticConfig.mUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("maFB").getValue(String.class).equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        StaticConfig.currentphone = ds.child("sdt").getValue(String.class);
                        StaticConfig.mNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    NhanVien nv = ds.getValue(NhanVien.class);
                                    if (nv.getSoDienThoai().toString().equals(StaticConfig.currentphone)) {
                                        tvNhanVien.setText(nv.getTenNV());
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setEvent() {
        tvThongTinTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), nhanvientapvu_thongtintaikhoan.class));
            }
        });
        tvQuanLyPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), nhanvientapvu_quanlyphong.class));
            }
        });
        tvQuanLyDichVu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), nhanvientapvu_dangkydichvu.class));
            }
        });
        tvLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), nhanvientapvu_thongtinluong.class));
            }
        });
        tvLichLamViec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), nhanvientapvu_lichlamviec.class));
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Nhanvientapvu_manhinhchinh.this)
                        .setTitle("Đăng xuất")
                        .setMessage("Bạn có Chắc đăng xuất không ?")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                StaticConfig.fAuth.signOut();
                                startActivity(new Intent(Nhanvientapvu_manhinhchinh.this, MainActivity.class));
                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
        doiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Change_passwork.class));
            }
        });

    }

    private void setControl() {
        tvNhanVien = findViewById(R.id.tvNhanVien);
        tvThongTinTaiKhoan = findViewById(R.id.tvThongTinTaiKhoan);
        tvQuanLyPhong = findViewById(R.id.tvQuanLyPhong);
        tvQuanLyDichVu = findViewById(R.id.tvQuanLyDichVu);
        tvLuong = findViewById(R.id.tvLuong);
        tvLichLamViec = findViewById(R.id.tvLichLamViec);
        doiMK = findViewById(R.id.lnchange);
//        tvThongBao= findViewById(R.id.tvThongBao);
        btnDangXuat = findViewById(R.id.btnDangXuat);

    }
}