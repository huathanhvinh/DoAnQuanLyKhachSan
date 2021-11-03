package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.SuDungDichVu;
import com.example.doanquanlykhachsan.model.nvtv_qlphong;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class nhanvientapvu_sudungdichvu extends AppCompatActivity {
    ListView lvSuDungDV ;
    Spinner spDichVu;
    ArrayList<SuDungDichVu> data = new ArrayList<>();
    Button btnTroVe,btnDangKyDV;
    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvientapvu_sudungdichvu);
        SetControl();
        setEvent();
    }

    private void SetControl() {
        btnDangKyDV = findViewById(R.id.btnDangKyDV);
        lvSuDungDV = findViewById(R.id.lvSuDungDV);
        btnTroVe = findViewById(R.id.btnTroVe);
        spDichVu = findViewById(R.id.spDichVu);
    }

    private void setEvent() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnDangKyDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),nhanvientapvu_dangkydichvu.class));
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

        //Lấy dữ liệu các dịch vụ từ firebase vô Spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        StaticConfig.mDichVu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    arrayList.add(ds.child("mota").getValue(String.class));
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });
        spDichVu.setAdapter(arrayAdapter);

    }
}