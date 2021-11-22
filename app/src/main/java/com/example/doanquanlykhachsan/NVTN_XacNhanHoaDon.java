package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.doanquanlykhachsan.model.*;

public class NVTN_XacNhanHoaDon extends AppCompatActivity {
    Button btnXacNhan,btnTroVe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NVTN_LapHoaDon hoaDon = (NVTN_LapHoaDon) getIntent().getSerializableExtra("XacNhanHoaDon");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_xac_nhan_hoa_don);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setControl() {
        btnXacNhan=findViewById(R.id.btnXacNhan);
        btnTroVe=findViewById(R.id.btnTroVe);
    }
}