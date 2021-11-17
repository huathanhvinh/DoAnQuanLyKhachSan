package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Phong;
import com.example.doanquanlykhachsan.model.Room;
import com.example.doanquanlykhachsan.model.RoomRented;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class KH_doi_phong extends AppCompatActivity {
    private TextView chinhsach, tvtenphong;
    private Button btntrove, btnxacnhan;
    Phong chitiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_doi_phong);
        chitiet = (Phong) getIntent().getSerializableExtra("chitiet");
        SetControl();
        setEvnet();
    }

    private void setEvnet() {
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticConfig.mRoom.child(StaticConfig.chon.getMaFB()).child("trangThai").setValue("trống");
                StaticConfig.mRoom.child(chitiet.getMaFB()).child("trangThai").setValue("đã đặt");
                StaticConfig.mRoomRented.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            if (ds.child("maKH").getValue().toString().equals(StaticConfig.currentuser) && ds.child("sMaPH")
                                    .getValue(String.class).equals(StaticConfig.chon.getMaFB())) {
                                Log.d("test",ds.child("maFB").getValue(String.class));
                                StaticConfig.mRoomRented.child(ds.child("maFB").getValue(String.class)).
                                        child("maPhong").setValue(chitiet.getMaFB());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                startActivity(new Intent(getApplicationContext(),KH_danh_sach_phong_da_dat.class));

            }
        });
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        chinhsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), KH_chinh_sach.class));
            }
        });
    }

    private void SetControl() {
        btntrove = findViewById(R.id.btntrove);
        chinhsach = findViewById(R.id.chinhsach);
        btnxacnhan = findViewById(R.id.btnxacnhan);
        tvtenphong = findViewById(R.id.tvTenPhong);
        tvtenphong.setText("Phòng " + chitiet.getSoPhong());
    }
}