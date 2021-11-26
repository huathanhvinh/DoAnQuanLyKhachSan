package com.example.doanquanlykhachsan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.DangKyDichVu;
import com.example.doanquanlykhachsan.model.DichVuDaChon;
import com.example.doanquanlykhachsan.model.Phong;
import com.example.doanquanlykhachsan.model.PhongDaDat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class custom_nhanvientapvu_sudungdichvu extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<Phong> data;
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

        Phong suDungDichVu = data.get(position);

        StaticConfig.mRoom.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Phong phong = ds.getValue(Phong.class);
                    if (suDungDichVu.getMaPhong().equals(phong.getMaPhong())) {
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
                        for(DataSnapshot ds:snapshot.getChildren()){
                            PhongDaDat da= ds.getValue(PhongDaDat.class);
                            String maPhong =da.getMaPhong();
                            String[] parts1;
                            parts1 = maPhong.split(" ");
                            for (String w : parts1) {
                                if (w.equals(suDungDichVu.getMaPhong())) {
                                    StaticConfig.mRoomRented.child(da.getMaFB()).child("maDichVu").setValue("");
                                }
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
}
