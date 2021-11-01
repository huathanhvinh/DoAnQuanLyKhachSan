package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;

public class NVTN_ThemKhachHang extends AppCompatActivity {
    Button btnThemKH,btnTroVe;
    EditText edtTenKH,edtSoDT,edtDiaChi,edtCMND;
    Spinner spHoiVien;
    TextView tvMaKH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_them_khach_hang);
        setconTrol();
        setEvent();
    }

    private void setEvent() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnThemKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String tenkh =edtTenKH.getText().toString();
//                String diachi=edtDiaChi.getText().toString();
//                String sodt=edtSoDT.getText().toString();
//                String cmnd=edtCMND.getText().toString();
//                String hoivien="";
//                if(spHoiVien.getSelectedItem().equals("Bạc"))
//                {
//                    hoivien="Bạc";
//                }
//                else if (spHoiVien.getSelectedItem().equals("Vàng"))
//                {
//                    hoivien="Vàng";
//                }
//                else {
//                    hoivien="Kim Cương";
//                }
//                StaticConfig.mKhachHang.child("TenKhachHang").setValue(tenkh);
//                StaticConfig.mKhachHang.child("DiaChi").setValue(diachi);
//                StaticConfig.mKhachHang.child("SoDT").setValue(sodt);
//                StaticConfig.mKhachHang.child("CMND").setValue(cmnd);
//                StaticConfig.mKhachHang.child("HoiVien").setValue(hoivien);
            }
        });
    }

    private void setconTrol() {
        tvMaKH=findViewById(R.id.tvMaKH);
        spHoiVien=findViewById(R.id.spHoiVien);
        edtDiaChi = findViewById(R.id.edtDiachi);
        edtSoDT = findViewById(R.id.edtSoDT);
        edtCMND = findViewById(R.id.edtCMND);
        edtTenKH = findViewById(R.id.edtTenKH);
        btnThemKH = findViewById(R.id.btnThemKH);
        btnTroVe = findViewById(R.id.btnTroVe);
    }
}