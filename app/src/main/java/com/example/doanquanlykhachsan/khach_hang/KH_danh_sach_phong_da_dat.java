package com.example.doanquanlykhachsan.khach_hang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.adapter.Adapter_Phong;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Phong;
import com.example.doanquanlykhachsan.adapter.Phong_adapter;
import com.example.doanquanlykhachsan.model.PhongDaDat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KH_danh_sach_phong_da_dat extends AppCompatActivity {
    private ListView listView;
    private Button btntrove, btndoiphonng;
    private ArrayList<Phong> data = new ArrayList<>();
    private Phong_adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_danh_sach_phong_da_dat);
        StaticConfig.sXacNhan = "phong da thue";
        StaticConfig.chon = null;
        setControl();
        setEvnet();
    }

    private void setEvnet() {
        //chon phong de doi
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < listView.getChildCount(); i++) {
                    if (position == i) {
                        listView.getChildAt(i).setBackgroundColor(Color.WHITE);
                        StaticConfig.chon = data.get(i);

                        StaticConfig.mRoomRented.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    PhongDaDat da = ds.getValue(PhongDaDat.class);
                                    String chuoimaphong = ds.child("maPhong").getValue(String.class);
                                    String[] parts;
                                    parts = chuoimaphong.split(" ");
                                    for (String w : parts) {
                                        if (StaticConfig.chon.getMaPhong().equals(w)) {
                                            StaticConfig.mathue = da.getMaFB();
                                            if (da.getManHinh().equals("ngay")) {
                                                StaticConfig.Loai = "ngay";
                                            }
                                            if (da.getManHinh().equals("gio")) {
                                                StaticConfig.Loai = "gio";
                                            }
                                            break;
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    } else {
                        listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }

            }
        });
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btndoiphonng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StaticConfig.chon == null) {
                    new AlertDialog.Builder(KH_danh_sach_phong_da_dat.this)
                            .setTitle("Danh S??ch Ph??ng ")
                            .setMessage("Vui L??ng Ch???n Ph??ng !!")
                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                } else {
                    startActivity(new Intent(getApplicationContext(), KH_danh_sach_phong_trong.class));
                }
            }
        });
    }

    private void setControl() {
        btntrove = findViewById(R.id.btntrove);
        btndoiphonng = findViewById(R.id.btndoiphong);
        listView = findViewById(R.id.lvDanhSachPhong);
        adapter = new Phong_adapter(getApplicationContext(), R.layout.item_phong, data);
        listView.setAdapter(adapter);
        khoitao();
    }

    private void khoitao() {
        StaticConfig.mRoomRented.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    PhongDaDat da = ds.getValue(PhongDaDat.class);
                    if (da.getXacnhan().equals("???? X??c Nh???n") || da.getXacnhan().equals("Tr??? Ph??ng")) {
                        if (ds.child("maKH").getValue().toString().equals(StaticConfig.currentuser)) {
                            String chuoimaphong = ds.child("maPhong").getValue(String.class);
                            String[] parts;
                            parts = chuoimaphong.split(" ");
                            for (String w : parts) {
                                String maPhong = w.toString();

                                StaticConfig.mRoom.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot ds : snapshot.getChildren()) {
                                            Phong phong = ds.getValue(Phong.class);
                                            if (phong.getMaPhong().equals(maPhong)) {
                                                data.add(phong);
                                                if (da.getManHinh().equals("ngay")) {
                                                    StaticConfig.loaiNgay.add(phong.getMaFB());
                                                } else {
                                                    StaticConfig.loaiGio.add(phong.getMaFB());
                                                }
                                            }
                                        }
                                        adapter.notifyDataSetChanged();

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }

                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}