package com.example.doanquanlykhachsan.nhanvien_letan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.KhachHang;

public class NVTN_SuaKhachHang extends AppCompatActivity {
    Button btnTroVe,btnSuaKH;
    EditText edtTenKH ,edtCMND,edtSDT;
    KhachHang kh ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_sua_khach_hang);
        setControl();
        setEvent();
        KhoiTao();
    }

    private void KhoiTao() {
        kh = (KhachHang) getIntent().getSerializableExtra("SuaKhachHang");
        edtTenKH.setText(kh.getTenKH()+"");
        edtSDT.setText(kh.getSdtKH()+"");
        edtCMND.setText(kh.getCmnd()+"");


    }

    private void setEvent() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NVTN_HienThiDanhSachKhachHang.class));
            }
        });
        btnSuaKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenkh =edtTenKH.getText().toString();
                String sodt=edtSDT.getText().toString();
                String cmnd=edtCMND.getText().toString();
                KhachHang khachhang = new KhachHang(kh.getStt(),kh.getMaFB(),tenkh,sodt,kh.getDiaChi(),cmnd);
                StaticConfig.mKhachHang.child(kh.getMaFB()).setValue(khachhang);
                Toast.makeText(getApplicationContext(), "Bạn Đã Cập nhật Thành Công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setControl() {
        btnTroVe =findViewById(R.id.btnTroVe);
        btnSuaKH =findViewById(R.id.btnSuaKH);
        edtTenKH =findViewById(R.id.edtTenKH);
        edtCMND =findViewById(R.id.edtCMND);
        edtSDT = findViewById(R.id.edtSDT);
    }
}