package com.example.doanquanlykhachsan.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.MainActivity;
import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.chung.Change_passwork;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.khach_hang.menu_khachhang;

public class AD_MenuAdmin extends AppCompatActivity {
    TextView tvDSP, tvDSDV, tvDSNV, tvThongKe, tvKhuyenMai, tvLuongNV, tvLichLV, tvDoiMK;
    Button btnDangXuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_menuadmin);
        setControl();
        setEvent();
    }

    private void setEvent() {
        //
        tvDSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AD_HienThiDanhSachPhong.class));
            }
        });
        //
        tvDSDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AD_HienThiDanhSachDichVu.class));
            }
        });
        //
        tvDSNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AD_HienThiDanhSachNhanVien.class));
            }
        });
        //
        tvThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AD_ThongKe.class));
            }
        });
        //
        tvKhuyenMai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AD_HienThiDanhSachKhuyenMai.class));
            }
        });

        tvLuongNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AD_LuongNhanVien.class));
            }
        });
        //
        tvLichLV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AD_LichLamViec.class));
            }
        });
        //
        tvDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             startActivity(new Intent(getApplicationContext(), Change_passwork.class));
            }
        });
        //
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(AD_MenuAdmin.this)
                        .setTitle("Đăng Xuất")
                        .setMessage("Bạn có muốn quay về màn hình đăng nhập ?")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                StaticConfig.fAuth.signOut();
                                startActivity(new Intent(AD_MenuAdmin.this, MainActivity.class));
                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    private void setControl() {
        tvDSP = findViewById(R.id.tvDSP);
        tvDSDV = findViewById(R.id.tvDSDV);
        tvDSNV = findViewById(R.id.tvDSNV);
        tvThongKe = findViewById(R.id.tvThongKe);
        tvKhuyenMai = findViewById(R.id.tvKhuyenMai);
        tvLuongNV = findViewById(R.id.tvLuongNhanVien);
        tvLichLV = findViewById(R.id.tvLichLamViec);
        tvDoiMK = findViewById(R.id.tvDoiMatKhau);
        btnDangXuat = findViewById(R.id.btnDangXuat);
    }
}