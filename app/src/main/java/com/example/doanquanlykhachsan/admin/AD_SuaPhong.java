package com.example.doanquanlykhachsan.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Phong;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class AD_SuaPhong extends AppCompatActivity {
    TextView tvTenPhong,tvMaPhong;
    Spinner spLoaiPhong,spTrangThai;
    RadioButton rd1phong,rdNhieuPhong;
    EditText edGiaGio,edGiaNgay,edMoTa;
    Button btnLuu,btnTrove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_sua_phong);
        setControl();
        setEvent();
    }

    private void setControl() {
        tvTenPhong = findViewById(R.id.tvTenPhongS);
        tvMaPhong = findViewById(R.id.tvMaPhongS);

        spLoaiPhong = findViewById(R.id.spLoaiPhongS);
        spTrangThai = findViewById(R.id.spTrangThaiPhong);
        rd1phong = findViewById(R.id.rd1phong);
        rdNhieuPhong = findViewById(R.id.rdNhieuPhong);

        edGiaGio = findViewById(R.id.edGiaGioS);
        edGiaNgay = findViewById(R.id.edGiaNgayS);
        edMoTa = findViewById(R.id.edMotaS);

        btnLuu = findViewById(R.id.btnSuaPhong);
        btnTrove = findViewById(R.id.btnTroVe);
    }

    private void setEvent() {
        //lấy thông tin phòng
        layThongTinPhong();
        //nút trở về
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Phong thongTinPhong = (Phong) getIntent().getSerializableExtra("ThongTinPhong");
                String giaGio = thongTinPhong.getGiaGio()+"";
                String giaNgay = thongTinPhong.getGiaNgay()+"";
                String loaiPhong = thongTinPhong.getLoai();
                if(thongTinPhong.getMoTa().equals(edMoTa.getText().toString())==false||
                giaGio.equals(edGiaGio.getText().toString())==false||
                giaNgay.equals(edGiaNgay.getText().toString())==false||
                loaiPhong.equals(spLoaiPhong.getSelectedItem().toString())==false)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AD_SuaPhong.this);
                    builder.setTitle("Sửa Phòng");
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
                }
                else
                {
                    finish();
                }
            }
        });
        //nút lưu
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Phong thongTinPhong = (Phong) getIntent().getSerializableExtra("ThongTinPhong");
                if (rd1phong.isChecked())
                {
                    thongTinPhong.setLoai(spLoaiPhong.getSelectedItem().toString());
                    thongTinPhong.setGiaGio(Float.parseFloat(edGiaGio.getText().toString()));
                    thongTinPhong.setGiaNgay(Float.parseFloat(edGiaNgay.getText().toString()));
                    thongTinPhong.setMoTa(edMoTa.getText().toString());
                    thongTinPhong.setTrangThai(spTrangThai.getSelectedItem().toString());
                    StaticConfig.mPhong.child(thongTinPhong.getMaFB()).setValue(thongTinPhong);
                    new AlertDialog.Builder(AD_SuaPhong.this)
                            .setTitle("Sửa Phòng")
                            .setMessage("lưu thành công!!")
                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                }
                else
                {
                    thongTinPhong.setLoai(spLoaiPhong.getSelectedItem().toString());
                    thongTinPhong.setGiaGio(Integer.parseInt(edGiaGio.getText().toString()));
                    thongTinPhong.setGiaNgay(Integer.parseInt(edGiaNgay.getText().toString()));
                    thongTinPhong.setMoTa(edMoTa.getText().toString());
                    StaticConfig.mPhong.child(thongTinPhong.getMaFB()).setValue(thongTinPhong);
                    StaticConfig.mPhong.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            Phong p = new Phong();
                            p = snapshot.getValue(Phong.class);
                            p.setGiaGio(Integer.parseInt(edGiaGio.getText().toString()));
                            p.setGiaNgay(Integer.parseInt(edGiaNgay.getText().toString()));
                            StaticConfig.mPhong.child(p.getMaFB()).setValue(p);
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
                    new AlertDialog.Builder(AD_SuaPhong.this)
                            .setTitle("Sửa nhân viên")
                            .setMessage("lưu thành công!!")
                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                }
            }
        });
    }
    private void layThongTinPhong() {
        Phong thongTinPhong = (Phong) getIntent().getSerializableExtra("ThongTinPhong");
        String lau = thongTinPhong.getLau()+"";
        if(thongTinPhong.getLoai().equals("Đơn"))
            spLoaiPhong.setSelection(0);
        else
            spLoaiPhong.setSelection(1);
        //
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
        tvTenPhong.setText(thongTinPhong.getTenPhong());
        tvMaPhong.setText(thongTinPhong.getMaPhong());
        edGiaGio.setText(thongTinPhong.getGiaGio()+"");
        edGiaNgay.setText(thongTinPhong.getGiaNgay()+"");
        edMoTa.setText(thongTinPhong.getMoTa());
    }
}