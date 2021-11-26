package com.example.doanquanlykhachsan.khach_hang;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.DichVu;
import com.example.doanquanlykhachsan.adapter.KH_CustomDichvu;
import com.example.doanquanlykhachsan.model.Phong;
import com.example.doanquanlykhachsan.model.PhongDaDat;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KH_ChiTietPhong extends AppCompatActivity {
    TextView tvTenPhong, tvLau, tvLoaiPhong, tvMoTa, tvGia;
    Button btnDatPhong, btntroVe;
    GridView gridView;
    ArrayList<DichVu> data = new ArrayList<>();
    KH_CustomDichvu customDichvu;
    String tendv = "";
    String loai = "";
    String maDichvu = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_chi_tiet_phong);
        StaticConfig.arrayListTemporaryService.clear();

        setConTrol();
        Phong chitiet = (Phong) getIntent().getSerializableExtra("chitiet");
        tvTenPhong.setText(chitiet.getTenPhong() + "");
        tvLau.setText(chitiet.getLau() + "");
        tvLoaiPhong.setText(chitiet.getLoai() + "");
        tvMoTa.setText(chitiet.getMoTa() + "");
        Float gia = (Float) getIntent().getFloatExtra("Gia", 0);
        tvGia.setText(gia + "");

        setEvent();

    }

    private void setEvent() {
        customDichvu = new KH_CustomDichvu(getApplicationContext(), R.layout.kh_item_ds_dich_vu, data);
        gridView.setAdapter(customDichvu);
        khoitao();
        btnDatPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnDatPhong.getText().toString().equals("Xác nhận")) {
                    StaticConfig.mRoomRented.child(StaticConfig.mathue).child("maDichVu").setValue("");
                    maDichvu="";
                    for (int i = 0; i < StaticConfig.arrayListTemporaryService.size(); i++) {
                        maDichvu += StaticConfig.arrayListTemporaryService.get(i).getMaFB() + " ";
                    }

                    Log.e("solan",StaticConfig.arrayListTemporaryService.size()+"");
                    StaticConfig.mRoomRented.child(StaticConfig.mathue).child("maDichVu").setValue(maDichvu);


                } else {
                    Intent intent = new Intent(getApplicationContext(), KH_XacNhanDatPhong.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ngaynhan", StaticConfig.NgayNhanXacNhanPhong + "");
                    bundle.putString("ngaytra", StaticConfig.NgayNhanXacTraPhong + "");
                    intent.putExtras(bundle);
                    intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            }
        });
        btntroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticConfig.arrayListTemporaryRoom.clear();
                finish();
            }
        });
    }

    private void khoitao() {
        StaticConfig.mDichVu.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    DichVu dv = ds.getValue(DichVu.class);
                    data.add(dv);
                }
                customDichvu.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void setConTrol() {
        tvTenPhong = findViewById(R.id.tvTenPhong);
        tvLau = findViewById(R.id.tvLau);
        tvLoaiPhong = findViewById(R.id.tvLoaiPhong);
        tvMoTa = findViewById(R.id.tvMoTa);
        tvGia = findViewById(R.id.tvGia);
        btnDatPhong = findViewById(R.id.btnDatPhong);
        btntroVe = findViewById(R.id.btnTroVe);
        gridView = findViewById(R.id.gv_dv);
        if (StaticConfig.sXacNhan.equals("phong da thue")) {
            btnDatPhong.setText("Xác nhận");
        }

    }
}