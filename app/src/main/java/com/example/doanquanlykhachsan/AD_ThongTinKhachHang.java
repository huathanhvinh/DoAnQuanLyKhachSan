package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.doanquanlykhachsan.model.KhachHang;
import com.example.doanquanlykhachsan.model.NhanVien;

public class AD_ThongTinKhachHang extends AppCompatActivity {
    TextView tvMaKh,tvtenKH,tvSDTKH,tvDiaChi,tvCMND;
    ImageButton imChuyen;
    Button btnTrove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_thong_tin_khach_hang);
        setControl();
        setEvent();
    }

    private void setEvent() {
        //lấy thông tin khách hàng
        setThongTinKhachHang();
        //xử lý nút trở về
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setControl() {
        tvMaKh = findViewById(R.id.tvMaKH);
        tvtenKH = findViewById(R.id.tvtenKh);
        tvSDTKH = findViewById(R.id.tvSDTKH);
        tvDiaChi = findViewById(R.id.tvDiaChiKH);
        tvCMND = findViewById(R.id.tvCMNDKH);
        imChuyen = findViewById(R.id.imChuyen);
        btnTrove = findViewById(R.id.btnTroVe);
    }

    private void setThongTinKhachHang()
    {
        KhachHang thongTinKhachHang = (KhachHang) getIntent().getSerializableExtra("ThongTinKhachHang");
        //Toast.makeText(getApplicationContext(), thongTinNhanVienDaSua.toString(), Toast.LENGTH_SHORT).show();
        tvMaKh.setText("KH100" + thongTinKhachHang.getStt());
        tvtenKH.setText(thongTinKhachHang.getTenKH());
        tvSDTKH.setText(thongTinKhachHang.getSdtKH());
        tvDiaChi.setText(thongTinKhachHang.getDiaChi());
        tvCMND.setText(thongTinKhachHang.getCmnd());
    }
}