package com.example.doanquanlykhachsan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.model.Room;

import java.util.ArrayList;

public class Phong_adapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Room> data;

    public Phong_adapter(@NonNull Context context, int resource, ArrayList<Room> data) {
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
        TextView soluong = convertView.findViewById(R.id.tvSoLuong);
        TextView chitiet = convertView.findViewById(R.id.tvChiTiet);

        Room room = data.get(position);
        tenphong.setText(room.getTen());
        int sophong = room.getSophong();
        if (sophong < 21) {
            solau.setText("1");
        } else {
            solau.setText("2");
        }
        loai.setText(room.getLoai());
        gia.setText(room.getGiangay() + "");
        soluong.setText(room.getSoluong() + "");

        chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KH_doi_phong.class);
                Bundle bundle = new Bundle();
                bundle.putString("idroom", room.getMa());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
