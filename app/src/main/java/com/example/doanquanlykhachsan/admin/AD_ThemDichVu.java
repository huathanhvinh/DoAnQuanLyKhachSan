package com.example.doanquanlykhachsan.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.DichVu;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class AD_ThemDichVu extends AppCompatActivity {
    TextView tvMadichvu;
    Spinner spTendichvu, spDonViTinh;
    EditText edgiaDichVu, edMota;
    Button btnThem, btnTroVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_them_dich_vu);
        setControl();
        setEvent();
    }

    private void setEvent() {
        //tự động cập nhật mã
        TuDongCapNhatMaDV();
        //Thêm dịch vụ
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edgiaDichVu.getText().toString().equals(""))
                    edgiaDichVu.setError("Không được để trống giá dịch vụ");

                else if (edMota.getText().toString().equals(""))
                    edMota.setError("Không được để trống mô tả");

                else {
                    StaticConfig.mDichVu.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean check = false;
                            int stt = 0;
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                stt = Integer.parseInt(ds.child("stt").getValue().toString());
                                if (ds.child("tenDV").getValue().toString().equals(spTendichvu.getSelectedItem().toString())) {
                                    check = true;
                                }
                            }
                            if (check == true)
                                new AlertDialog.Builder(AD_ThemDichVu.this)
                                        .setTitle("Thêm Dịch vụ")
                                        .setMessage("Hiện đã có dịch vụ này trong danh sách")
                                        // Specifying a listener allows you to take an action before dismissing the dialog.
                                        // The dialog is automatically dismissed when a dialog button is clicked.
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();

                            else {
                                int giaDichVu = Integer.parseInt(edgiaDichVu.getText().toString());
                                String key = StaticConfig.mDichVu.push().getKey();
                                DichVu dv = new DichVu(stt, key, spTendichvu.getSelectedItem().toString(), giaDichVu, spDonViTinh.getSelectedItem().toString(), 0, edMota.getText().toString());
                                StaticConfig.mDichVu.child(key).setValue(dv);
                                new AlertDialog.Builder(AD_ThemDichVu.this)
                                        .setTitle("Thêm Dịch vụ")
                                        .setMessage("Lưu thành công")
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
        //nút trở về
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void TuDongCapNhatMaDV() {
        StaticConfig.mDichVu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int stt = 0;
                boolean check = false;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    stt = Integer.parseInt(ds.child("stt").getValue().toString());
                    if (ds.child("tenDV").getValue().toString().equals(spTendichvu.getSelectedItem().toString())) {
                        check = true;
                    }
                }
                stt++;
                tvMadichvu.setText("DV100" + stt);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setControl() {
        tvMadichvu = findViewById(R.id.tvMaDichVuT);
        spTendichvu = findViewById(R.id.spTenDichVuT);
        spDonViTinh = findViewById(R.id.spDonViTinhT);
        edgiaDichVu = findViewById(R.id.edGiaDichVuT);
        edMota = findViewById(R.id.edMotaT);
        btnThem = findViewById(R.id.btnThemDichVu);
        btnTroVe = findViewById(R.id.btnTroVe);
    }
}