package com.example.doanquanlykhachsan.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.adapter.Adapter_NhanVien_Luong;
import com.example.doanquanlykhachsan.model.NhanVien_Luong;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class AD_LuongNhanVien extends AppCompatActivity {
    EditText edTimKiemLuong;
    ListView lvDSLNV;
    Button btnTroVe;
    Adapter_NhanVien_Luong adapter_nhanVien_luong;
    ArrayList<NhanVien_Luong> arrNv_L = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_luong_nhan_vien);
        setControl();
        setEvent();
    }
    private void setControl() {
        edTimKiemLuong = findViewById(R.id.edTimKiemLuong);
        lvDSLNV = findViewById(R.id.lvDSLNV);
        btnTroVe = findViewById(R.id.btnTroVe);
        adapter_nhanVien_luong = new Adapter_NhanVien_Luong(getApplicationContext(),R.layout.custom_nhanvien_luong,arrNv_L);
        lvDSLNV.setAdapter(adapter_nhanVien_luong);
    }

    private void setEvent() {
        //xử lý nút tìm kiếm
        edTimKiemLuong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ArrayList<NhanVien_Luong> kq = new ArrayList<>();
                String key = edTimKiemLuong.getText().toString().toLowerCase();
                for (int i = 0;i<arrNv_L.size();i++)
                {
                    if(arrNv_L.get(i).getTenNV().toLowerCase().contains(key)){
                        kq.add(arrNv_L.get(i));
                    }
                }
                Adapter_NhanVien_Luong adapter_nhanVien_luong  = new Adapter_NhanVien_Luong(getApplicationContext(),R.layout.custom_nhanvien_luong,kq);
                lvDSLNV.setAdapter(adapter_nhanVien_luong);
                adapter_nhanVien_luong.notifyDataSetChanged();
            }
        });
        //lấy danh sách lương nhân viên
        layDSLNV();
        //Sự kiện nút trở về
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void layDSLNV()
    {
        StaticConfig.mNhanVien_Luong.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NhanVien_Luong nv_l = snapshot.getValue(NhanVien_Luong.class);
                arrNv_L.add(nv_l);
                adapter_nhanVien_luong.notifyDataSetChanged();
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