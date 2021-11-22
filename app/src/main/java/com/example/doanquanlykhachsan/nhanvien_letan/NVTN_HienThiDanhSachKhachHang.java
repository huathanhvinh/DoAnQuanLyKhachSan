package com.example.doanquanlykhachsan.nhanvien_letan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.doanquanlykhachsan.Custom_NVTN_HienThiDanhSachKH;
import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NVTN_HienThiDanhSachKhachHang extends AppCompatActivity {
    private Button btnThem , btnTroVe;
    ListView lvKhachHang;
    EditText edtTimKiem;
    Custom_NVTN_HienThiDanhSachKH nhanvienthungan;
    ArrayList<KhachHang>arrKH= new ArrayList<>();
    ArrayList<KhachHang>arrTimKiem= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_hien_thi_danh_sach_khach_hang);
        setControl();
        setEvent();
    }

    private void setEvent() {
        nhanvienthungan = new Custom_NVTN_HienThiDanhSachKH(this,R.layout.listview_nvtn_hienthidanhsachkhachhang,arrKH);
        lvKhachHang.setAdapter(nhanvienthungan);
        KhoiTao();
        lvKhachHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KhachHang kh = arrKH.get(position);
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NVTN_ThemKhachHang.class));
            }
        });
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NVTN_MenuNhanVienThuNgan.class));
            }
        });
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String timkiem = edtTimKiem.getText().toString().toLowerCase();
                arrTimKiem.clear();
                for (int i = 0;i<arrKH.size();i++)
                {

                    String makh =arrKH.get(i).getStt()+"".toLowerCase();

                    if (arrKH.get(i).getTenKH().toLowerCase().contains(timkiem)||makh.contains(timkiem))
                    {
                        arrTimKiem.add(arrKH.get(i));
                    }

                }
                nhanvienthungan = new Custom_NVTN_HienThiDanhSachKH(getApplicationContext(),R.layout.listview_nvtn_hienthidanhsachkhachhang,arrTimKiem);
                lvKhachHang.setAdapter(nhanvienthungan);
                nhanvienthungan.notifyDataSetChanged();
            }
        });
    }

    private void KhoiTao() {
        Query sapxep = StaticConfig.mKhachHang.orderByChild("stt");
        sapxep.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrKH.clear();
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    KhachHang dskh = ds.getValue(KhachHang.class);
                    arrKH.add(dskh);
                }
                nhanvienthungan.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setControl() {
        edtTimKiem = findViewById(R.id.edtTimKiem);
        lvKhachHang =findViewById(R.id.lvKhachHang);
        btnThem = findViewById(R.id.btnThem);
        btnTroVe = findViewById(R.id.btnTroVe);
    }
}