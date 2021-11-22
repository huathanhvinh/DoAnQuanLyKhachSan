package com.example.doanquanlykhachsan.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.KhachHang;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.example.doanquanlykhachsan.model.NhanVien_LichLamViec;
import com.example.doanquanlykhachsan.model.NhanVien_Luong;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class AD_ThongTinKhachHang extends AppCompatActivity {
    TextView tvMaKh, tvtenKH, tvSDTKH, tvDiaChi, tvCMND;
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
        //chuyển khách hàng thành nhân viên
        imChuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KhachHang thongTinKhachHang = (KhachHang) getIntent().getSerializableExtra("ThongTinKhachHang");
                AlertDialog.Builder builder = new AlertDialog.Builder(AD_ThongTinKhachHang.this);
                builder.setTitle("Thông Báo");
                builder.setMessage("Xác nhận chuyển đổi !");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StaticConfig.mNhanVien.addValueEventListener(new ValueEventListener() {
                            int i = 0;
                            int size = 1;

                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    size++;
                                    NhanVien nv = ds.getValue(NhanVien.class);
                                    if (nv.getSoDienThoai().equals(thongTinKhachHang.getSdtKH())) {
                                        i++;
                                    }
                                }
                                if (i == 0) {
                                    String key = StaticConfig.mNhanVien.push().getKey();
                                    //Thêm thông tin vào bảng nhân viên
                                    NhanVien nvMoi = new NhanVien(size, key, thongTinKhachHang.getTenKH(),
                                            thongTinKhachHang.getSdtKH(), thongTinKhachHang.getDiaChi(),
                                            "01/01/2000", thongTinKhachHang.getCmnd(), "3,000,000",
                                            "Sáng", "Tạp Vụ");
                                    StaticConfig.mNhanVien.child(key).setValue(nvMoi);
                                    //Thêm thông tin vào bảng nhân viên - lương
                                    String key1 = StaticConfig.mNhanVien_Luong.push().getKey();
                                    NhanVien_Luong nvMoi1 = new NhanVien_Luong(size, key, thongTinKhachHang.getTenKH(),
                                            thongTinKhachHang.getSdtKH(), thongTinKhachHang.getDiaChi(),
                                            "01/01/2000", thongTinKhachHang.getCmnd(), "3,000,000",
                                            "Sáng", "Tạp Vụ", "", "");
                                    StaticConfig.mNhanVien_Luong.child(key).setValue(nvMoi1);
                                    //Thêm thông tin vào bảng nhân viên - ca làm
                                    String key2 = StaticConfig.mNhanVien_LichLamViec.push().getKey();
                                    NhanVien_LichLamViec nvMoi2 = new NhanVien_LichLamViec(size, key, thongTinKhachHang.getTenKH(),
                                            thongTinKhachHang.getSdtKH(), thongTinKhachHang.getDiaChi(),
                                            "01/01/2000", thongTinKhachHang.getCmnd(), "3,000,000",
                                            "Sáng", "Tạp Vụ", "");
                                    StaticConfig.mNhanVien_LichLamViec.child(key).setValue(nvMoi2);

                                    StaticConfig.mKhachHang.child(thongTinKhachHang.getMaFB()).removeValue();

                                    Toast.makeText(getApplicationContext(), "Thêm Thành Công", Toast.LENGTH_LONG).show();
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Đã tồn tại số điện thoại trong danh sách nhân viên", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        StaticConfig.mUser.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                if(snapshot.child("sdt").getValue().toString().equals(thongTinKhachHang.getSdtKH()))
                                {
                                    //code tại đây.
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

    private void setControl() {
        tvMaKh = findViewById(R.id.tvMaKH);
        tvtenKH = findViewById(R.id.tvtenKh);
        tvSDTKH = findViewById(R.id.tvSDTKH);
        tvDiaChi = findViewById(R.id.tvDiaChiKH);
        tvCMND = findViewById(R.id.tvCMNDKH);
        imChuyen = findViewById(R.id.imChuyen);
        btnTrove = findViewById(R.id.btnTroVe);
    }

    private void setThongTinKhachHang() {
        KhachHang thongTinKhachHang = (KhachHang) getIntent().getSerializableExtra("ThongTinKhachHang");
        //Toast.makeText(getApplicationContext(), thongTinNhanVienDaSua.toString(), Toast.LENGTH_SHORT).show();
        tvMaKh.setText("KH100" + thongTinKhachHang.getStt());
        tvtenKH.setText(thongTinKhachHang.getTenKH());
        tvSDTKH.setText(thongTinKhachHang.getSdtKH());
        tvDiaChi.setText(thongTinKhachHang.getDiaChi());
        tvCMND.setText(thongTinKhachHang.getCmnd());
    }
}