package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.NhanVien;

public class AD_SuaNhanVien extends AppCompatActivity {
    EditText edManv, edTennv, edSdt, edNgaysinh, edDiachi, edCmnd, edLuong;
    ImageButton imLich, imLuu;
    Spinner spChucVu;
    Button btnTrove;
    TextView tvLuu;
    AD_ThongTinNhanVien ad_thongTinNhanVien = new AD_ThongTinNhanVien();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_sua_nhan_vien);
        setControl();
        setEvent();
    }

    private void setEvent() {
        //tắt tính năng chỉnh sửa cho mã nv
        edManv.setFocusable(false);
        //load thông tin nhân viên
        setThongTinNhanVien();
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
                    builder.setMessage("Bạn Bạn chưa lưu, Bạn có muốn thoát không ?");
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
                } else
                    finish();
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
        } else
            spChucVu.setSelection(1);
        //Toast.makeText(getApplicationContext(), spChucVu.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }
}