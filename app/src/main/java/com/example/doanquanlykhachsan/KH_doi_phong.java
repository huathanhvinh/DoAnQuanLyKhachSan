package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class KH_doi_phong extends AppCompatActivity {
    private TextView chinhsach;
    private Button btntrove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_doi_phong);
        SetControl();
        setEvnet();
    }

    private void setEvnet() {
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        chinhsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),KH_chinh_sach.class));
            }
        });
    }

    private void SetControl() {
        String idRoom = getIntent().getStringExtra("idroom");
        Log.d("test",idRoom);
        btntrove=findViewById(R.id.btntrove);
        chinhsach=findViewById(R.id.chinhsach);
    }
}