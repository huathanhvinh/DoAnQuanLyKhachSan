package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NVTN_HienThiDanhSachKhachHang extends AppCompatActivity {
    private Button btnThem , btnTroVe;
    ListView lvKhachHang;
    Custom_NVTN_HienThiDanhSachKH nhanvienthungan;
    ArrayList<NVTN_HienThiDSKH>arrKH= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_hien_thi_danh_sach_khach_hang);
        setControl();
        setEvent();
    }

    private void setEvent() {
        nhanvienthungan = new Custom_NVTN_HienThiDanhSachKH(this,R.layout.listview_nvtn_hienthidanhsachkhachhang,arrKH);
        lvKhachHang.setAdapter(nhanvienthungan);
        KhoiTao();
        lvKhachHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NVTN_HienThiDSKH kh = arrKH.get(position);
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NVTN_ThemKhachHang.class));
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
//        Query sapxep = StaticConfig.mKhachHang.orderByChild("TenKhachHang");
        StaticConfig.mKhachHang.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                arrKH.clear();
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    NVTN_HienThiDSKH dskh = ds.getValue(NVTN_HienThiDSKH.class);
                    arrKH.add(dskh);
                }
                nhanvienthungan.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setControl() {
        lvKhachHang =findViewById(R.id.lvKhachHang);
        btnThem = findViewById(R.id.btnThem);
        btnTroVe = findViewById(R.id.btnTroVe);
    }
}