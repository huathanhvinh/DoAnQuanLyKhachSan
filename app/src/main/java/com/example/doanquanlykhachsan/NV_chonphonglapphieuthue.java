package com.example.doanquanlykhachsan;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NV_chonphonglapphieuthue extends AppCompatActivity {

    ImageView imgLen, imgXuong;
    TextView tvLau;
    int floor = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_chonphong_lapphieuthue);


        setControl();
        setEvent();
        tvLau.setText("Lầu"+floor);


    }

    private void setEvent() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        imgLen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floor = floor++;
                tvLau.setText("Lầu"+floor);
                Fragment_f2 f2 = new Fragment_f2();
                fragmentTransaction.add(R.id.ListRoom, f2);

            }
        });
        imgXuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (floor== 2){
                    floor = floor--;
                    tvLau.setText("Lầu"+floor);
                    Fragment_f1 f1 = new Fragment_f1();
                    fragmentTransaction.add(R.id.ListRoom,f1);
                }
            }
        });
    }

    private void setControl() {
        imgLen = findViewById(R.id.imgLen);
        imgXuong = findViewById(R.id.imgXuong);
        tvLau = findViewById(R.id.tvLau);
    }
}
