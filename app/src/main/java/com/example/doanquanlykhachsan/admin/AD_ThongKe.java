package com.example.doanquanlykhachsan.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.HoaDon;
import com.example.doanquanlykhachsan.adapter.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AD_ThongKe extends AppCompatActivity {
    Button btnTroVe;
    GridView gridView;
    ArrayList<HoaDon> data = new ArrayList<>();
    Adapter_thongke adapte;
    TextView tvTongtien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_thong_ke);
        setControl();
        setEvent();
    }

    private void setControl() {

        btnTroVe = findViewById(R.id.btnTroVe);
        gridView = findViewById(R.id.listhoadon);
        tvTongtien = findViewById(R.id.tvTongTien);
        adapte = new Adapter_thongke(getApplicationContext(), R.layout.item_hoadon, data);
        gridView.setAdapter(adapte);
        khoitao();
    }

    private void khoitao() {
        StaticConfig.mHoaDon.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    HoaDon hd = ds.getValue(HoaDon.class);
                    data.add(hd);
                }
                //Tong tien
                float Tong = 0;
                for (int i = 0; i < data.size(); i++) {
                    Tong += data.get(i).getTongTien();
                }
                DecimalFormat toTheFormat = new DecimalFormat("###,###,###.#");
                tvTongtien.setText(toTheFormat.format(Tong)+" VNĐ");
                adapte.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void setEvent() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}