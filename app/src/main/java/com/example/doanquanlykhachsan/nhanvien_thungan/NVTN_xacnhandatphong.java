package com.example.doanquanlykhachsan.nhanvien_thungan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.PhongDaDat;

public class NVTN_xacnhandatphong extends AppCompatActivity {
    Button btnXacnhan;
    PhongDaDat chitiet;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_xacnhandatphong);
        setControl();
        setEvent();
        Log.e("dasd",chitiet.getMaDichVu());
    }

    private void setEvent() {
        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < StaticConfig.arrayListTemporaryRoom.size(); i++) {
                    StaticConfig.mRoom.child(StaticConfig.arrayListTemporaryRoom.get(i).getMaFB()).child("trangThai").setValue("Đã Đặt Phòng");
                }
                StaticConfig.mRoomRented.child(key).setValue(chitiet);
                startActivity(new Intent(getApplicationContext(), NV_chonphonglapphieuthue.class));
            }
        });
    }

    private void setControl() {
        chitiet = (PhongDaDat) getIntent().getSerializableExtra("chitiet");
        key = getIntent().getStringExtra("ma");
        btnXacnhan = findViewById(R.id.btnXacNhan);
    }
}