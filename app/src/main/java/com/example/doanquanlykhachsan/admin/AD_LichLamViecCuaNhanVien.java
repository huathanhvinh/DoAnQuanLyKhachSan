package com.example.doanquanlykhachsan.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.example.doanquanlykhachsan.model.NhanVien_LichLamViec;
import com.example.doanquanlykhachsan.model.NhanVien_Luong;
import com.example.doanquanlykhachsan.nhanvien_letan.NVTN_ThemKhachHang;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class AD_LichLamViecCuaNhanVien extends AppCompatActivity {
    TextView tvManv,tvTennv,tvChucvu;
    Spinner spCalam;
    EditText edGhichu;
    Button btnLuu,btnTrove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_lich_lam_viec_cua_nhan_vien);
        setControl();
        setEvent();
    }

    private void setEvent() {
        //load dữ liệu
        layDSLLVNV();
        //xử lý nút trở về
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien_LichLamViec thongTinNhanVien = (NhanVien_LichLamViec) getIntent().getSerializableExtra("ThongTinLLVNhanVien");
                if (thongTinNhanVien.getCaLam().equals(spCalam.getSelectedItem().toString()) == false ||
                        thongTinNhanVien.getGhiChu().equals(edGhichu.getText().toString()) == false) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(AD_LichLamViecCuaNhanVien.this);
                    builder.setTitle("Sửa Nhân Viên");
                    builder.setMessage("Bạn chưa lưu, Bạn có muốn thoát không ?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                } else {
                    finish();
                }
            }
        });
        //xử lý nút Lưu
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien_LichLamViec thongTinNhanVien = (NhanVien_LichLamViec) getIntent().getSerializableExtra("ThongTinLLVNhanVien");
                thongTinNhanVien.setCaLam(spCalam.getSelectedItem().toString());
                thongTinNhanVien.setGhiChu(edGhichu.getText().toString());
                StaticConfig.mNhanVien_LichLamViec.child(thongTinNhanVien.getMaFB()).setValue(thongTinNhanVien);
                //sửa thông tin lại bên Nhân Viên
                StaticConfig.mNhanVien.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds: snapshot.getChildren())
                        {
                            if (ds.child("soDienThoai").getValue().toString().equals(thongTinNhanVien.getSoDienThoai()))
                            {
                                NhanVien nv = ds.getValue(NhanVien.class);
                                nv.setCaLam(spCalam.getSelectedItem().toString());
                                StaticConfig.mNhanVien.child(nv.getMaFB()).setValue(nv);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                //Sửa thông tin lại bên Nhan Viên - Lương
                StaticConfig.mNhanVien_Luong.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds: snapshot.getChildren())
                        {
                            if (ds.child("soDienThoai").getValue().toString().equals(thongTinNhanVien.getSoDienThoai()))
                            {
                                NhanVien_Luong nv = ds.getValue(NhanVien_Luong.class);
                                nv.setCaLam(spCalam.getSelectedItem().toString());
                                StaticConfig.mNhanVien_Luong.child(nv.getMaFB()).setValue(nv);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                //Thông Báo
                new AlertDialog.Builder(AD_LichLamViecCuaNhanVien.this)
                        .setTitle("Lịch làm việc")
                        .setMessage("lưu thành công")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    private void layDSLLVNV() {
        NhanVien_LichLamViec thongTinNhanVien = (NhanVien_LichLamViec) getIntent().getSerializableExtra("ThongTinLLVNhanVien");
        //Toast.makeText(getApplicationContext(), thongTinNhanVienDaSua.toString(), Toast.LENGTH_SHORT).show();
        tvManv.setText("NV100" + thongTinNhanVien.getStt());
        tvTennv.setText(thongTinNhanVien.getTenNV());
        tvChucvu.setText(thongTinNhanVien.getChucVu());
        if (thongTinNhanVien.getCaLam().equals("Sáng"))
            spCalam.setSelection(0);
        if (thongTinNhanVien.getCaLam().equals("Trưa"))
            spCalam.setSelection(1);
        if (thongTinNhanVien.getCaLam().equals("Chiều"))
            spCalam.setSelection(2);
        if (thongTinNhanVien.getCaLam().equals("Tối"))
            spCalam.setSelection(3);
        edGhichu.setText(thongTinNhanVien.getGhiChu());
    }

    private void setControl() {
        tvManv = findViewById(R.id.tvManvLV);
        tvTennv = findViewById(R.id.tvTennvLV);
        tvChucvu = findViewById(R.id.tvChucvuLV);
        spCalam = findViewById(R.id.spLichLamViecLV);
        edGhichu = findViewById(R.id.edGhiChuLV);
        btnLuu = findViewById(R.id.btnLuuLV);
        btnTrove = findViewById(R.id.btnTroVe);
    }
}