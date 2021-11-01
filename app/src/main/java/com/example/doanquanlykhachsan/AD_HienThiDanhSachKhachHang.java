package com.example.doanquanlykhachsan;

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

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Adapter_KhachHang;
import com.example.doanquanlykhachsan.model.KhachHang;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class AD_HienThiDanhSachKhachHang extends AppCompatActivity {
    EditText edTimKiem;
    ListView lvDSKH;
    Button btnTroVe;
    Adapter_KhachHang adapter_khachHang;
    ArrayList<KhachHang> arrKhachHang = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_hien_thi_danh_sach_khach_hang);
        setControl();
        setEvent();
    }

    private void setEvent() {
        // Lấy danh sách khách hàng từ firebase
        layDSKH();
        //xử lý nút tìm kiếm theo tên khách hàng
        edTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ArrayList<KhachHang> kq = new ArrayList<>();
                String key = edTimKiem.getText().toString().toLowerCase();
                for (int i = 0;i<arrKhachHang.size();i++)
                {
                    if(arrKhachHang.get(i).getTenKH().toLowerCase().contains(key)){
                        kq.add(arrKhachHang.get(i));
                    }
                }
                Adapter_KhachHang adapter_khachHang = new Adapter_KhachHang(getApplicationContext(),R.layout.custom_khach_hang, kq);
                lvDSKH.setAdapter(adapter_khachHang);
                adapter_khachHang.notifyDataSetChanged();
            }
        });
        //Xử lý nút trở về
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setControl() {
        edTimKiem = findViewById(R.id.edTimKiem);
        lvDSKH = findViewById(R.id.lvDSKH);
        btnTroVe = findViewById(R.id.btnTroVe);

        adapter_khachHang = new Adapter_KhachHang(getApplicationContext(), R.layout.custom_khach_hang, arrKhachHang);
        lvDSKH.setAdapter(adapter_khachHang);
    }

    private void layDSKH() {
        StaticConfig.mKhachHang.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                KhachHang kh = snapshot.getValue(KhachHang.class);
                arrKhachHang.add(kh);
                adapter_khachHang.notifyDataSetChanged();
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