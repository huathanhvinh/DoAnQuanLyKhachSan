package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Room;

import java.util.ArrayList;

public class KH_ChiTietPhong extends AppCompatActivity {
    TextView tvTenPhong, tvLau, tvLoaiPhong, tvSoLuong, tvGia;
    Button btnDatPhong, btntroVe;

    ArrayList<Room> roomArrayList = new ArrayList<>();
    KH_CusTomXacNhanDatPhong customRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_chi_tiet_phong);

        setConTrol();

        Room chitiet = (Room) getIntent().getSerializableExtra("chitiet");
        tvTenPhong.setText(chitiet.getTen()+"");
        tvLau.setText(chitiet.getSophong()+"");
        tvLoaiPhong.setText(chitiet.getLoai()+"");
        tvSoLuong.setText(chitiet.getSoluong()+"");
        Float gia = (Float) getIntent().getFloatExtra("Gia",0);
        tvGia.setText(gia+"");
        setEvent();
        //Toast.makeText(getApplicationContext(),chitiet.getMa(), Toast.LENGTH_LONG).show();


    }

    private void setEvent() {
        btnDatPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KH_XacNhanDatPhong.class);
                Bundle bundle = new Bundle();
                bundle.putString("ngaynhan",StaticConfig.NgayNhanXacNhanPhong+"");
                bundle.putString("ngaytra",StaticConfig.NgayNhanXacTraPhong+"");
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        btntroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticConfig.arrayListCheckItem.clear();
                finish();
            }
        });
    }

    private void setConTrol() {
        tvTenPhong = findViewById(R.id.tvTenPhong);
        tvLau = findViewById(R.id.tvLau);
        tvLoaiPhong = findViewById(R.id.tvLoaiPhong);
        tvSoLuong = findViewById(R.id.tvSoLuong);
        tvGia = findViewById(R.id.tvGia);
        btnDatPhong = findViewById(R.id.btnDatPhong);
        btntroVe = findViewById(R.id.btnTroVe);
    }

}