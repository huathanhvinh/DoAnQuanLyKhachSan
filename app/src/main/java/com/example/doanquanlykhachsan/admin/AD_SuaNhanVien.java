package com.example.doanquanlykhachsan.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.example.doanquanlykhachsan.model.NhanVien_LichLamViec;
import com.example.doanquanlykhachsan.model.NhanVien_Luong;
import com.example.doanquanlykhachsan.model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

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
        //tắt tính năng chỉnh sửa cho số điện thoại
        edSdt.setFocusable(false);
        //tắt tính năng chỉnh sửa cho mã nv
        edManv.setFocusable(false);
        //tắt tính năng chỉnh sửa cho lịch tại editText
        edNgaysinh.setFocusable(false);
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
                //sửa thông tin nhân viên
                StaticConfig.mNhanVien.child(thongTinNhanVien.getMaFB()).setValue(thongTinNhanVien);
                //sửa thông tin nhân viên - lương
                StaticConfig.mNhanVien_Luong.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            if (thongTinNhanVien.getSoDienThoai().equals(ds.child("soDienThoai").getValue().toString())) {
                                NhanVien_Luong nv = ds.getValue(NhanVien_Luong.class);
                                NhanVien_Luong nvMoi1 = new NhanVien_Luong(nv.getStt(), nv.getMaFB(), edTennv.getText().toString(),
                                        nv.getSoDienThoai(), edDiachi.getText().toString(),
                                        edNgaysinh.getText().toString(), edCmnd.getText().toString(), nv.getLuong(),
                                        nv.getCaLam(), spChucVu.getSelectedItem().toString(), nv.getTienThuong(), nv.getGhiChu());
                                StaticConfig.mNhanVien_Luong.child(nv.getMaFB()).setValue(nvMoi1);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                //Sửa thông tin nhân viên - ca làm
                StaticConfig.mNhanVien_LichLamViec.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            if (thongTinNhanVien.getSoDienThoai().equals(ds.child("soDienThoai").getValue().toString())) {
                                NhanVien_LichLamViec nv = ds.getValue(NhanVien_LichLamViec.class);
                                NhanVien_LichLamViec nvMoi1 = new NhanVien_LichLamViec(nv.getStt(), nv.getMaFB(), edTennv.getText().toString(),
                                        nv.getSoDienThoai(), edDiachi.getText().toString(), edNgaysinh.getText().toString(),
                                        edCmnd.getText().toString(), nv.getLuong(), nv.getCaLam(), spChucVu.getSelectedItem().toString(),
                                        nv.getGhiChu());
                                StaticConfig.mNhanVien_LichLamViec.child(nv.getMaFB()).setValue(nvMoi1);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                //lấy sdt và Role
                String sdt = thongTinNhanVien.getSoDienThoai();
                int role = 0;
                if (thongTinNhanVien.getChucVu().equals("Quản Lý"))
                    role = 1;
                else if(thongTinNhanVien.getChucVu().equals("Lễ Tân"))
                    role = 2;
                else
                    role = 3;
                //Đổi Role bên User
                int finalRole = role;
                StaticConfig.mUser.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.child("sdt").getValue().toString().equals(sdt))
                        {
                            User us = snapshot.getValue(User.class);
                            us.setRole(finalRole);
                            StaticConfig.mUser.child(us.getMaFB()).setValue(us);
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                //Thông báo
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
        } else if (thongTinNhanVien.getChucVu().equals("Lễ Tân"))
            spChucVu.setSelection(1);
        else
            spChucVu.setSelection(2);
    }

    private void imButtonLich() {
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