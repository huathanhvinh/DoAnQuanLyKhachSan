package com.example.doanquanlykhachsan.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.MainActivity;
import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.DichVu;
import com.example.doanquanlykhachsan.nhanvien_tapvu.*;
import com.example.doanquanlykhachsan.model.DangKyDichVu;
import com.example.doanquanlykhachsan.model.DichVuDaChon;
import com.example.doanquanlykhachsan.model.Phong;
import com.example.doanquanlykhachsan.model.PhongDaDat;
import com.example.doanquanlykhachsan.nhanvien_thungan.NVTN_MenuNhanVienThuNgan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class custom_nhanvientapvu_sudungdichvu extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<Phong> data;
    ArrayList<DichVu> datadv;
    String tenPhong = "";

    public custom_nhanvientapvu_sudungdichvu(@NonNull Context context, int resource, ArrayList<Phong> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);

        TextView tvPhong = convertView.findViewById(R.id.tvPhong);
        Button btnHuyDV = convertView.findViewById(R.id.btnHuyDV);

        Phong p = data.get(position);

        StaticConfig.mRoom.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Phong phong = ds.getValue(Phong.class);
                    if (p.getMaPhong().equals(phong.getMaPhong())) {
                        tvPhong.setText(phong.getTenPhong());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnHuyDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticConfig.mRoomRented.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            PhongDaDat da = ds.getValue(PhongDaDat.class);
                            String maPhong = da.getMaPhong();
                            String[] parts2;
                            parts2 = maPhong.split(" ");
                            for (String u : parts2) {
                                StaticConfig.mDichVu.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot ds : snapshot.getChildren()) {
                                            DichVu dv = ds.getValue(DichVu.class);
                                            if (nhanvientapvu_sudungdichvu.loaiDV.equals(dv.getMaFB()) && u.equals(p.getMaPhong())) {
                                                String str = da.getMaDichVu();
                                                String replacedStr = str.replaceAll(nhanvientapvu_sudungdichvu.loaiDV, "");
                                                StaticConfig.mRoomRented.child(da.getMaFB()).child("maDichVu").setValue(replacedStr);
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        return convertView;
    }

    private Context getDialogContext() {
        Context context;
        if (getContext() != null)
            context = getContext();
        else
            context = getContext();
        return context;
    }
}
