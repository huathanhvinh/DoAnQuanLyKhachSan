package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Adapter_KhuyenMai;
import com.example.doanquanlykhachsan.model.KhuyenMai;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class AD_HienThiDanhSachKhuyenMai extends AppCompatActivity {
    Button btnThemKM, btnTroVe;
    RadioButton rdSapDienRa, rdDangDienRa, rdDaKetThuc;
    ListView lvDSKM;

    Adapter_KhuyenMai adapter_khuyenMai;
    ArrayList<KhuyenMai> arrKhuyenMai = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_hien_thi_danh_sach_khuyen_mai);
        setControl();
        setEvent();

    }

    private void setEvent() {
        //lấy danh sách các thông tin khuyến mãi
        layDanhSachKhuyenMai();
        //lấy danh sách các thông tin Đang diễn ra
        rdDangDienRa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdDangDienRa.isChecked()) {
                    adapter_khuyenMai.clear();
                    layDanhSachKhuyenMaiDangDienRa();
                }
            }
        });

        //lấy danh sách các thông tin Sắp diễn ra
        rdSapDienRa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdSapDienRa.isChecked())
                    adapter_khuyenMai.clear();
                layDanhSachKhuyenMaiSapDienRa();
            }
        });
        //lấy danh sách các thông tin Đã Kết thúc
        rdDaKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdDaKetThuc.isChecked())
                    adapter_khuyenMai.clear();
                layDanhSachKhuyenMaiDaKetThuc();
            }
        });
        //xử lý nút trở về
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void layDanhSachKhuyenMaiDaKetThuc() {
        StaticConfig.mKhuyenMai.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.child("trangThai").getValue().toString().equals("Đã Kết Thúc")) {
                    KhuyenMai khuyenMai = snapshot.getValue(KhuyenMai.class);
                    arrKhuyenMai.add(khuyenMai);
                    adapter_khuyenMai.notifyDataSetChanged();
                }
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

    private void layDanhSachKhuyenMaiSapDienRa() {
        StaticConfig.mKhuyenMai.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.child("trangThai").getValue().toString().equals("Sắp Diễn Ra")) {
                    KhuyenMai khuyenMai = snapshot.getValue(KhuyenMai.class);
                    arrKhuyenMai.add(khuyenMai);
                    adapter_khuyenMai.notifyDataSetChanged();
                }
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

    private void layDanhSachKhuyenMaiDangDienRa() {
        StaticConfig.mKhuyenMai.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.child("trangThai").getValue().toString().equals("Đang Diễn Ra")) {
                    KhuyenMai khuyenMai = snapshot.getValue(KhuyenMai.class);
                    arrKhuyenMai.add(khuyenMai);
                    adapter_khuyenMai.notifyDataSetChanged();
                }
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

    private void layDanhSachKhuyenMai() {
        StaticConfig.mKhuyenMai.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                KhuyenMai khuyenMai = snapshot.getValue(KhuyenMai.class);
                arrKhuyenMai.add(khuyenMai);
                adapter_khuyenMai.notifyDataSetChanged();
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

    private void setControl() {
        btnThemKM = findViewById(R.id.btnTaoKhuyenMai);
        btnTroVe = findViewById(R.id.btnTroVe);
        rdDangDienRa = findViewById(R.id.rdDangDienRa);
        rdSapDienRa = findViewById(R.id.rdSapDienRa);
        rdDaKetThuc = findViewById(R.id.rdDaKetThuc);
        lvDSKM = findViewById(R.id.lvDSKM);

        adapter_khuyenMai = new Adapter_KhuyenMai(getApplicationContext(), R.layout.custom_khuyen_mai, arrKhuyenMai);
        lvDSKM.setAdapter(adapter_khuyenMai);
    }
}