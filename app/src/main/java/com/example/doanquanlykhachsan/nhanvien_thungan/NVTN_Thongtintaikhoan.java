package com.example.doanquanlykhachsan.nhanvien_thungan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.chung.update_phonenumber_1;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.example.doanquanlykhachsan.model.NhanVien_LichLamViec;
import com.example.doanquanlykhachsan.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class NVTN_Thongtintaikhoan extends AppCompatActivity {
    EditText edtTenNV, edtCMND, edtSoDT;
    Button btnLuu, btnTroVe;
    ImageView ivChangeTen, ivChangeSDT, ivChangeCMND;
    TextView tvLuong, tvLichLamViec;
    String maNV;
    String tenNV;
    String SDT;
    String CMND;
    boolean iscmnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_thongtintaikhoan);
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
        edtSoDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), update_phonenumber_1.class));
            }
        });
        edtCMND.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                StaticConfig.mUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            User user = ds.getValue(User.class);
                            if (edtCMND.getText().toString().equals(user.getCmnd().toString())) {
                                iscmnd = false;
                                break;
                            } else {
                                iscmnd = true;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTenNV.getText().toString().isEmpty() || edtCMND.getText().toString().isEmpty()) {
                    new androidx.appcompat.app.AlertDialog.Builder(NVTN_Thongtintaikhoan.this)
                            .setTitle("Th??ng tin t??i kho???n")
                            .setMessage("Vui L??ng Kh??ng ????? Tr???ng !!")
                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
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
                                    if (iscmnd == true) {
                                        StaticConfig.mNhanVien.child(ma).child("cmnd").setValue(edtCMND.getText().toString());
                                        StaticConfig.mUser.child(StaticConfig.currentuser).child("cmnd").setValue(edtCMND.getText().toString());
                                        new AlertDialog.Builder(NVTN_Thongtintaikhoan.this)
                                                .setTitle("Th??ng b??o ")
                                                .setMessage("C???p Nh???n Th??nh c??ng")
                                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                                // The dialog is automatically dismissed when a dialog button is clicked.
                                                .setIcon(android.R.drawable.ic_dialog_alert)
                                                .show();
                                    } else {
                                        new AlertDialog.Builder(NVTN_Thongtintaikhoan.this)
                                                .setTitle("Th??ng b??o ")
                                                .setMessage("CMND ???? c??")
                                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                                // The dialog is automatically dismissed when a dialog button is clicked.
                                                .setIcon(android.R.drawable.ic_dialog_alert)
                                                .show();
                                    }
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
                new androidx.appcompat.app.AlertDialog.Builder(NVTN_Thongtintaikhoan.this)
                        .setTitle("Th??ng tin t??i kho???n ")
                        .setMessage("L??u Th??nh c??ng")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                startActivity(new Intent(getApplicationContext(), NVTN_MenuNhanVienThuNgan.class));
            }
        });
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NVTN_Thongtinluong.class));
            }
        });
        tvLichLamViec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NVTN_lichlamviec.class));
            }
        });
        ivChangeTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTenNV.setEnabled(true);
                edtTenNV.requestFocus();
            }
        });
        ivChangeSDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),update_phonenumber_1.class));
            }
        });
        ivChangeCMND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtCMND.setEnabled(true);
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