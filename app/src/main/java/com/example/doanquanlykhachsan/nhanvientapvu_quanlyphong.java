package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import  com.example.doanquanlykhachsan.model.*;

import java.util.ArrayList;

public class nhanvientapvu_quanlyphong extends AppCompatActivity {
    ListView lvQuanLyPhong;
    Button btnKiemTra;
    ArrayList<nvtv_qlphong>data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvientapvu_quanlyphong);
        setControl();
        setEvent();
    }

    private void setControl() {
        lvQuanLyPhong = findViewById(R.id.lvQuanLyPhong);
    }

    private void setEvent() {
        custom_nhanvientapvu_qlphong nhanvientapvu = new custom_nhanvientapvu_qlphong(this,R.layout.listview_nhanvientapvu_quanlyphong,data);
        lvQuanLyPhong.setAdapter(nhanvientapvu);
        khoitao();
        nhanvientapvu.notifyDataSetChanged();
    }

    private void khoitao() {
        nvtv_qlphong qlphong = new nvtv_qlphong("phòng:2","Kiểm tra tình trạng phòng");
        nvtv_qlphong qlphong1 = new nvtv_qlphong("phòng:3","Kiểm tra tình trạng phòng");
        data.add(qlphong);
        data.add(qlphong1);
    }
}