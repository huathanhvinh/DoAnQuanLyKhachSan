package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    Button btnTroVe,btnThem;
    EditText edTimKiem;
    ImageButton imTimKiem,imRF;
    Adapter_NhanVien adapter_nhanVien;
    ArrayList<NhanVien> arrNV = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_hien_thi_danh_sach_nhan_vien);
        setControl();
        setEvent();

    }

    private void setEvent() {
        //Thêm nhân viên
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AD_HienThiDanhSachKhachHang.class));
            }
        });
        //Sự kiện load dữ liệu từ Firebase lên listview
        LayDSNV();
        //sự kiện tìm kiếm
        edTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ArrayList<NhanVien> kq = new ArrayList<>();
                String key = edTimKiem.getText().toString().toLowerCase();
                for (int i = 0;i<arrNV.size();i++)
                {
                    if(arrNV.get(i).getTenNV().toLowerCase().contains(key)){
                        kq.add(arrNV.get(i));
                    }
                }
                Adapter_NhanVien adapter_nhanVien = new Adapter_NhanVien(getApplicationContext(),R.layout.custom_nhan_vien, kq);
                lvDSNV.setAdapter(adapter_nhanVien);
                adapter_nhanVien.notifyDataSetChanged();
            }
        });
        //code lỗi không click đc !!!!!
        lvDSNV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhanVien nv = arrNV.get(position);
                Toast.makeText(getApplicationContext(), nv.getMaFB(), Toast.LENGTH_LONG).show();
            }
        });
        //sự kiện trở về
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //refresh
        imRF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrNV.clear();
                LayDSNV();
            }
        });
    }

    private void setControl() {
        btnThem = findViewById(R.id.btmThemNV);
        btnTroVe = findViewById(R.id.btnTroVe);
        maNV = findViewById(R.id.maNV);
        tenNV = findViewById(R.id.tenNV);
        lvDSNV = findViewById(R.id.lvDSNV);
        edTimKiem = findViewById(R.id.edTimNhanVien);
        imTimKiem = findViewById(R.id.imTimKiem);
        imRF = findViewById(R.id.imRefresh);


        adapter_nhanVien = new Adapter_NhanVien(getApplicationContext(),R.layout.custom_nhan_vien, arrNV);
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