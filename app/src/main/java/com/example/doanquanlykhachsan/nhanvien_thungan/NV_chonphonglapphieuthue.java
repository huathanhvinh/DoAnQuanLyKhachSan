package com.example.doanquanlykhachsan.nhanvien_thungan;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.adapter.Adapter_phieuthue;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Phong;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NV_chonphonglapphieuthue extends AppCompatActivity {

    ImageView imgLen, imgXuong;
    LinearLayout lnchange;
    TextView chuoiphong;
    TextView tvLau;
    GridView gridView;
    int floor = 1;
    Adapter_phieuthue adapter;
    ArrayList<Phong> data = new ArrayList<>();
    Button btnTrove, btnTieptheo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_chonphong_lapphieuthue);
        setControl();
        setEvent();

        tvLau.setText("Lầu " + floor);
    }

    private void setEvent() {
        lnchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticConfig.arrayListTemporaryRoom.clear();
                StaticConfig.arrayListTemporaryService.clear();
                chuoiphong.setText("");
            }
        });
        imgLen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (floor < 3) {
                    floor++;
                    tvLau.setText("Lầu " + floor);

                    khoitao();
                }

            }
        });
        imgXuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (floor > 1) {
                    floor--;
                    tvLau.setText("Lầu" + floor);
                    khoitao();
                }
            }
        });
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(),NVTN_MenuNhanVienThuNgan.class));
            }
        });
        btnTieptheo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "qua ma hinh chi tiet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setControl() {
        imgLen = findViewById(R.id.imgLen);
        imgXuong = findViewById(R.id.imgXuong);
        tvLau = findViewById(R.id.tvLau);
        gridView = findViewById(R.id.ListRoom);
        btnTrove = findViewById(R.id.btnTroVe);
        btnTieptheo = findViewById(R.id.btntieptuc);
        lnchange = findViewById(R.id.lnchange);
        chuoiphong = findViewById(R.id.phongdachon);

        adapter = new Adapter_phieuthue(getApplicationContext(), R.layout.item_phieuthue, data);
        gridView.setAdapter(adapter);
        khoitao();
    }

    private void khoitao() {
        StaticConfig.mRoom.orderByChild("sophong").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Phong p = ds.getValue(Phong.class);
                    if (p.getLau() == floor) {
                        data.add(p);
                    }
                }
                String chuoi = "";
                for (int i = 0; i < StaticConfig.arrayListTemporaryRoom.size(); i++) {
                    chuoi += StaticConfig.arrayListTemporaryRoom.get(i).getSoPhong()+" ";
                }
                chuoiphong.setText(chuoi);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
