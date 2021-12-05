package com.example.doanquanlykhachsan.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.HoaDon;
import com.example.doanquanlykhachsan.adapter.*;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AD_ThongKe extends AppCompatActivity {
    Button btnTroVe;
    GridView gridView;
    ImageView imTimKiem;
    ArrayList<HoaDon> data = new ArrayList<>();
    Adapter_thongke adapte;
    TextView tvTongtien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_thong_ke);
        setControl();
        setEvent();
    }

    private void setControl() {
        btnTroVe = findViewById(R.id.btnTroVe);
        gridView = findViewById(R.id.listhoadon);
        tvTongtien = findViewById(R.id.tvTongTien);
        imTimKiem = findViewById(R.id.imTimKiem);
        adapte = new Adapter_thongke(getApplicationContext(), R.layout.item_hoadon, data);
        gridView.setAdapter(adapte);
        khoitao();
    }

    private void khoitao() {
        StaticConfig.mHoaDon.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    HoaDon hd = ds.getValue(HoaDon.class);
                    data.add(hd);
                }
                //Tong tien
                float Tong = 0;
                for (int i = 0; i < data.size(); i++) {
                    Tong += data.get(i).getTongTien();
                }
                DecimalFormat toTheFormat = new DecimalFormat("###,###,###.#");
                tvTongtien.setText(toTheFormat.format(Tong) + " VNĐ");
                adapte.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void seachDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_seach);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setGravity(Gravity.TOP);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        EditText seach = dialog.findViewById(R.id.editTimKiem);
        ImageView imTimkiem = dialog.findViewById(R.id.imTimkiem);
        imTimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timkiem = seach.getText().toString().toLowerCase();
                ArrayList<HoaDon>temp= new ArrayList<>();
                StaticConfig.mHoaDon.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        data.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            HoaDon hd = ds.getValue(HoaDon.class);
                            StaticConfig.mNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot ds:snapshot.getChildren()){
                                        NhanVien nv = ds.getValue(NhanVien.class);
                                        if(nv.getMaFB().equals(hd.getMaNV())){
                                            if(nv.getTenNV().toLowerCase().contains(timkiem)){
                                                data.add(hd);
                                            }
                                        }
                                    }
                                    //Tong tien
                                    float Tong = 0;
                                    for (int i = 0; i < data.size(); i++) {
                                        Tong += data.get(i).getTongTien();
                                    }
                                    DecimalFormat toTheFormat = new DecimalFormat("###,###,###.#");
                                    tvTongtien.setText(toTheFormat.format(Tong) + " VNĐ");
                                    adapte.notifyDataSetChanged();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }

                        dialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        dialog.show();
    }

    private void setEvent() {
        imTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seachDialog();
            }
        });
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}