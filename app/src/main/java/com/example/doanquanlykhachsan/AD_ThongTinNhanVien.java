package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.model.NhanVien;

public class AD_ThongTinNhanVien extends AppCompatActivity {
    TextView tvMa,tvTen,tvNgaySinh,tvQueQuan,tvCMND,tvLuong,tvChucVu;
    Button btnTrove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_thong_tin_nhan_vien);
        setControl();
        setEvent();
    }
    private void setControl() {
        tvMa = findViewById(R.id.tvManv);
        tvTen = findViewById(R.id.tvTennv);
        tvNgaySinh = findViewById(R.id.tvNgaysinh);
        tvQueQuan = findViewById(R.id.tvQueQuan);
        tvCMND = findViewById(R.id.tvCMNN);
        tvLuong = findViewById(R.id.tvLuongNV);
        tvChucVu = findViewById(R.id.tvChucvu);
        btnTrove = findViewById(R.id.btnTroVe);
    }

    private void setEvent() {
        //
        setThongTinNhanVien();
        //
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void setThongTinNhanVien()
    {
        NhanVien thongTinNhanVien = (NhanVien) getIntent().getSerializableExtra("ThongTinNhanVien");
        tvMa.setText("100"+thongTinNhanVien.getStt());
        tvTen.setText(thongTinNhanVien.getTenNV());
        tvNgaySinh.setText(thongTinNhanVien.getNgaySinh());
        tvQueQuan.setText(thongTinNhanVien.getDiaChi());
        tvCMND.setText(thongTinNhanVien.getCmnd());
        tvLuong.setText(thongTinNhanVien.getLuong()+" VNĐ");
        tvChucVu.setText(thongTinNhanVien.getChucVu());
    }
}