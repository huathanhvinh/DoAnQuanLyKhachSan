package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.doanquanlykhachsan.admin.AD_HienThiDanhSachNhanVien;

public class MainActivity extends AppCompatActivity {
    String ma = "";
    ArrayList<Long> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //màn hình loading....
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (StaticConfig.fAuth.getCurrentUser() != null) {
                    startActivity(new Intent(getApplicationContext(), menu_khachhang.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), sign_in.class));
                }
            }
        }, 2500);

    }

}