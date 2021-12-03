package com.example.doanquanlykhachsan.admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Phong;

import java.text.DecimalFormat;

public class AD_ThongTinPhong extends AppCompatActivity {
    Button btnSua, btnXoa, btnTroVe;
    TextView tvTenPhong, tvMaPhong;
    Spinner spSoPhong, spLau, spLoaiPhong,spTrangThai;
    EditText edGiaGio, edGiaNgay, edMoTa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_thong_tin_phong);
        setControl();
        setEvent();
    }

    private void setEvent() {
        //lấy thông tin phòng
        setThongTinPhong();
        //nút trở về
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //nút sửa
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Phong thongTinPhong = (Phong) getIntent().getSerializableExtra("ThongTinPhong");
                Intent intent = new Intent(getApplicationContext(), AD_SuaPhong.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ThongTinPhong",thongTinPhong);
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
        //nút xóa
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AD_ThongTinPhong.this);
                builder.setTitle("Xóa Khuyến Mãi");
                builder.setMessage("Bạn có muốn xóa không ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Phong thongTinPhong = (Phong) getIntent().getSerializableExtra("ThongTinPhong");
                        StaticConfig.mPhong.child(thongTinPhong.getMaFB()).removeValue();
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
    //lấy thông tin phòng
    private void setThongTinPhong() {
        Phong thongTinPhong = (Phong) getIntent().getSerializableExtra("ThongTinPhong");
        String lau = thongTinPhong.getLau()+"";
        //
        if(thongTinPhong.getSoPhong().equals("1"))
            spSoPhong.setSelection(0);
        else if(thongTinPhong.getSoPhong().equals("2"))
            spSoPhong.setSelection(1);
        else if(thongTinPhong.getSoPhong().equals("3"))
            spSoPhong.setSelection(2);
        else if(thongTinPhong.getSoPhong().equals("4"))
            spSoPhong.setSelection(3);
        else if(thongTinPhong.getSoPhong().equals("5"))
            spSoPhong.setSelection(4);
        else if(thongTinPhong.getSoPhong().equals("6"))
            spSoPhong.setSelection(5);
        else if(thongTinPhong.getSoPhong().equals("7"))
            spSoPhong.setSelection(6);
        else if(thongTinPhong.getSoPhong().equals("8"))
            spSoPhong.setSelection(7);
        else if(thongTinPhong.getSoPhong().equals("9"))
            spSoPhong.setSelection(8);
        else
            spSoPhong.setSelection(9);
        //trạng thái
//        <item>Trống</item>
//        <item>Đã đặt phòng</item>
//        <item>Chưa xử lý</item>
//        <item>Trả phòng</item>
//        <item>Bảo trì</item>
        if(thongTinPhong.getTrangThai().equals("Trống"))
            spTrangThai.setSelection(0);
        else if(thongTinPhong.getTrangThai().equals("Đã đặt phòng"))
            spTrangThai.setSelection(1);
        else if(thongTinPhong.getTrangThai().equals("Chưa xử lý"))
            spTrangThai.setSelection(2);
        else if(thongTinPhong.getTrangThai().equals("Trả phòng"))
            spTrangThai.setSelection(3);
        else
            spTrangThai.setSelection(4);
        //
        if (lau.equals("1"))
            spLau.setSelection(0);
        else if (lau.equals("2"))
            spLau.setSelection(1);
        else if (lau.equals("3"))
            spLau.setSelection(2);
        else if (lau.equals("4"))
            spLau.setSelection(3);
        else
            spLau.setSelection(4);
        //
        if(thongTinPhong.getLoai().equals("Đơn"))
            spLoaiPhong.setSelection(0);
        else
            spLoaiPhong.setSelection(1);
        //
        tvTenPhong.setText(thongTinPhong.getTenPhong());
        tvMaPhong.setText(thongTinPhong.getMaPhong());
        DecimalFormat toTheFormat = new DecimalFormat("#.#");
        edGiaGio.setText(toTheFormat.format(thongTinPhong.getGiaGio())+"");
        edGiaNgay.setText(toTheFormat.format(thongTinPhong.getGiaNgay())+"");
        edMoTa.setText(thongTinPhong.getMoTa());
    }

    private void setControl() {
        btnSua = findViewById(R.id.btnSuaPhong);
        btnXoa = findViewById(R.id.btnXoaPhong);
        btnTroVe = findViewById(R.id.btnTroVe);

        tvTenPhong = findViewById(R.id.tvTenPhongTT);
        tvMaPhong = findViewById(R.id.tvMaPhongTT);

        spSoPhong = findViewById(R.id.spSoPhongTT);
        spLau = findViewById(R.id.spLauTT);
        spLoaiPhong = findViewById(R.id.spLoaiPhongTT);
        spTrangThai = findViewById(R.id.spTrangThaiPhong);


        edGiaGio = findViewById(R.id.edGiaGioTT);
        edGiaNgay = findViewById(R.id.edGiaNgayTT);
        edMoTa = findViewById(R.id.edMotaTT);
    }
}