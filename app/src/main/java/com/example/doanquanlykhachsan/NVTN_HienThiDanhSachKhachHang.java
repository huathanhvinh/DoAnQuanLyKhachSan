package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.doanquanlykhachsan.model.*;

import java.util.ArrayList;

public class NVTN_HienThiDanhSachKhachHang extends AppCompatActivity {
    private Button btnThem , btnTroVe;
    ListView lvKhachHang;

    ArrayList<NVTN_HienThiDSKH>data= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_hien_thi_danh_sach_khach_hang);
        setControl();
        setEvent();
    }

    private void setEvent() {
        Custom_NVTN_HienThiDanhSachKH nhanvienthungan = new Custom_NVTN_HienThiDanhSachKH(this,R.layout.listview_nvtn_hienthidanhsachkhachhang,data);
        lvKhachHang.setAdapter(nhanvienthungan);
        KhoiTao();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NVTN_ThemKhachHang.class));
            }
        });
    }

    private void KhoiTao() {
        NVTN_HienThiDSKH DSkhachhang= new NVTN_HienThiDSKH("KH01","Dương Ngọc Thanh Duy");
        data.add(DSkhachhang);
        NVTN_HienThiDSKH DSkhachhang2= new NVTN_HienThiDSKH("KH01","Dương Ngọc Thanh Duy");
        data.add(DSkhachhang2);
    }

    private void setControl() {
        lvKhachHang =findViewById(R.id.lvKhachHang);
        btnThem = findViewById(R.id.btnThem);
        btnTroVe = findViewById(R.id.btnTroVe);
    }
}