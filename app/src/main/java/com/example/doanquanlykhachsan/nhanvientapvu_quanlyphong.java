package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class nhanvientapvu_quanlyphong extends AppCompatActivity {
    ListView lvQuanLyPhong;
    Button btnTroVe;
    CheckBox ckDaChon,ckChuaChon;
    custom_nhanvientapvu_qlphong nhanvientapvu;

    ArrayList<Room> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvientapvu_quanlyphong);
        setControl();
        setEvent();
    }

    private void setControl() {
        lvQuanLyPhong = findViewById(R.id.lvQuanLyPhong);
        btnTroVe = findViewById(R.id.btnTroVe);
        ckDaChon = findViewById(R.id.ckChuaChon);
        ckChuaChon = findViewById(R.id.ckChuaChon);

    }

    private void setEvent() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nhanvientapvu = new custom_nhanvientapvu_qlphong(this, R.layout.listview_nhanvientapvu_quanlyphong, data);
        lvQuanLyPhong.setAdapter(nhanvientapvu);
        khoitao();


        ckDaChon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ckDaChon.isChecked())
                {
                    StaticConfig.mQLPhong.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot ds : snapshot.getChildren())
                            {
                                nvtv_qlphong qlphong = ds.getValue(nvtv_qlphong.class);
//                                if(qlphong.getPhong().equals(data.))

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else
                {
                    khoitao();
                }
            }
        });


    }

    private void khoitao() {
//        nvtv_qlphong qlphong = new nvtv_qlphong("phòng:2","Kiểm tra tình trạng phòng");
//        nvtv_qlphong qlphong1 = new nvtv_qlphong("phòng:3","Kiểm tra tình trạng phòng");
//        data.add(qlphong);
//        data.add(qlphong1);
        Query sapxep = StaticConfig.mRoom.orderByChild("sophong");
        sapxep.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Room room = ds.getValue(Room.class);
                    data.add(room);
                }

                nhanvientapvu.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}