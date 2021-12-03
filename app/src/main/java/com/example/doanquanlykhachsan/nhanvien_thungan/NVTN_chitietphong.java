package com.example.doanquanlykhachsan.nhanvien_thungan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.doanquanlykhachsan.R;

public class NVTN_chitietphong extends AppCompatActivity {
    Button btnTrove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_chitiephong);
        setControl();
        setEvnet();
    }

    private void setEvnet() {
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NV_chonphonglapphieuthue.class));
            }
        });
    }

    private void setControl() {
        btnTrove = findViewById(R.id.btnTroVe);
    }
}