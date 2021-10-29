package com.example.doanquanlykhachsan;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doanquanlykhachsan.helpers.StaticConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //kiểm tra coi đã login chưa
        if (StaticConfig.fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), menu_khachhang.class));
        } else {
            startActivity(new Intent(getApplicationContext(), sign_in.class));
        }
//
//        StaticConfig.mDichvu.child("b1").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot ds:snapshot.getChildren())
//                {
//                    if(snapshot.child("ThongtinDv").exists()){
//                        Thongtin_Dich_vu thongtin_dich_vu = snapshot.
//                                child("ThongtinDv").child("a1").getValue(Thongtin_Dich_vu.class);
//                        Log.d("data",thongtin_dich_vu.getGia()+"");
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


//
//        StaticConfig.mThongtinDv.child("a1").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    ma = ds.child("ma").getValue(String.class);
//                    gia = ds.child("gia").getValue(Float.class);
//                    madv = ds.child("madv").getValue(String.class);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//        StaticConfig.mDichvu.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                 if(madv.equals(ds.child("ma").getValue(String.class))){
//
//                 }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


    }

}