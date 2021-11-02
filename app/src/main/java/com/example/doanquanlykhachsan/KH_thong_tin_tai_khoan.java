package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Dichvu;
import com.example.doanquanlykhachsan.model.Thongtin_Dich_vu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class KH_thong_tin_tai_khoan extends AppCompatActivity {
    private Button btntrove;
    private float Tongtien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_thong_tin_tai_khoan);
        setControl();
        setEvnet();
    }

    private void setEvnet() {
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setControl() {
        btntrove = findViewById(R.id.btntrove);
//        Dichvu dv = new Dichvu("DV2", "xe",100);
//        StaticConfig.mDichvu.child("DV2").setValue(dv);
//        Thongtin_Dich_vu thongtin = new Thongtin_Dich_vu("DV2","KH1","L01");
//        StaticConfig.mThongtinDv.child(StaticConfig.currentuser).child("DV2").setValue(thongtin);
        StaticConfig.mThongtinDv.child(StaticConfig.currentuser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String madv = ds.child("madv").getValue(String.class);
                    String maphong = ds.child("maphong").getValue(String.class);
                    StaticConfig.mRoom.child(maphong).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Tongtien += snapshot.child("giagio").getValue(Float.class);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    StaticConfig.mDichVu.child(madv).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Tongtien += snapshot.child("gia").getValue(Float.class);
                            Log.d("Tong tien", Tongtien + "");
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}