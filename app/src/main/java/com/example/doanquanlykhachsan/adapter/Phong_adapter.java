package com.example.doanquanlykhachsan.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.khach_hang.KH_ChiTietPhong;
import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.model.Phong;
import com.example.doanquanlykhachsan.model.PhongDaDat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Phong_adapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Phong> data;

    public Phong_adapter(@NonNull Context context, int resource, ArrayList<Phong> data) {
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
        TextView tenphong = convertView.findViewById(R.id.tvTenPhong);
        TextView solau = convertView.findViewById(R.id.tvLau);
        TextView loai = convertView.findViewById(R.id.tvLoai);
        TextView gia = convertView.findViewById(R.id.tvGiaNgay);
        TextView moTa = convertView.findViewById(R.id.tvMoTa);
        TextView chitiet = convertView.findViewById(R.id.tvChiTiet);

        Phong phong = data.get(position);
        tenphong.setText(phong.getTenPhong());
        solau.setText(phong.getLau() + "");

        loai.setText(phong.getLoai());
        DecimalFormat toTheFormat = new DecimalFormat("###,###,###.#");
//        if(StaticConfig.Loai.equals("ngay")) {
//            gia.setText(toTheFormat.format(phong.getGiaNgay()));
//        }
//        else {
//            gia.setText(toTheFormat.format(phong.getGiaGio()));
//        }
        for (int i = 0; i < StaticConfig.loaiNgay.size(); i++) {
            if(phong.getMaFB().equals(StaticConfig.loaiNgay.get(i).toString())){
                gia.setText(toTheFormat.format(phong.getGiaNgay()));
            }
        }
        for (int i = 0; i < StaticConfig.loaiGio.size(); i++) {
            if(phong.getMaFB().equals(StaticConfig.loaiGio.get(i).toString())){
                gia.setText(toTheFormat.format(phong.getGiaGio()));
            }
        }

        moTa.setText(phong.getMoTa() + "");

        chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StaticConfig.sXacNhan.equals("phong da thue")) {
                    StaticConfig.mRoomRented.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                PhongDaDat da = ds.getValue(PhongDaDat.class);
                                String maPhong = da.getMaPhong();

                                String[] parts;
                                parts = maPhong.split(" ");
                                for (String w : parts) {
                                    StaticConfig.mDichVu.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (phong.getMaPhong().equals(w)) {
                                                StaticConfig.mathue = da.getMaFB();
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
                Intent intent = new Intent(getContext(), KH_ChiTietPhong.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("chitiet", phong);
                bundle.putFloat("Gia", phong.getGiaNgay());
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
