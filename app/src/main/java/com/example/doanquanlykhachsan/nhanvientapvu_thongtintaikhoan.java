package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.example.doanquanlykhachsan.model.NhanVien_LichLamViec;
import com.example.doanquanlykhachsan.model.NhanVien_Luong;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class nhanvientapvu_thongtintaikhoan extends AppCompatActivity {
    EditText edtTenNV, edtCMND, edtSoDT;
    Button btnLuu, btnTroVe;
    ImageView ivChangeTen, ivChangeSDT, ivChangeCMND;
    TextView tvLuong, tvLichLamViec;
    String maNV;
    String tenNV;
    String SDT;
    String CMND;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvientapvu_thongtintaikhoan);
        setconTrol();
        setEvent();
        KhoiTao();
    }


    private void KhoiTao() {
        StaticConfig.mNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    NhanVien nv = ds.getValue(NhanVien.class);
                    if (nv.getSoDienThoai().toString().equals(StaticConfig.currentphone)) {

                        maNV = nv.getMaFB();
                        tenNV = nv.getTenNV();
                        SDT = nv.getSoDienThoai();
                        CMND = nv.getCmnd();
                        edtTenNV.setText(nv.getTenNV());
                        edtCMND.setText(nv.getCmnd());
                        edtSoDT.setText(nv.getSoDienThoai() + "");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setEvent() {

        edtTenNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTenNV.getText().toString().isEmpty() || edtSoDT.getText().toString().isEmpty() || edtCMND.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "không được bỏ trống", Toast.LENGTH_SHORT).show();
                    return;
                } else
                    StaticConfig.mNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                NhanVien_LichLamViec nv = ds.getValue(NhanVien_LichLamViec.class);
                                if (nv.getSoDienThoai().toString().equals(StaticConfig.currentphone)) {
                                    String ma = nv.getMaFB();
                                    StaticConfig.mNhanVien.child(ma).child("tenNV").setValue(edtTenNV.getText().toString());
                                    StaticConfig.mNhanVien.child(ma).child("cmnd").setValue(edtCMND.getText().toString());
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                StaticConfig.mNhanVien_Luong.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            NhanVien_LichLamViec nv = ds.getValue(NhanVien_LichLamViec.class);
                            if (nv.getSoDienThoai().toString().equals(StaticConfig.currentphone)) {
                                String ma = nv.getMaFB();
                                StaticConfig.mNhanVien_Luong.child(ma).child("tenNV").setValue(edtTenNV.getText().toString());
                                StaticConfig.mNhanVien_Luong.child(ma).child("cmnd").setValue(edtCMND.getText().toString());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                StaticConfig.mNhanVien_LichLamViec.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            NhanVien_LichLamViec nv = ds.getValue(NhanVien_LichLamViec.class);
                            if (nv.getSoDienThoai().toString().equals(StaticConfig.currentphone)) {
                                String ma = nv.getMaFB();
                                StaticConfig.mNhanVien_LichLamViec.child(ma).child("tenNV").setValue(edtTenNV.getText().toString());
                                StaticConfig.mNhanVien_LichLamViec.child(ma).child("cmnd").setValue(edtCMND.getText().toString());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), Nhanvientapvu_manhinhchinh.class));
            }
        });
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Nhanvientapvu_manhinhchinh.class));
            }
        });
        tvLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), nhanvientapvu_thongtinluong.class));
            }
        });
        tvLichLamViec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), nhanvientapvu_lichlamviec.class));
            }
        });
        ivChangeTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTenNV.setEnabled(true);
                edtTenNV.setText("");
                edtTenNV.requestFocus();
            }
        });
        ivChangeSDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //edtSoDT.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Chuyển màn hình đổi số điện thoại", Toast.LENGTH_SHORT).show();
            }
        });
        ivChangeCMND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtCMND.setEnabled(true);
                edtCMND.setText("");
                edtCMND.requestFocus();
            }
        });
    }

    private void setconTrol() {
        edtTenNV = findViewById(R.id.edtTenNV);
        edtSoDT = findViewById(R.id.edtSoDT);
        edtCMND = findViewById(R.id.edtCMND);
        btnLuu = findViewById(R.id.btnLuu);
        btnTroVe = findViewById(R.id.btnTroVe);
        ivChangeTen = findViewById(R.id.ivChangeTen);
        ivChangeSDT = findViewById(R.id.ivChangeSDT);
        ivChangeCMND = findViewById(R.id.ivChangeCMND);
        tvLuong = findViewById(R.id.tvLuong);
        tvLichLamViec = findViewById(R.id.tvLichLamViec);
    }
}

