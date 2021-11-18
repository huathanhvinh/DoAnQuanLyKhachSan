package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Adapter_DichVu;
import com.example.doanquanlykhachsan.model.DichVu;
import com.example.doanquanlykhachsan.model.KhachHang;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class AD_HienThiDanhSachDichVu extends AppCompatActivity {
    Button btnTroVe, btnTaoMoi;
    EditText edTimKiem;
    ListView lvDSDV;
    ImageView imRF;

    Adapter_DichVu adapter_dichVu;
    ArrayList<DichVu> arrDichVu = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_hien_thi_danh_sach_dich_vu);
        setControl();
        setEvent();
    }

    private void setEvent() {
        //hiển thị danh sách dịch vụ từ firebase
        setDanhSachDichVu();
        //sự kiện nút trở về
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
                arrDichVu.clear();
                setDanhSachDichVu();
            }
        });
    }

    private void setControl() {
        btnTroVe = findViewById(R.id.btnTroVe);
        btnTaoMoi = findViewById(R.id.btnTaoMoi);
        edTimKiem = findViewById(R.id.edTimDichVu);
        lvDSDV = findViewById(R.id.lvDSDV);
        imRF = findViewById(R.id.imRefresh);

        adapter_dichVu = new Adapter_DichVu(getApplicationContext(), R.layout.custom_dich_vu, arrDichVu);
        lvDSDV.setAdapter(adapter_dichVu);
    }

    private void setDanhSachDichVu()
    {
        StaticConfig.mDichVu.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DichVu dv = snapshot.getValue(DichVu.class);
                arrDichVu.add(dv);
                adapter_dichVu.notifyDataSetChanged();
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