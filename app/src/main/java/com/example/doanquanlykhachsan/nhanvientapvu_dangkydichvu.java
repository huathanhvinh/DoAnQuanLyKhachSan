package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.DangKyDichVu;
import com.example.doanquanlykhachsan.model.DichVu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class nhanvientapvu_dangkydichvu extends AppCompatActivity {
    ListView lvDanhSachDV;
    ArrayList<DangKyDichVu> data = new ArrayList<>();
    Button btnTroVe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvientapvu_dangkydichvu);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        custom_nhanvientapvu_dangkydichvu dangkydichvu = new custom_nhanvientapvu_dangkydichvu(getApplicationContext(),R.layout.listview_nhanvientapvu_dangkydichvu,data);
        lvDanhSachDV.setAdapter(dangkydichvu);
        dangkydichvu.notifyDataSetChanged();
        khoitao();
    }


    private void setControl() {
        btnTroVe = findViewById(R.id.btnTroVe);
        lvDanhSachDV = findViewById(R.id.lvDanhSachDV);
    }

    //lấy thông tin các dịch vụ từ firebase
    private void khoitao() {
//        DangKyDichVu dv = new DangKyDichVu("Dọn dẹp quần áo","Xem các phòng sử dụng dịch vụ");
//        data.add(dv);
//        DangKyDichVu dv1 = new DangKyDichVu("Ráy tai","Xem các phòng sử dụng dịch vụ");
//        data.add(dv1);
        StaticConfig.mDichVu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    data.add(new DangKyDichVu(ds.child("tenDV").getValue(String.class),"Xem phòng sử dụng dịch vụ"));
                }
                custom_nhanvientapvu_dangkydichvu dangkydichvu = new custom_nhanvientapvu_dangkydichvu(getApplicationContext(),R.layout.listview_nhanvientapvu_dangkydichvu,data);
                lvDanhSachDV.setAdapter(dangkydichvu);
                dangkydichvu.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });
    }
}