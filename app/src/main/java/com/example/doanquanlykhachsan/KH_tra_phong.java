package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class KH_tra_phong extends AppCompatActivity {
    private Button btntrove;
    private TextView phongdathue, hoten, songayo, ngaynhan, ngaytra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_tra_phong);
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
        phongdathue = findViewById(R.id.tvsophong);
        hoten = findViewById(R.id.tvhoten);
        songayo = findViewById(R.id.tvsongay);
        ngaynhan = findViewById(R.id.tvNgayNhanPhong);
        ngaytra = findViewById(R.id.tvNgayTraPhong);
        khoitao();
        btntrove = findViewById(R.id.btntrove);
    }

    private void khoitao() {
        StaticConfig.mRoomRented.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String phongthue="";
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("sMaKH").getValue().toString().equals("KH1")) {
                        phongthue += ds.child("sMaPH").getValue(String.class)+", ";
                    }
                }
                phongdathue.setText(phongthue);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}