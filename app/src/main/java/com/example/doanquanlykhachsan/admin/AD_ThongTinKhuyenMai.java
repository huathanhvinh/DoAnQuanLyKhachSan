package com.example.doanquanlykhachsan.admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.KhuyenMai;

public class AD_ThongTinKhuyenMai extends AppCompatActivity {
    TextView tvMaKM, tvSizeTen, tvNgayBatDau, tvNgayKetThuc;
    ImageButton imNgayBatDau, imNgayKetThuc;
    EditText edTenKm, edMucGiam, edNoiDung;
    Button btnSua, btnTrove,btnXoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_thong_tin_khuyen_mai);
        setControl();
        setEvent();
    }
    private void setEvent()
    {
        //lấy thông tin khuyến mãi từ listview
        layThongTinKhuyenMai();
        //tắt các dòng, không cho phép người dùng nhập, chỉ được xem
        edTenKm.setFocusable(false);
        edMucGiam.setFocusable(false);
        edNoiDung.setFocusable(false);
        //xử lý nút trở về
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //xử lý nút xóa
        xoaThongTinKhuyenMai();
        //xử lý nút sửa Khuyến mãi
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KhuyenMai khuyenMai = (KhuyenMai) getIntent().getSerializableExtra("ThongTinKhuyenMai");
                Intent intent = new Intent(getApplicationContext(),AD_SuaKhuyenMai.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ThongTinKhuyenMai",khuyenMai);
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });

    }

    private void xoaThongTinKhuyenMai() {
        KhuyenMai khuyenMai = (KhuyenMai) getIntent().getSerializableExtra("ThongTinKhuyenMai");
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AD_ThongTinKhuyenMai.this);
                builder.setTitle("Xóa Khuyến Mãi");
                builder.setMessage("Bạn có muốn xóa không ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StaticConfig.mKhuyenMai.child(khuyenMai.getMaFB()).removeValue();
                        Toast.makeText(getApplicationContext(), "Xóa Thành Công", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

    }

    private void layThongTinKhuyenMai()
    {
        KhuyenMai khuyenMai = (KhuyenMai) getIntent().getSerializableExtra("ThongTinKhuyenMai");
        tvMaKM.setText(khuyenMai.getMaKM());
        edTenKm.setText(khuyenMai.getTenKM());
        int size = khuyenMai.getTenKM().length();
        tvSizeTen.setText(size + "/50");
        tvNgayBatDau.setText(khuyenMai.getNgayBatDau());
        tvNgayKetThuc.setText(khuyenMai.getNgayKetThuc());
        edMucGiam.setText(khuyenMai.getMucGiamGia() + "");
        edNoiDung.setText(khuyenMai.getNoiDung());
    }

    private void setControl()
    {
        tvMaKM = findViewById(R.id.tvMakhuyenMaiT);
        tvSizeTen = findViewById(R.id.tvSizeTenKMT);
        tvNgayBatDau = findViewById(R.id.tvNgayBatDauT);
        tvNgayKetThuc = findViewById(R.id.tvNgayKetThucT);
        edTenKm = findViewById(R.id.edTenKhuyenMaiT);
        edMucGiam = findViewById(R.id.edGiamGiaT);
        edNoiDung = findViewById(R.id.edNoiDungChuongTrinhT);
        btnSua = findViewById(R.id.btnSuaT);
        btnTrove = findViewById(R.id.btnTroVe);
        btnXoa = findViewById(R.id.btnXoaT);
        imNgayBatDau = findViewById(R.id.imNgayBatDauT);
        imNgayKetThuc = findViewById(R.id.imNgayKetThucT);
    }
}