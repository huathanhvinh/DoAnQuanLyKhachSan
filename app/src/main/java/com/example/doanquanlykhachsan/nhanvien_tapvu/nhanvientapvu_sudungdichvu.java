package com.example.doanquanlykhachsan.nhanvien_tapvu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.doanquanlykhachsan.model.Phong;
import com.example.doanquanlykhachsan.model.PhongDaDat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class nhanvientapvu_sudungdichvu extends AppCompatActivity {
    ListView lvSuDungDV;

    ArrayList<Phong> data = new ArrayList<>();
    Button btnTroVe;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> temp = new ArrayList<>();
    String loaiDV = "";
    static public custom_nhanvientapvu_sudungdichvu sudungdichvu;

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
        loaiDV = (String) getIntent().getStringExtra("XemPhong");
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
        StaticConfig.mRoomRented.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    PhongDaDat da = ds.getValue(PhongDaDat.class);
                    String maPhong = da.getMaPhong();
                    String maLoai = da.getMaDichVu();
                    String[] parts1;
                    parts1 = maPhong.split(" ");
                    String[] parts2;
                    parts2 = maLoai.split(" ");
                    for (String w : parts1) {
                        StaticConfig.mRoom.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    Phong phong = ds.getValue(Phong.class);
                                        if (phong.getMaPhong().equals(w)) {
                                            for (String u : parts2) {
                                            if (loaiDV.equals(u)) {
                                                data.add(phong);
                                                Log.e("ma",da.getMaDichVu());
                                            }
                                        }
                                    }
                                }

                                sudungdichvu = new custom_nhanvientapvu_sudungdichvu(getApplicationContext(), R.layout.listview_nhanvientapvu_sudungdichvu, data);
                                lvSuDungDV.setAdapter(sudungdichvu);
                                sudungdichvu.notifyDataSetChanged();
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

}