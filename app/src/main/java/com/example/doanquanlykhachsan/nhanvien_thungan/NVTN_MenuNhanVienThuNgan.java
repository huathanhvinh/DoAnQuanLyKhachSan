package com.example.doanquanlykhachsan.nhanvien_thungan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.doanquanlykhachsan.MainActivity;
import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.chung.Change_passwork;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class NVTN_MenuNhanVienThuNgan extends AppCompatActivity {
    private TextView tvTTTaiKhoan , tvLichLamViec,tvQLKhachHang,tvLapPhieuThue,tvLapHoaDon,tvThongBao,tvLuong,tvDoiMK,tvTenNV;
    private Button btnDangXuat;
    private LinearLayout doiMK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_menu_nhan_vien_thu_ngan);
        setConTrol();
        setEvent();
        sdtHienTai();
    }

    private void sdtHienTai() {
        StaticConfig.mUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("maFB").getValue(String.class).equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        StaticConfig.currentphone=ds.child("sdt").getValue(String.class);
                        StaticConfig.mNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot ds : snapshot.getChildren())
                                {
                                    NhanVien nv = ds.getValue(NhanVien.class);
                                    if(nv.getSoDienThoai().toString().equals(StaticConfig.currentphone))
                                    {
                                        tvTenNV.setText(nv.getTenNV());
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
        tvLapPhieuThue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),NV_chonphonglapphieuthue.class));
            }
        });
        doiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Change_passwork.class));
            }
        });
        tvLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NVTN_Thongtinluong.class));
            }
        });
        tvLichLamViec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NVTN_lichlamviec.class));
            }
        });
        tvTTTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NVTN_Thongtintaikhoan.class));
            }
        });
        tvQLKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NVTN_HienThiDanhSachKhachHang.class));
            }
        });
        tvLapHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NVTN_LapHoaDon.class));
            }
        });
        tvThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NVTN_Thong_Bao.class));
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(NVTN_MenuNhanVienThuNgan.this)
                        .setTitle("Đăng xuất")
                        .setMessage("Bạn có Chắc đăng xuất không ?")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                StaticConfig.fAuth.signOut();
                                startActivity(new Intent(NVTN_MenuNhanVienThuNgan.this, MainActivity.class));
                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }



    private void setConTrol() {
        tvTenNV=findViewById(R.id.tvTenNV);
        tvTTTaiKhoan=findViewById(R.id.tvTTTaiKhoan);
        tvLichLamViec=findViewById(R.id.tvLichLamViec);
        tvQLKhachHang=findViewById(R.id.tvQLKhachHang);
        tvLapPhieuThue=findViewById(R.id.tvLapPhieuThue);
        tvLapHoaDon=findViewById(R.id.tvLapHoaDon);
        tvThongBao=findViewById(R.id.tvThongBao);
        tvLuong=findViewById(R.id.tvLuong);
        tvDoiMK=findViewById(R.id.tvDoiMK);
        btnDangXuat=findViewById(R.id.btnDangXuat);
        doiMK=findViewById(R.id.lnchange);
    }
}