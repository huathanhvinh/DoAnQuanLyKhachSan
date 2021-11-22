package com.example.doanquanlykhachsan.nhanvien_tapvu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.DichVuDaChon;

import com.example.doanquanlykhachsan.adapter.custom_nhanvientapvu_sudungdichvu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class nhanvientapvu_sudungdichvu extends AppCompatActivity {
    ListView lvSuDungDV;
    Spinner spDichVu;
    ArrayList<DichVuDaChon> data = new ArrayList<>();
    Button btnTroVe;
    ArrayList<String> arrayList = new ArrayList<>();
    String loaiDV = "";

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
        spDichVu = findViewById(R.id.spDichVu);
    }

    private void setEvent() {
        loaiDV = (String) getIntent().getStringExtra("XemPhong");
        Log.d("loaiphong", loaiDV);
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        custom_nhanvientapvu_sudungdichvu sudungdichvu = new custom_nhanvientapvu_sudungdichvu(this, R.layout.listview_nhanvientapvu_sudungdichvu, data);
        lvSuDungDV.setAdapter(sudungdichvu);
        khoiTao();
        sudungdichvu.notifyDataSetChanged();

    }

    private void khoiTao() {
        StaticConfig.mDichVuDaChon.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("maDV").getValue().toString().equals(loaiDV)) {
                        DichVuDaChon suDungDichVu = ds.getValue(DichVuDaChon.class);
                        data.add(suDungDichVu);
                        custom_nhanvientapvu_sudungdichvu sudungdichvu = new custom_nhanvientapvu_sudungdichvu(getApplicationContext(), R.layout.listview_nhanvientapvu_sudungdichvu, data);
                        lvSuDungDV.setAdapter(sudungdichvu);
                        sudungdichvu.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //Lấy dữ liệu các dịch vụ từ firebase vô Spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        StaticConfig.mDichVu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    arrayList.add(ds.child("tenDV").getValue(String.class));
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