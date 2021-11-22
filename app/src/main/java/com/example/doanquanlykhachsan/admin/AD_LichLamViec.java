package com.example.doanquanlykhachsan.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.adapter.Adapter_NhanVien_LichLamViec;
import com.example.doanquanlykhachsan.model.NhanVien_LichLamViec;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class AD_LichLamViec extends AppCompatActivity {
    Button btnTroVe;
    EditText edTimKiem;
    ListView lvDSLVNV;
    Adapter_NhanVien_LichLamViec adapter_nhanVien_lichLamViec;
    ArrayList<NhanVien_LichLamViec> arrNV_lv = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_lich_lam_viec);
        setControl();
        setEvent();
    }

    private void setControl() {
        btnTroVe = findViewById(R.id.btnTroVe);
        edTimKiem = findViewById(R.id.edTimkiemViecLam);
        lvDSLVNV = findViewById(R.id.lvDSVLNV);
        adapter_nhanVien_lichLamViec = new Adapter_NhanVien_LichLamViec(getApplicationContext(),R.layout.custom_nhanvien_lichlamviec, arrNV_lv);
        lvDSLVNV.setAdapter(adapter_nhanVien_lichLamViec);
    }

    private void setEvent() {
        //Load thông tin việc làm của nhân viên
        layDanhSachNhanVien();
        //Xử lý nút trở về
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void layDanhSachNhanVien() {
        StaticConfig.mNhanVien_LichLamViec.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NhanVien_LichLamViec nv = snapshot.getValue(NhanVien_LichLamViec.class);
                arrNV_lv.add(nv);
                adapter_nhanVien_lichLamViec.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}