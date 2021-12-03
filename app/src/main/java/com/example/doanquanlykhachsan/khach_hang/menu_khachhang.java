package com.example.doanquanlykhachsan.khach_hang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanquanlykhachsan.khach_hang.*;
import com.example.doanquanlykhachsan.MainActivity;
import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class menu_khachhang extends AppCompatActivity {


    private ImageView avatar;
    private TextView name;
    private TextView test, tvDatPhongTheoNgay, getTvDatPhongTheoGio;
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
        StaticConfig.currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        setControl();
        setEvent();
        Log.e("role",StaticConfig.role+"");
        Log.e("cmnd",StaticConfig.currentCmnd+"");
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
                        .setTitle("Đăng xuất")
                        .setMessage("Bạn có muốn quay trở lại màn hình đăng xuất ?")
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
        tvDatPhongTheoNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menu_khachhang.this, KH_DatPhongTheoNgay.class));
            }
        });
        getTvDatPhongTheoGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menu_khachhang.this, KH_DatPhongTheoGio.class));
            }
        });
    }

    private void setControl() {
        test = findViewById(R.id.text);
        avatar = findViewById(R.id.avatar);
        name = findViewById(R.id.name);
        dangxuat = findViewById(R.id.dangxuat);
        traphong = findViewById(R.id.traphong);
        tvDatPhongTheoNgay = findViewById(R.id.tvDatPhongTheoNgay);
        getTvDatPhongTheoGio = findViewById(R.id.tvDatPhongTheoGio);
        danhsachdaphong = findViewById(R.id.phongdadat);
        thongtin = findViewById(R.id.thongtin);
        StaticConfig.mKhachHang.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    KhachHang kh = ds.getValue(KhachHang.class);
                    if (kh.getSdtKH().toString().equals(StaticConfig.currentphone)) {
                        name.setText(kh.getTenKH());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }
}