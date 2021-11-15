package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Adapter_NhanVien;
import com.example.doanquanlykhachsan.model.Adapter_Phong;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.example.doanquanlykhachsan.model.Phong;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class AD_HienThiDanhSachPhong extends AppCompatActivity {
    Button btnTroVe,btnTaoMoi;
    ListView lvPhong;
    EditText edTimKiem;

    ImageView imRefresh;

    Adapter_Phong adapter_phong;
    ArrayList<Phong> arrPhong = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_hien_thi_danh_sach_phong);
        setControl();
        setEvent();
    }

    private void setControl() {
        imRefresh = findViewById(R.id.imRefresh);
        btnTroVe = findViewById(R.id.btnTroVe);
        btnTaoMoi = findViewById(R.id.btnTaoMoiPhong);
        lvPhong = findViewById(R.id.lvDSP);
        edTimKiem = findViewById(R.id.edTimKiemPhong);
        adapter_phong = new Adapter_Phong(getApplicationContext(),R.layout.custom_phong,arrPhong);
        lvPhong.setAdapter(adapter_phong);
    }

    private void setEvent() {
        //refresh data
        imRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrPhong.clear();
                layDanhSachPhong();
            }
        });
        //xử lý nút tìm kiếm dựa trên mã phòng
        edTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ArrayList<Phong> p = new ArrayList<>();
                String key = edTimKiem.getText().toString().toLowerCase();
                for (int i = 0;i<arrPhong.size();i++)
                {
                    if(arrPhong.get(i).getMaPhong().toLowerCase().contains(key)){
                        p.add(arrPhong.get(i));
                    }
                }
                Adapter_Phong adapter_phong = new Adapter_Phong(getApplicationContext(),R.layout.custom_phong, p);
                lvPhong.setAdapter(adapter_phong);
                adapter_phong.notifyDataSetChanged();
            }
        });
        //Xử lý nút thêm Phòng
        btnTaoMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AD_ThemPhong.class));
            }
        });
        //lấy danh sách các phòng
        layDanhSachPhong();
        //xử lý nút trở về
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    // Lấy ds phòng
    private void layDanhSachPhong() {
        StaticConfig.mPhong.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Phong p = snapshot.getValue(Phong.class);
                arrPhong.add(p);
                adapter_phong.notifyDataSetChanged();
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