package com.example.doanquanlykhachsan.admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.KhuyenMai;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AD_SuaKhuyenMai extends AppCompatActivity {
    TextView tvMaKM, tvSizeTen, tvNgayBatDau, tvNgayKetThuc;
    ImageButton imNgayBatDau, imNgayKetThuc;
    EditText edTenKm, edMucGiam, edNoiDung;
    Button btnSua, btnTrove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_sua_khuyen_mai);
        setControl();
        setEvent();

    }

    private void setEvent() {
        //lấy thông tin khuyến mãi từ listview
        layThongTinKhuyenMai();
        //xử lý nút trở về
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KhuyenMai khuyenMai = (KhuyenMai) getIntent().getSerializableExtra("ThongTinKhuyenMai");
                if (khuyenMai.getTenKM().equals(edTenKm.getText().toString()) == false ||
                        khuyenMai.getNgayBatDau().equals(tvNgayBatDau.getText().toString()) == false ||
                        khuyenMai.getNgayKetThuc().equals(tvNgayKetThuc.getText().toString()) == false ||
                        khuyenMai.getMucGiamGia() != Integer.parseInt(edMucGiam.getText().toString()) ||
                        khuyenMai.getNoiDung().equals(edNoiDung.getText().toString()) == false) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(AD_SuaKhuyenMai.this);
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
        //Xử lý đo độ dài tên khuyến mãi
        edTenKm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int size = edTenKm.getText().toString().length();
                tvSizeTen.setText(size + "/50");
            }
        });
        //mở lịch và sửa ngày bất đầu
        imNgayBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imButtonLichNgayBatDau();
            }
        });
        //mở lịch và sửa ngày bất đầu
        imNgayKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imButtonLichNgayKetThuc();
            }
        });
        //Xử lý nút sửa Khuyến mãi
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sizeTen = edTenKm.getText().toString().length();
                int sizeNoiDung = edNoiDung.getText().toString().length();
                int checkNgay = 0;
                //kiểm tra thời gian bất đầu với thời gian kết thúc
                try {
                    Date ngayBatDau = new SimpleDateFormat("dd/MM/yyyy").parse(tvNgayBatDau.getText().toString());
                    Date ngayKetThuc = new SimpleDateFormat("dd/MM/yyyy").parse(tvNgayKetThuc.getText().toString());
                    if (ngayBatDau.compareTo(ngayKetThuc) > 0)
                        checkNgay = 1;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //Kiểm tra các trường hợp
                if (sizeTen == 0) {
                    Toast.makeText(getApplicationContext(), "Tên không được bỏ trống", Toast.LENGTH_LONG).show();
                } else if (sizeTen > 50) {
                    Toast.makeText(getApplicationContext(), "Tên không được vượt quá 50 kí tự", Toast.LENGTH_LONG).show();
                } else if (checkNgay != 0) {
                    Toast.makeText(getApplicationContext(), "Thời gian bất đầu không được lớn hơn thời gian kết thúc", Toast.LENGTH_LONG).show();
                } else if (edMucGiam.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Không được để trống mức giảm giá", Toast.LENGTH_LONG).show();
                } else if (Integer.parseInt(edMucGiam.getText().toString()) < 1) {
                    Toast.makeText(getApplicationContext(), "Mức giảm giá phải lớn hơn 1", Toast.LENGTH_LONG).show();
                } else if (Integer.parseInt(edMucGiam.getText().toString()) > 100) {
                    Toast.makeText(getApplicationContext(), "Mức giảm giá phải nhỏ hơn 100", Toast.LENGTH_LONG).show();
                }else if (sizeNoiDung == 0) {
                    Toast.makeText(getApplicationContext(), "Nội dung không được để trống", Toast.LENGTH_LONG).show();
                }
                else
                {
                    KhuyenMai khuyenMai = (KhuyenMai) getIntent().getSerializableExtra("ThongTinKhuyenMai");
                    khuyenMai.setTenKM(edTenKm.getText().toString());
                    khuyenMai.setNgayBatDau(tvNgayBatDau.getText().toString());
                    khuyenMai.setNgayKetThuc(tvNgayKetThuc.getText().toString());
                    khuyenMai.setMucGiamGia(Integer.parseInt(edMucGiam.getText().toString()));
                    khuyenMai.setNoiDung(edNoiDung.getText().toString());

                    StaticConfig.mKhuyenMai.child(khuyenMai.getMaFB()).setValue(khuyenMai);
                    Toast.makeText(getApplicationContext(), "Sửa thành công !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void layThongTinKhuyenMai() {
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

    private void imButtonLichNgayBatDau() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                tvNgayBatDau.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }
    private void imButtonLichNgayKetThuc() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                tvNgayKetThuc.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    private void setControl() {
        tvMaKM = findViewById(R.id.tvMakhuyenMaiE);
        tvSizeTen = findViewById(R.id.tvSizeTenKME);
        tvNgayBatDau = findViewById(R.id.tvNgayBatDauE);
        tvNgayKetThuc = findViewById(R.id.tvNgayKetThucE);
        edTenKm = findViewById(R.id.edTenKhuyenMaiE);
        edMucGiam = findViewById(R.id.edGiamGiaE);
        edNoiDung = findViewById(R.id.edNoiDungChuongTrinhE);
        btnSua = findViewById(R.id.btnSuaE);
        btnTrove = findViewById(R.id.btnTroVe);
        imNgayBatDau = findViewById(R.id.imNgayBatDauE);
        imNgayKetThuc = findViewById(R.id.imNgayKetThucE);
    }

}