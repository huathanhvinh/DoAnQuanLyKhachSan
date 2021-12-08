package com.example.doanquanlykhachsan.khach_hang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.adapter.Apapter_thongbao_kh;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.ThongBao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KH_Thongbao extends AppCompatActivity {
    Button btnTrove;
    ListView listView;
    Apapter_thongbao_kh apapter;
    private ArrayList<ThongBao> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_thongbao);
        setControl();
        setEvnet();
    }

    private void setEvnet() {
        apapter = new Apapter_thongbao_kh(getApplicationContext(), R.layout.item_thongbao, data);
        listView.setAdapter(apapter);
        khoitao();
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), menu_khachhang.class));
            }
        });
    }

    private void khoitao() {
        StaticConfig.mThongBao.orderByChild("stt").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    ThongBao tb = ds.getValue(ThongBao.class);
                    if (tb.getNguoinhan().equals(StaticConfig.currentuser) &&
                            !tb.getTrangThai().equals("Đã xác nhận")) {
                        data.add(0,tb);
                    }
                }
                apapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setControl() {
        listView = findViewById(R.id.list_thongbao);
        btnTrove = findViewById(R.id.btnTroVe);
    }

}