package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class AD_MenuAdmin extends AppCompatActivity implements View.OnClickListener{
    //TextView tvDSNV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_menuadmin);
        setControl();
        setEvent();
        /*StaticConfig.mNhanVien.addChildEventListener(new ChildEventListener() {
            NhanVien nv = new NhanVien();
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                nv = snapshot.getValue(NhanVien.class);
                if (nv.getStt() == 1)
                {   nv.setDiaChi("HCM");
                    StaticConfig.mNhanVien.child(nv.getMaFB()+"").setValue(nv);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

    }

    private void setEvent() {
    }

    private void setControl() {
        //tvDSNV = findViewById(R.id.tvDSNV);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvDSNV:
                startActivity(new Intent(getApplicationContext(), AD_HienThiDanhSachNhanVien.class));
                break;
        }
    }
}