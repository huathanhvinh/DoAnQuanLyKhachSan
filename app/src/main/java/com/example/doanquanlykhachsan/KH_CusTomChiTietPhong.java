package com.example.doanquanlykhachsan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Dichvu;
import com.example.doanquanlykhachsan.model.Room;

import java.util.ArrayList;

public class KH_CusTomChiTietPhong extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Room> data;

    public KH_CusTomChiTietPhong(@NonNull Context context, int resource, ArrayList<Room> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        TextView tvTenPhong = convertView.findViewById(R.id.tvTenPhong);
        TextView tvLau = convertView.findViewById(R.id.tvLau);
        TextView tvLoai = convertView.findViewById(R.id.tvLoai);
        TextView tvSoLuong = convertView.findViewById(R.id.tvSoLuong);
        TextView tvGiaGio = convertView.findViewById(R.id.tvGiaGio);

        Room room = data.get(position);

        tvTenPhong.setText(room.getTen());
        tvLau.setText(room.getSophong() + "");
        tvLoai.setText(room.getLoai() + "");
        tvSoLuong.setText(room.getSoluong() + "");
        tvGiaGio.setText(String.valueOf(room.getGiagio()));


        return convertView;
    }
}
