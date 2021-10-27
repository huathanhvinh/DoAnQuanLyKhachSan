package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class nhanvientapvu_thongtintaikhoan extends AppCompatActivity {
    Button btnTroVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvientapvu_thongtintaikhoan);
        setControl();
        setEvent();

    }

    private void setEvent() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Nhanvientapvu_manhinhchinh.class));
            }
        });
    }

    private void setControl() {
        btnTroVe = findViewById(R.id.btnTroVe);
    }
}