package com.example.doanquanlykhachsan.khach_hang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.doanquanlykhachsan.R;

public class KH_chinh_sach extends AppCompatActivity {
    private Button btntrove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_chinh_sach);
        btntrove=findViewById(R.id.btntrove);
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}