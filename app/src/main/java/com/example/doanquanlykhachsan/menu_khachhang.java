package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanquanlykhachsan.helpers.*;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class menu_khachhang extends AppCompatActivity {

    private ImageView avatar;
    private TextView name;
    private TextView test;
    private Button dangxuat;
    private TextView traphong, danhsachdaphong, thongtin;
    ArrayList<Long> soluong = new ArrayList<>();
    ArrayList<Room> data = new ArrayList<>();
    int i;

    String key;
    Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_khachhang);
//        String ma ="";
//        for (int i = 1; i < 21; i++) {
//            ma= "L"+i;
//            StaticConfig.mRoom.child(ma).setValue(new Room(ma,"Phòng "+i,"Đơn","trống",500000,150000,i,2));
//        }
        setControl();
        setEvent();
    }

    private void setEvent() {
        traphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), KH_tra_phong.class));
            }
        });
        thongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), KH_thong_tin_tai_khoan.class));
            }
        });
        danhsachdaphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), KH_danh_sach_phong_da_dat.class));
            }
        });
        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(menu_khachhang.this)
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to sign out of the app??")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                StaticConfig.fAuth.signOut();
                                startActivity(new Intent(menu_khachhang.this, MainActivity.class));
                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    private void setControl() {
        test = findViewById(R.id.text);
        avatar = findViewById(R.id.avatar);
        name = findViewById(R.id.name);
        dangxuat = findViewById(R.id.dangxuat);
        traphong = findViewById(R.id.traphong);
        danhsachdaphong = findViewById(R.id.phongdadat);
        thongtin = findViewById(R.id.thongtin);
//        String emailhientai = FirebaseAuth.getInstance().getCurrentUser().getEmail();
//        StaticConfig.mUser.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    if (ds.child("email").getValue(String.class).equals(emailhientai)) {
//                        StaticConfig.currentuser = ds.child("id").getValue(String.class);
//                        name.setText(ds.child("name").getValue(String.class));
//                    }
//                }
//                if (name.getText().toString().isEmpty()) {
//                    name.setText(emailhientai);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) { }
//        });


    }
}