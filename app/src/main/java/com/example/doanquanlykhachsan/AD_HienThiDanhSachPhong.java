package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AD_HienThiDanhSachPhong extends AppCompatActivity {
    Button btnTroVe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_hien_thi_danh_sach_phong);
        setControl();
        setEvent();
        Toast.makeText(getApplicationContext(), "hihi", Toast.LENGTH_SHORT).show();
    }

    private void setControl() {
        btnTroVe = findViewById(R.id.btnTroVe);
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