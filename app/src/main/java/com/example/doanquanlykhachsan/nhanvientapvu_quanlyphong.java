package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

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
                //Khi click vào textview danh sách phòng thì reset 2 radioButton
                rdDaChon.setChecked(false);
                rdChuaChon.setChecked(false);
                nhanvientapvu = new custom_nhanvientapvu_qlphong(getApplicationContext(), R.layout.listview_nhanvientapvu_quanlyphong, data);
                lvQuanLyPhong.setAdapter(nhanvientapvu);
                khoitao();

            }
        });
        //Xử lý 2 nút radioButton
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
                finish();
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

                        for(DataSnapshot ds : snapshot.getChildren() ){
                            Phong phong = ds.getValue(Phong.class);
                            if(phong.getTenPhong().toLowerCase().contains(timkiem)){
                                timkiemphong.add(phong);

                            }
                        }
                        if(timkiem.isEmpty()){
                            timkiemphong = data;
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

        //Xử lý nút radionbutton chưa chọn hiển thị các phòng xem đã dọn hay chưa
        rdChuaChon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    ArrayList<Phong> ketqua = new ArrayList<>();
                    if (rdChuaChon.isChecked()) {
                        for (int i = 0; i < data.size(); i++) {
                            ketqua.clear();
                            String ma = data.get(i).getMaFB();
                            Phong room = data.get(i);
                            ketqua.addAll(data);//Add tất cả phòng
                            StaticConfig.mQLPhong.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {


                                    for (DataSnapshot ds : snapshot.getChildren()) {

                                        nvtv_qlphong qlphong = ds.getValue(nvtv_qlphong.class);


                                        if (qlphong.getPhong().equals(ma) && qlphong.isKiemtra() == true) {

                                            //Kiểm tra nếu dữ liệu = true thì remove phòng
                                            //Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
                                            ketqua.remove(room);
                                        }
                                    }
                                    nhanvientapvu = new custom_nhanvientapvu_qlphong(getApplicationContext(), R.layout.listview_nhanvientapvu_quanlyphong, ketqua);
                                    lvQuanLyPhong.setAdapter(nhanvientapvu);
                                    nhanvientapvu.notifyDataSetChanged();
                                    Log.d("soluong", ketqua.size() + "");
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }


                    }
            }
        });

        //Xử lý nút radionbutton đã chọn hiển thị các phòng xem đã dọn hay chưa
        rdDaChon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ArrayList<Phong> ketqua = new ArrayList<>();
                if (rdDaChon.isChecked()) {
                    for (int i = 0; i < data.size(); i++) {
                        String ma = data.get(i).getMaFB();
                        Phong room = data.get(i);
                        ketqua.clear();
                        StaticConfig.mQLPhong.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    nvtv_qlphong qlphong = ds.getValue(nvtv_qlphong.class);
                                    if (qlphong.getPhong().equals(ma) && qlphong.isKiemtra() == true) {
                                        ketqua.add(room);
                                    }
                                }
                                nhanvientapvu = new custom_nhanvientapvu_qlphong(getApplicationContext(), R.layout.listview_nhanvientapvu_quanlyphong, ketqua);
                                lvQuanLyPhong.setAdapter(nhanvientapvu);
                                nhanvientapvu.notifyDataSetChanged();
                                Log.d("soluong", ketqua.size() + "");
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }

                } else {
                    nhanvientapvu = new custom_nhanvientapvu_qlphong(getApplicationContext(), R.layout.listview_nhanvientapvu_quanlyphong, data);
                    lvQuanLyPhong.setAdapter(nhanvientapvu);
                    nhanvientapvu.notifyDataSetChanged();
                }

            }
        });
    }


    private void khoitao() {
//        nvtv_qlphong qlphong = new nvtv_qlphong("phòng:2","Kiểm tra tình trạng phòng");
//        nvtv_qlphong qlphong1 = new nvtv_qlphong("phòng:3","Kiểm tra tình trạng phòng");
//        data.add(qlphong);
//        data.add(qlphong1);
        Query sapxep = StaticConfig.mRoom.orderByChild("soPhong");
        sapxep.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Phong room = ds.getValue(Phong.class);
                    data.add(room);
                }
                nhanvientapvu.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}