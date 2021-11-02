package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.KhuyenMai;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class AD_ThemKhuyenMai extends AppCompatActivity {
    EditText edTenkm, edNoidung, edMucGiamGia;
    TextView tvSizeTen, tvNgayBatDau, tvNgayKetThuc, tvMaKM;
    ImageButton imNgayBatDau, imNgayKetThuc, imChange;
    Button btnThemMoi, btnTrove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_tao_khuyen_mai);
        setControl();
        setevent();
    }

    private void setevent() {
        //Lấy ra ngày bất đầu và kết thúc hiện tại rồi gán vào textview
        layNgayHienTai();
        //Sự kiện đo độ dài tên chương trình khuyến mãi
        edTenkm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int size = edTenkm.getText().toString().length();
                tvSizeTen.setText(size + "/50");
            }
        });
        //Sự kiện nút tạo mới
        btnThemMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sizeTen = edTenkm.getText().toString().length();
                int sizeNoiDung = edNoidung.getText().toString().length();
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
                //khởi tạo khuyến mãi
                if (sizeTen == 0) {
                    Toast.makeText(getApplicationContext(), "Tên không được bỏ trống", Toast.LENGTH_LONG).show();
                } else if (sizeTen > 50) {
                    Toast.makeText(getApplicationContext(), "Tên không được vượt quá 50 kí tự", Toast.LENGTH_LONG).show();
                } else if (checkNgay != 0) {
                    Toast.makeText(getApplicationContext(), "Thời gian bất đầu không được lớn hơn thời gian kết thúc", Toast.LENGTH_LONG).show();
                } else if (edMucGiamGia.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Không được để trống mức giảm giá", Toast.LENGTH_LONG).show();
                } else if (Integer.parseInt(edMucGiamGia.getText().toString()) < 1) {
                    Toast.makeText(getApplicationContext(), "Mức giảm giá phải lớn hơn 1", Toast.LENGTH_LONG).show();
                } else if (Integer.parseInt(edMucGiamGia.getText().toString()) > 100) {
                    Toast.makeText(getApplicationContext(), "Mức giảm giá phải nhỏ hơn 100", Toast.LENGTH_LONG).show();
                }else if (sizeNoiDung == 0) {
                    Toast.makeText(getApplicationContext(), "Nội dung không được để trống", Toast.LENGTH_LONG).show();
                }
                else
                {
                    StaticConfig.mKhuyenMai.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int check = 0;
                            for (DataSnapshot ds:snapshot.getChildren()) {
                                if (ds.child("maKM").getValue().toString().equals(tvMaKM.getText().toString()))
                                {
                                    check = 1;
                                }
                            }
                            if (check != 0)
                            {
                                Toast.makeText(getApplicationContext(), "Đã tồn tại mã này trong danh sách", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                int giamGia = Integer.parseInt(edMucGiamGia.getText().toString());
                                String key = StaticConfig.mKhuyenMai.push().getKey();
                                KhuyenMai km = new KhuyenMai(key,tvMaKM.getText().toString(),edTenkm.getText().toString(),
                                        tvNgayBatDau.getText().toString(),tvNgayKetThuc.getText().toString(),
                                        giamGia,edNoidung.getText().toString());
                                StaticConfig.mKhuyenMai.child(key).setValue(km);
                                Toast.makeText(getApplicationContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        //mở lịch và thêm dữ liệu vào ngày bất đầu
        imNgayBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imButtonLichNgayBatDau();
            }
        });
        //mở lịch và thêm dữ liệu vào ngày Kết Thúc
        imNgayKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imButtonLichNgayKetThuc();
            }
        });
        //set Mã khuyến mãi khi mới mở màn hình
        StringBuilder sb = getStringBuilder();
        tvMaKM.setText(sb + "");
        //tạo mã mới khi ấn vào nút thay đổi
        imChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = getStringBuilder();
                tvMaKM.setText(sb + "");
            }
        });
        //sự kiện khi nhấn nút trở về
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void layNgayHienTai() {
        Date date = new Date();
        SimpleDateFormat ngayHienTai = new SimpleDateFormat("dd/MM/yyyy");
        tvNgayBatDau.setText(ngayHienTai.format(date.getTime()));
        tvNgayKetThuc.setText(ngayHienTai.format(date.getTime()));
    }

    private void setControl() {
        edTenkm = findViewById(R.id.edTenKhuyenMai);
        edNoidung = findViewById(R.id.edNoiDungChuongTrinh);
        edMucGiamGia = findViewById(R.id.edGiamGia);
        tvSizeTen = findViewById(R.id.tvSizeTenKM);
        tvNgayBatDau = findViewById(R.id.tvNgayBatDau);
        tvNgayKetThuc = findViewById(R.id.tvNgayKetThuc);
        tvMaKM = findViewById(R.id.tvMaKhuyenMai);
        imNgayBatDau = findViewById(R.id.imNgayBatDau);
        imNgayKetThuc = findViewById(R.id.imNgayKetThuc);
        imChange = findViewById(R.id.imChangeMaKM);
        btnThemMoi = findViewById(R.id.btnTaoMoiKM);
        btnTrove = findViewById(R.id.btnTroVe);
    }

    private StringBuilder getStringBuilder() {
        String ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        }
        return sb;
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
}