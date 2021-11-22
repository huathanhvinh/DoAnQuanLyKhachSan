package com.example.doanquanlykhachsan.admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.DichVu;

public class AD_ThongTinDichVu extends AppCompatActivity {
    TextView tvMaDV,tvTenDV,tvGia,tvDVT,tvMota;
    ImageButton imSuaDV,imXoaDV;
    Button btnTrove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_thong_tin_dich_vu);
        setControl();
        setEvent();
    }

    private void setEvent() {
        //load thông tin dịch vụ từ danh sách dịch vụ
        layThongTinDichVu();
        //Sử lý nút trở về
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //xử lý nút xóa dịch vụ
        imXoaDV.setOnClickListener(new View.OnClickListener() {
            DichVu thongTinDichVu = (DichVu) getIntent().getSerializableExtra("ThongTinDichVu");
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AD_ThongTinDichVu.this);
                builder.setTitle("Xóa Dịch Vụ");
                builder.setMessage("Bạn có muốn xóa không ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StaticConfig.mDichVu.child(thongTinDichVu.getMaFB()).removeValue();
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
        //xử lý nút Sửa dịch vụ
        imSuaDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DichVu thongTinDichVu = (DichVu) getIntent().getSerializableExtra("ThongTinDichVu");
                Intent intent = new Intent(getApplicationContext(),AD_SuaDichVu.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ThongTinDichVu",thongTinDichVu);
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
    }

    private void layThongTinDichVu() {
        DichVu thongTinDichVu = (DichVu) getIntent().getSerializableExtra("ThongTinDichVu");
        tvMaDV.setText("DV100"+thongTinDichVu.getStt());
        tvTenDV.setText(thongTinDichVu.getTenDV());
        tvGia.setText(thongTinDichVu.getGiaDV()+""+" VNĐ");
        tvDVT.setText(thongTinDichVu.getDvt());
        tvMota.setText(thongTinDichVu.getMota());

    }

    private void setControl() {
        tvMaDV = findViewById(R.id.tvMaDV);
        tvTenDV = findViewById(R.id.tvTenDV);
        tvGia = findViewById(R.id.tvGiaDV);
        tvDVT = findViewById(R.id.tvDVT);
        tvMota = findViewById(R.id.tvMoTa);
        imSuaDV = findViewById(R.id.imSuaDichVu);
        imXoaDV = findViewById(R.id.imXoaDichVu);
        btnTrove = findViewById(R.id.btnTroVe);
    }
}