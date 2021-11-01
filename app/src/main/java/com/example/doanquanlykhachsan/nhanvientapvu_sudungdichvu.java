package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.doanquanlykhachsan.model.SuDungDichVu;
import com.example.doanquanlykhachsan.model.nvtv_qlphong;

import java.util.ArrayList;

public class nhanvientapvu_sudungdichvu extends AppCompatActivity {
    ListView lvSuDungDV ;
    ArrayList<SuDungDichVu> data = new ArrayList<>();
    Button btnTroVe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvientapvu_sudungdichvu);
        SetControl();
        setEvent();

    }

    private void SetControl() {
        lvSuDungDV = findViewById(R.id.lvSuDungDV);
        btnTroVe = findViewById(R.id.btnTroVe);
    }

    private void setEvent() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        custom_nhanvientapvu_sudungdichvu sudungdichvu = new custom_nhanvientapvu_sudungdichvu(this,R.layout.listview_nhanvientapvu_sudungdichvu,data);
        lvSuDungDV.setAdapter(sudungdichvu);
        khoiTao();
        sudungdichvu.notifyDataSetChanged();

    }

    private void khoiTao() {
        SuDungDichVu suDungDichVu = new SuDungDichVu("Phòng : 1","Hủy sử dụng dịch vụ");
        SuDungDichVu suDungDichVu1 = new SuDungDichVu("Phòng : 2","Hủy sử dụng dịch vụ");
        SuDungDichVu suDungDichVu2 = new SuDungDichVu("Phòng : 3","Hủy sử dụng dịch vụ");
        SuDungDichVu suDungDichVu3 = new SuDungDichVu("Phòng : 4","Hủy sử dụng dịch vụ");
        data.add(suDungDichVu);
        data.add(suDungDichVu1);
        data.add(suDungDichVu2);
        data.add(suDungDichVu3);
    }
}