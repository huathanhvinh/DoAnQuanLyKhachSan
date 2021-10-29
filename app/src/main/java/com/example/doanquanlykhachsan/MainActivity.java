package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.example.doanquanlykhachsan.model.Room;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //StaticConfig st = new StaticConfig();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        String key = StaticConfig.mNhanVien.push().getKey();
//        NhanVien nv = new NhanVien(4,key,"Trần Văn Cao","0933 123 003","Gò Vấp, HCM","01/01/2003","1909292102","5,000,000","Lễ Tân");
//        StaticConfig.mNhanVien.child(key).setValue(nv);
        startActivity(new Intent(getApplicationContext(), AD_HienThiDanhSachNhanVien.class));

    }

}