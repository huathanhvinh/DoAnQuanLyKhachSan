package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.KhachHang;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.example.doanquanlykhachsan.model.NhanVien_Luong;

public class AD_LuongCuaNhanVien extends AppCompatActivity {
    TextView tvMaNV,tvTennv,tvChucVu;
    EditText edLuong,edThuong,edGhichu;
    Button btnLuu,btnTroVe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_luong_cua_nhan_vien);
        setControl();
        setEvent();
    }

    private void setEvent() {
        //lấy thông tin nhân viên_lương
        layThongTinNhanVien();
        //xử lý edittext Luong
        edLuong.setFocusable(false);
        //xử lý nút lưu
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien_Luong thongTinNhanVien = (NhanVien_Luong) getIntent().getSerializableExtra("ThongTinLuongNhanVien");
                thongTinNhanVien.setTienThuong(edThuong.getText().toString());
                thongTinNhanVien.setGhiChu(edGhichu.getText().toString());
                StaticConfig.mNhanVien_Luong.child(thongTinNhanVien.getMaFB()).setValue(thongTinNhanVien);
                Toast.makeText(getApplicationContext(), "Lưu Thành Công !", Toast.LENGTH_SHORT).show();
            }
        });
        //xử lý nút trở về
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien_Luong thongTinNhanVien = (NhanVien_Luong) getIntent().getSerializableExtra("ThongTinLuongNhanVien");
                if (thongTinNhanVien.getTienThuong().equals(edThuong.getText().toString()) == false ||
                        thongTinNhanVien.getGhiChu().equals(edGhichu.getText().toString()) == false) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(AD_LuongCuaNhanVien.this);
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
    }

    private void layThongTinNhanVien() {
        NhanVien_Luong thongTinNhanVien = (NhanVien_Luong) getIntent().getSerializableExtra("ThongTinLuongNhanVien");
        //Toast.makeText(getApplicationContext(), thongTinNhanVienDaSua.toString(), Toast.LENGTH_SHORT).show();
        tvMaNV.setText("NV100" + thongTinNhanVien.getStt());
        tvTennv.setText(thongTinNhanVien.getTenNV());
        tvChucVu.setText(thongTinNhanVien.getChucVu());
        edLuong.setText(thongTinNhanVien.getLuong());
        edThuong.setText(thongTinNhanVien.getTienThuong());
        edGhichu.setText(thongTinNhanVien.getGhiChu());
    }

    private void setControl() {
        tvMaNV = findViewById(R.id.tvMaNV_L);
        tvTennv = findViewById(R.id.tvTenNV_l);
        tvChucVu = findViewById(R.id.tvChucvu_l);
        edLuong = findViewById(R.id.edLuongNV);
        edThuong = findViewById(R.id.edThuongNV);
        edGhichu = findViewById(R.id.edGhiChu);
        btnLuu = findViewById(R.id.btnLuuLuong);
        btnTroVe = findViewById(R.id.btnTroVe);


    }
}