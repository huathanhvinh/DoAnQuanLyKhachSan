package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.example.doanquanlykhachsan.model.NhanVien_LichLamViec;
import com.example.doanquanlykhachsan.model.NhanVien_Luong;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class AD_ThongTinNhanVien extends AppCompatActivity {
    TextView tvMa, tvTen, tvSdt, tvNgaySinh, tvQueQuan, tvCMND, tvLuong, tvChucVu;
    Button btnTrove;
    ImageButton imSuaNhanVien, imXoaNhanVien;

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
        tvSdt = findViewById(R.id.tvSoDienThoai);
        tvNgaySinh = findViewById(R.id.tvNgaysinh);
        tvQueQuan = findViewById(R.id.tvQueQuan);
        tvCMND = findViewById(R.id.tvCMNN);
        tvLuong = findViewById(R.id.tvLuongNV);
        tvChucVu = findViewById(R.id.tvChucvu);
        btnTrove = findViewById(R.id.btnTroVe);
        imSuaNhanVien = findViewById(R.id.imSuaNhanVien);
        imXoaNhanVien = findViewById(R.id.imXoaNhanVien);
    }

    private void setEvent() {
        //load thông tin nhân viên
        setThongTinNhanVien();
        //Sự kiện button sửa nhân viên
        imSuaNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien thongTinNhanVien = (NhanVien) getIntent().getSerializableExtra("ThongTinNhanVien");
                Intent intent = new Intent(getApplicationContext(), AD_SuaNhanVien.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ThongTinNhanVien", thongTinNhanVien);
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
        //sự kiện button xóa nhân viên
        xoaThongTinNhanVien();
        //sự kiện button trở về
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //lấy thông tin nhân viên từ listview
    private void setThongTinNhanVien() {
        NhanVien thongTinNhanVien = (NhanVien) getIntent().getSerializableExtra("ThongTinNhanVien");
        //Toast.makeText(getApplicationContext(), thongTinNhanVienDaSua.toString(), Toast.LENGTH_SHORT).show();
        tvMa.setText("NV100" + thongTinNhanVien.getStt());
        tvTen.setText(thongTinNhanVien.getTenNV());
        tvSdt.setText(thongTinNhanVien.getSoDienThoai());
        tvNgaySinh.setText(thongTinNhanVien.getNgaySinh());
        tvQueQuan.setText(thongTinNhanVien.getDiaChi());
        tvCMND.setText(thongTinNhanVien.getCmnd());
        tvLuong.setText(thongTinNhanVien.getLuong() + " VNĐ");
        tvChucVu.setText(thongTinNhanVien.getChucVu());
    }

    private void xoaThongTinNhanVien() {
        NhanVien thongTinNhanVien = (NhanVien) getIntent().getSerializableExtra("ThongTinNhanVien");
        imXoaNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AD_ThongTinNhanVien.this);
                builder.setTitle("Xóa Nhân viên");
                builder.setMessage("Bạn có muốn xóa không ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //xóa thông tin nhân viên
                        StaticConfig.mNhanVien.child(thongTinNhanVien.getMaFB()).removeValue();
                        //xóa thông tin nhân viên - lương
                        StaticConfig.mNhanVien_Luong.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot ds:snapshot.getChildren()) {
                                    if(thongTinNhanVien.getSoDienThoai().equals(ds.child("soDienThoai").getValue().toString()))
                                    {
                                        NhanVien_Luong nv = ds.getValue(NhanVien_Luong.class);
                                        StaticConfig.mNhanVien_Luong.child(nv.getMaFB()).removeValue();
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        //Xóa thông tin nhân viên - ca làm
                        StaticConfig.mNhanVien_LichLamViec.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot ds:snapshot.getChildren()) {
                                    if(thongTinNhanVien.getSoDienThoai().equals(ds.child("soDienThoai").getValue().toString()))
                                    {
                                        NhanVien_LichLamViec nv = ds.getValue(NhanVien_LichLamViec.class);
                                        StaticConfig.mNhanVien_LichLamViec.child(nv.getMaFB()).removeValue();
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        //Thông báo
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
}