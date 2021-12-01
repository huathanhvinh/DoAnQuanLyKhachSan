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
    ArrayList<HoaDon>arrHoadon=new ArrayList<>();
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
//        String key = StaticConfig.mHoaDon.push().getKey();
//        HoaDon hoadon = new HoaDon(1,"Phúc","Hoàng","1/11/2021",key);
//        StaticConfig.mHoaDon.child(key).setValue(hoadon);
    }

    private void setEvent() {
        laphoadon = new Custom_NVTN_LapHoaDon(this,R.layout.listview_nvtn_lap_hoa_don,arrHoadon);
        lvHoaDon.setAdapter(laphoadon);
        KhoiTao();
        lvHoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               HoaDon hoadon= arrHoadon.get(position);
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
        StaticConfig.mHoaDon.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    HoaDon dshd = ds.getValue(HoaDon.class);
                    arrHoadon.add(dshd);
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