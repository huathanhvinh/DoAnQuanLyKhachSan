package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.NhanVien;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AD_SuaNhanVien extends AppCompatActivity {
    EditText edManv, edTennv, edSdt, edNgaysinh, edDiachi, edCmnd, edLuong;
    ImageButton imLich, imLuu;
    Spinner spChucVu;
    Button btnTrove;
    TextView tvLuu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_sua_nhan_vien);
        setControl();
        setEvent();
    }

    private void setEvent() {
        //Sự kiện dành cho lịch
        imLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imButtonLich();
            }
        });
        //tắt tính năng chỉnh sửa cho mã nv
        edManv.setFocusable(false);
        //tắt tính năng chỉnh sửa Lương cho nhân viên
        edLuong.setFocusable(false);
        //load thông tin nhân viên
        setThongTinNhanVien();
        //Sự kiện nút Lưu Thông Tin
        imLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien thongTinNhanVien = (NhanVien) getIntent().getSerializableExtra("ThongTinNhanVien");
                thongTinNhanVien.setTenNV(edTennv.getText().toString());
                thongTinNhanVien.setSoDienThoai(edSdt.getText().toString());
                thongTinNhanVien.setNgaySinh(edNgaysinh.getText().toString());
                thongTinNhanVien.setDiaChi(edDiachi.getText().toString());
                thongTinNhanVien.setCmnd(edCmnd.getText().toString());
                thongTinNhanVien.setLuong(edLuong.getText().toString());
                thongTinNhanVien.setChucVu(spChucVu.getSelectedItem().toString());
                StaticConfig.mNhanVien.child(thongTinNhanVien.getMaFB()).setValue(thongTinNhanVien);
                Toast.makeText(getApplicationContext(), "Lưu Thành Công", Toast.LENGTH_SHORT).show();
            }
        });
        //sự kiện nút trở về
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien thongTinNhanVien = (NhanVien) getIntent().getSerializableExtra("ThongTinNhanVien");
                if (thongTinNhanVien.getTenNV().equals(edTennv.getText().toString()) == false ||
                        thongTinNhanVien.getSoDienThoai().equals(edSdt.getText().toString()) == false ||
                        thongTinNhanVien.getNgaySinh().equals(edNgaysinh.getText().toString()) == false ||
                        thongTinNhanVien.getDiaChi().equals(edDiachi.getText().toString()) == false ||
                        thongTinNhanVien.getCmnd().equals(edCmnd.getText().toString()) == false ||
                        thongTinNhanVien.getLuong().equals(edLuong.getText().toString()) == false ||
                        thongTinNhanVien.getChucVu().equals(spChucVu.getSelectedItem().toString()) == false) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(AD_SuaNhanVien.this);
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

    private void setControl() {
        edManv = findViewById(R.id.edManv);
        edTennv = findViewById(R.id.edtenNV);
        edSdt = findViewById(R.id.edSDT);
        edNgaysinh = findViewById(R.id.edNgaySinh);
        edDiachi = findViewById(R.id.edDiaChi);
        edCmnd = findViewById(R.id.edCMND);
        edLuong = findViewById(R.id.edLuong);

        imLich = findViewById(R.id.imLich);
        imLuu = findViewById(R.id.imLuu);

        spChucVu = findViewById(R.id.spChucVu);

        btnTrove = findViewById(R.id.btnTroVe);

        tvLuu = findViewById(R.id.tvLuu);
    }
    private void setThongTinNhanVien() {
        NhanVien thongTinNhanVien = (NhanVien) getIntent().getSerializableExtra("ThongTinNhanVien");
        edManv.setText("100" + thongTinNhanVien.getStt());
        edTennv.setText(thongTinNhanVien.getTenNV());
        edSdt.setText(thongTinNhanVien.getSoDienThoai());
        edNgaysinh.setText(thongTinNhanVien.getNgaySinh());
        edDiachi.setText(thongTinNhanVien.getDiaChi());
        edCmnd.setText(thongTinNhanVien.getCmnd());
        edLuong.setText(thongTinNhanVien.getLuong());
        if (thongTinNhanVien.getChucVu().equals("Tạp Vụ")) {
            spChucVu.setSelection(0);
        } else if(thongTinNhanVien.getChucVu().equals("Lễ Tân"))
            spChucVu.setSelection(1);
        else
            spChucVu.setSelection(2);
    }
    private void imButtonLich()
    {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edNgaysinh.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }
}