package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Adapter_NhanVien;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class AD_HienThiDanhSachNhanVien extends AppCompatActivity {
    TextView maNV,tenNV;
    ListView lvDSNV;
    Button btnTroVe;
    Adapter_NhanVien adapter_nhanVien;
    ArrayList<NhanVien> arrNV = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_hien_thi_danh_sach_nhan_vien);
        setControl();
        setEvent();
        LayDSNV();
    }

    private void setEvent() {
        //
        lvDSNV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhanVien nv = arrNV.get(position);
                Toast.makeText(getApplicationContext(), nv.getMaFB(), Toast.LENGTH_LONG).show();
            }
        });
        //
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setControl() {
        btnTroVe = findViewById(R.id.btnTroVe);
        maNV = findViewById(R.id.maNV);
        tenNV = findViewById(R.id.tenNV);
        lvDSNV = findViewById(R.id.lvDSNV);
        adapter_nhanVien = new Adapter_NhanVien(getApplicationContext(),R.layout.custom_nhanvien, arrNV);
        lvDSNV.setAdapter(adapter_nhanVien);
    }
    public void LayDSNV()
    {
        StaticConfig.mNhanVien.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NhanVien nv = snapshot.getValue(NhanVien.class);
                arrNV.add(nv);
                adapter_nhanVien.notifyDataSetChanged();
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