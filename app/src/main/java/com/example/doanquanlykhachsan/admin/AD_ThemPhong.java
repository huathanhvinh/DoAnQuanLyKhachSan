package com.example.doanquanlykhachsan.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Phong;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class AD_ThemPhong extends AppCompatActivity {
    Spinner spSoPhong, spLau, spLoaiPhong;
    TextView tvTenPhong, tvMaPhong;
    EditText edGiaGio, edGiaNgay, edMoTa;
    Button btnTaoPhong, btnTroVe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_them_phong);
        setControl();
        setEvent();
    }

    private void setEvent() {
        //xử lý Tên phòng + mã phòng, tự động cập nhật
        layMaVaTenPhong();
        //xử lý Tên phòng + mã phòng, tự động cập nhật
        spSoPhong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                layMaVaTenPhong();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //xử lý Tên phòng + mã phòng, tự động cập nhật
        spLau.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                layMaVaTenPhong();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //xử lý nút thêm phòng
        btnTaoPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edGiaGio.getText().toString().equals("")) {
                    edGiaGio.setError("Giá giờ không được bỏ trống");
                } else if (edGiaNgay.getText().toString().equals("")) {
                    edGiaNgay.setError("Giá ngày không được bỏ trống");
                } else if (edMoTa.getText().toString().equals("")) {
                    edMoTa.setError("Mô tả không được bỏ trốn");
                } else {
                    StaticConfig.mPhong.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int check = 0;
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                if (ds.child("maPhong").getValue().toString().equals(tvMaPhong.getText().toString())) {
                                    check = 1;
                                }
                            }
                            if (check != 0) {
                                new AlertDialog.Builder(AD_ThemPhong.this)
                                        .setTitle("Thêm Phòng")
                                        .setMessage("Hiện đã có dịch vụ này trong danh sách")
                                        // Specifying a listener allows you to take an action before dismissing the dialog.
                                        // The dialog is automatically dismissed when a dialog button is clicked.
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
                            } else {
                                int soLau = spLau.getSelectedItemPosition();
                                soLau++;

                                int soPhong = spSoPhong.getSelectedItemPosition();
                                soPhong++;

                                int giaGio = Integer.parseInt(edGiaGio.getText().toString());
                                int giaNgay = Integer.parseInt(edGiaNgay.getText().toString());

                                String key = StaticConfig.mPhong.push().getKey();
                                Phong p = new Phong(key, tvMaPhong.getText().toString(), tvTenPhong.getText().toString(), soPhong + "", soLau,spLoaiPhong.getSelectedItem().toString(),
                                        edMoTa.getText().toString(),giaNgay, giaGio,"Trống");
                                StaticConfig.mPhong.child(key).setValue(p);
                                new AlertDialog.Builder(AD_ThemPhong.this)
                                        .setTitle("Thêm Phòng")
                                        .setMessage("Thêm Thành Công !!!")
                                        // Specifying a listener allows you to take an action before dismissing the dialog.
                                        // The dialog is automatically dismissed when a dialog button is clicked.
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();


                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
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

    private void layMaVaTenPhong() {
        String soPhong = spSoPhong.getSelectedItem().toString();
        String lau = spLau.getSelectedItem().toString();

        int soPhong1 = spSoPhong.getSelectedItemPosition();
        int lau1 = spLau.getSelectedItemPosition();

        soPhong1++;
        lau1++;

        tvTenPhong.setText(soPhong + "_" + lau);
        tvMaPhong.setText("p" + soPhong1 + "t" + lau1);
    }

    private void setControl() {
        spSoPhong = findViewById(R.id.spSoPhong);
        spLau = findViewById(R.id.spLau);
        spLoaiPhong = findViewById(R.id.spLoaiPhong);

        tvTenPhong = findViewById(R.id.tvTenPhongT);
        tvMaPhong = findViewById(R.id.tvMaPhongT);

        edGiaGio = findViewById(R.id.edGiaGio);
        edGiaNgay = findViewById(R.id.edGiaNgay);
        edMoTa = findViewById(R.id.edMota);

        btnTaoPhong = findViewById(R.id.btnThemPhongT);
        btnTroVe = findViewById(R.id.btnTroVe);


    }
}