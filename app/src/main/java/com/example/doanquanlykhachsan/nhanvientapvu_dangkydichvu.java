package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
    ArrayList<DangKyDichVu> timkiemphong = new ArrayList<>();
    Button btnTroVe;
    EditText etTimKiem;

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

        etTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String timkiem = etTimKiem.getText().toString().toLowerCase();
                timkiemphong.clear();
                for (int i = 0; i < data.size(); i++) {
                    DangKyDichVu dv = data.get(i);
                    if (dv.getTenDichVu().toLowerCase().contains(timkiem)) {
                        timkiemphong.add(dv);
                    }
                }

                custom_nhanvientapvu_dangkydichvu dangkydichvu = new custom_nhanvientapvu_dangkydichvu(getApplicationContext(), R.layout.listview_nhanvientapvu_dangkydichvu, timkiemphong);
                lvDanhSachDV.setAdapter(dangkydichvu);
                dangkydichvu.notifyDataSetChanged();
                if (timkiem.isEmpty()) {
                   khoitao();
                }


//                StaticConfig.mDichVu.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        timkiemphong.clear();
//                        for (DataSnapshot ds : snapshot.getChildren()) {
//                            DichVu dv = ds.getValue(DichVu.class);
////                            if (dv.getTenDichVu().toLowerCase().contains(timkiem)) {
////
////                                timkiemphong.add(dv);
////
////                            }
//                            Log.d("test", dv.getTenDV());
//                        }
//                        if (timkiem.isEmpty()) {
//                            timkiemphong = data;
//                        }
//                        custom_nhanvientapvu_dangkydichvu dangkydichvu = new custom_nhanvientapvu_dangkydichvu(getApplicationContext(), R.layout.listview_nhanvientapvu_dangkydichvu, timkiemphong);
//                        lvDanhSachDV.setAdapter(dangkydichvu);
//                        dangkydichvu.notifyDataSetChanged();
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
            }
        });
//        custom_nhanvientapvu_dangkydichvu dangkydichvu = new custom_nhanvientapvu_dangkydichvu(getApplicationContext(), R.layout.listview_nhanvientapvu_dangkydichvu, data);
//        lvDanhSachDV.setAdapter(dangkydichvu);
//        dangkydichvu.notifyDataSetChanged();
        khoitao();

    }


    private void setControl() {
        btnTroVe = findViewById(R.id.btnTroVe);
        lvDanhSachDV = findViewById(R.id.lvDanhSachDV);
        etTimKiem = findViewById(R.id.etTimKiem);
    }

    //lấy thông tin các dịch vụ từ firebase
    private void khoitao() {

        StaticConfig.mDichVu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    data.add(new DangKyDichVu(ds.child("tenDV").getValue(String.class), "Xem phòng sử dụng dịch vụ"));
                }
                custom_nhanvientapvu_dangkydichvu dangkydichvu = new custom_nhanvientapvu_dangkydichvu(getApplicationContext(), R.layout.listview_nhanvientapvu_dangkydichvu, data);
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