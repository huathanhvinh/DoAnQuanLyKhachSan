package com.example.doanquanlykhachsan.nhanvien_thungan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.adapter.Custom_NVTN_HienThiDanhSachKH;
import com.example.doanquanlykhachsan.adapter.Custom_NVTN_LapHoaDon;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NVTN_LapHoaDon extends AppCompatActivity {
    ListView lvHoaDon;
    Button btnTroVe;
    ArrayList<PhongDaDat> arrHoadon = new ArrayList<>();
    Custom_NVTN_LapHoaDon laphoadon;
    EditText edtiemkiem;
    private ArrayList<PhongDaDat> arrTimKiem = new ArrayList<>();
    private String maKH;
    private String makh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_lap_hoa_don);
        setControl();
        setEvent();
    }


    private void setEvent() {
        laphoadon = new Custom_NVTN_LapHoaDon(this, R.layout.listview_nvtn_lap_hoa_don, arrHoadon);
        lvHoaDon.setAdapter(laphoadon);
        KhoiTao();
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edtiemkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                arrTimKiem.clear();
                String timkiem = edtiemkiem.getText().toString().toLowerCase();
                if (!timkiem.equals("")) {
                    for (int i = 0; i < arrHoadon.size(); i++) {
                        PhongDaDat p = arrHoadon.get(i);
                        makh = p.getMaKH();
                        if (p.getSdt().toLowerCase().contains(timkiem) || p.getMaKH().contains(timkiem)) {
                            arrTimKiem.add(arrHoadon.get(i));
                        }
                    }

                    laphoadon = new Custom_NVTN_LapHoaDon(getApplicationContext(), R.layout.listview_nvtn_lap_hoa_don, arrTimKiem);
                    lvHoaDon.setAdapter(laphoadon);
                    laphoadon.notifyDataSetChanged();
                } else {
                    KhoiTao();
                }
            }
        });
    }

    private void KhoiTao() {
        StaticConfig.mRoomRented.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrHoadon.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    PhongDaDat da = ds.getValue(PhongDaDat.class);
                    arrHoadon.add(da);
                }
                laphoadon = new Custom_NVTN_LapHoaDon(getApplicationContext(), R.layout.listview_nvtn_lap_hoa_don, arrHoadon);
                lvHoaDon.setAdapter(laphoadon);
                laphoadon.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void setControl() {
        lvHoaDon = findViewById(R.id.lvHoaDon);
        btnTroVe = findViewById(R.id.btnTroVe);
        edtiemkiem = findViewById(R.id.editTimKiem);
    }
}