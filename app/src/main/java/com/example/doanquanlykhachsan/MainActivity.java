package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Room;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String ma = "";
    ArrayList<Long> data = new ArrayList<>();
//code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //màn hình loading....
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (StaticConfig.fAuth.getCurrentUser() != null) {
//                    startActivity(new Intent(getApplicationContext(), menu_khachhang.class));
//                } else {
//                    startActivity(new Intent(getApplicationContext(), sign_in.class));
//                }
//            }
//        }, 0);
        startActivity(new Intent(getApplicationContext(),register.class));
    }

}