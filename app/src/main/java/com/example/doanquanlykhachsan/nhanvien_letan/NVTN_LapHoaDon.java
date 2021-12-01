package com.example.doanquanlykhachsan.nhanvien_letan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


import com.example.doanquanlykhachsan.R;
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
    ArrayList<PhongDaDat>arrHoadon=new ArrayList<>();
    Custom_NVTN_LapHoaDon laphoadon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_lap_hoa_don);
        setControl();
        LapHoaDon();
        setEvent();
    }

    private void LapHoaDon() {

    }

    private void setEvent() {
        laphoadon = new Custom_NVTN_LapHoaDon(this,R.layout.listview_nvtn_lap_hoa_don,arrHoadon);
        lvHoaDon.setAdapter(laphoadon);
        KhoiTao();
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void KhoiTao() {
        StaticConfig.mRoomRented.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    PhongDaDat da = ds.getValue(PhongDaDat.class);
                    arrHoadon.add(da);
                }
                laphoadon.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setControl() {
        lvHoaDon=findViewById(R.id.lvHoaDon);
        btnTroVe = findViewById(R.id.btnTroVe);
    }
}