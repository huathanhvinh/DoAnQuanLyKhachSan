package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.*;

public class NVTN_Thongtinkhachhang extends AppCompatActivity {
    Button btnTroVe;
    TextView tvSuaKH,tvXoaKH,tvMaKH,tvTenKH,tvDiachi,tvCMND,tvSoDT;
    KhachHang kh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_thongtinkhachhang);
        setControl(); setEvent();
        KhoiTao();
    }

    private void setEvent() {
        tvXoaKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticConfig.mKhachHang.child(kh.getMaFB()).setValue(null);
                finish();
                Toast.makeText(getApplicationContext(), "Bạn Đã xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        tvSuaKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),NVTN_SuaKhachHang.class));
                Intent intent = new Intent(getApplicationContext(), NVTN_SuaKhachHang.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("SuaKhachHang", kh);
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void KhoiTao() {
        kh = (KhachHang) getIntent().getSerializableExtra("ThongTinKhachHang");
        tvMaKH.setText("KH"+kh.getStt());
        tvTenKH.setText(kh.getTenKH()+"");
        tvDiachi.setText(kh.getDiaChi()+"");
        tvCMND.setText(kh.getCmnd()+"");
        tvSoDT.setText(kh.getSdtKH()+"");
    }

    private void setControl() {
        tvCMND = findViewById(R.id.tvCMND);
        tvDiachi = findViewById(R.id.tvDiachi);
        tvTenKH = findViewById(R.id.tvTenKH);
        tvMaKH = findViewById(R.id.tvMaKH);
        tvSuaKH = findViewById(R.id.tvSuaKH);
        tvXoaKH = findViewById(R.id.tvXoaKH);
        btnTroVe = findViewById(R.id.btnTroVe);
        tvSoDT = findViewById(R.id.tvSoDT);
    }
}