package com.example.doanquanlykhachsan.nhanvien_tapvu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.adapter.*;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.example.doanquanlykhachsan.nhanvien_tapvu.*;

import java.util.ArrayList;

public class nhanvientapvu_quanlyphong extends AppCompatActivity {
    TextView tvDSPhong;
    ListView lvQuanLyPhong;
    Button btnTroVe;
    RadioButton rdDaChon, rdChuaChon;
    custom_nhanvientapvu_qlphong nhanvientapvu;
    EditText editTimKiem;
    ArrayList<Phong> timkiemphong = new ArrayList<>();
    ArrayList<Phong> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvientapvu_quanlyphong);
        setControl();
        setEvent();
    }

    private void setControl() {
        lvQuanLyPhong = findViewById(R.id.lvQuanLyPhong);
        btnTroVe = findViewById(R.id.btnTroVe);
        rdDaChon = findViewById(R.id.rdDaChon);
        rdChuaChon = findViewById(R.id.rdChuaChon);
        editTimKiem = findViewById(R.id.editTimKiem);
        tvDSPhong = findViewById(R.id.tvDSPhong);
    }

    private void setEvent() {
        tvDSPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Khi click v??o textview danh s??ch ph??ng th?? reset 2 radioButton
                rdDaChon.setChecked(false);
                rdChuaChon.setChecked(false);
                nhanvientapvu = new custom_nhanvientapvu_qlphong(getApplicationContext(), R.layout.listview_nhanvientapvu_quanlyphong, data);
                lvQuanLyPhong.setAdapter(nhanvientapvu);
                khoitao();

            }
        });
        //X??? l?? 2 n??t radioButton
        rdDaChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdDaChon.setChecked(true);
                rdChuaChon.setChecked(false);
            }
        });
        rdChuaChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdDaChon.setChecked(false);
                rdChuaChon.setChecked(true);
            }
        });
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Nhanvientapvu_manhinhchinh.class));
            }
        });
        nhanvientapvu = new custom_nhanvientapvu_qlphong(this, R.layout.listview_nhanvientapvu_quanlyphong, data);
        lvQuanLyPhong.setAdapter(nhanvientapvu);
        khoitao();


        //Tim kiem
        editTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String timkiem = editTimKiem.getText().toString().toLowerCase();
                StaticConfig.mRoom.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        timkiemphong.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Phong phong = ds.getValue(Phong.class);
                            if (phong.getTenPhong().toLowerCase().contains(timkiem)) {
                                if (rdChuaChon.isChecked()) {
                                    if (phong.getTrangThai().equals("Ch??a D???n")) {
                                        timkiemphong.add(phong);
                                    }
                                }
                                if (rdDaChon.isChecked()) {
                                    if (!phong.getTrangThai().equals("Ch??a D???n")) {
                                        timkiemphong.add(phong);
                                    }
                                }
                            }
                        }
                        nhanvientapvu = new custom_nhanvientapvu_qlphong(getApplicationContext(), R.layout.listview_nhanvientapvu_quanlyphong, timkiemphong);
                        lvQuanLyPhong.setAdapter(nhanvientapvu);
                        nhanvientapvu.notifyDataSetChanged();


                        //Toast.makeText(getApplicationContext(),timkiemphong.size() , Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

        //X??? l?? n??t radionbutton ch??a ch???n hi???n th??? c??c ph??ng xem ???? d???n hay ch??a
        rdChuaChon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ArrayList<Phong> ketqua = new ArrayList<>();
                if (rdChuaChon.isChecked()) {
                    StaticConfig.mRoom.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ketqua.clear();
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                Phong p = ds.getValue(Phong.class);
                                if (p.getTrangThai().equals("Ch??a D???n")) {
                                    ketqua.add(p);
                                }
                            }
                            nhanvientapvu = new custom_nhanvientapvu_qlphong(getApplicationContext(), R.layout.listview_nhanvientapvu_quanlyphong, ketqua);
                            lvQuanLyPhong.setAdapter(nhanvientapvu);
                            nhanvientapvu.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }

            }

        });

        //X??? l?? n??t radionbutton ???? ch???n hi???n th??? c??c ph??ng xem ???? d???n hay ch??a
        rdDaChon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ArrayList<Phong> ketqua = new ArrayList<>();
                if (rdDaChon.isChecked()) {
                    StaticConfig.mRoom.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ketqua.clear();
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                Phong p = ds.getValue(Phong.class);
                                if (p.getTrangThai().equals("Tr???ng")) {
                                    ketqua.add(p);
                                }
                            }
                            nhanvientapvu = new custom_nhanvientapvu_qlphong(getApplicationContext(), R.layout.listview_nhanvientapvu_quanlyphong, ketqua);
                            lvQuanLyPhong.setAdapter(nhanvientapvu);
                            nhanvientapvu.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }

            }


        });
    }


    private void khoitao() {
        Query sapxep = StaticConfig.mRoom.orderByChild("sophong");
        sapxep.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Phong room = ds.getValue(Phong.class);
                    if (room.getTrangThai().equals("Ch??a D???n")) {
                        data.add(room);
                    }
                }
                nhanvientapvu.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        rdChuaChon.setChecked(true);
    }
}