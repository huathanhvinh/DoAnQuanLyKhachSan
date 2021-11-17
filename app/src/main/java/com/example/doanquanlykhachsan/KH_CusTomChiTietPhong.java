package com.example.doanquanlykhachsan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.model.Phong;

import java.util.ArrayList;

public class KH_CusTomChiTietPhong extends ArrayAdapter{
    Context context;
    int resource;
    ArrayList<Phong> data;

    public KH_CusTomChiTietPhong(@NonNull Context context, int resource, ArrayList<Phong> data) {
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
        TextView tvMoTa = convertView.findViewById(R.id.tvMoTa);
        TextView tvGiaGio = convertView.findViewById(R.id.tvGiaGio);

        Phong phong = data.get(position);

        tvTenPhong.setText(phong.getTenPhong());
        tvLau.setText(phong.getLau() + "");
        tvLoai.setText(phong.getLoai() + "");
        tvMoTa.setText(phong.getMoTa() + "");
        tvGiaGio.setText(String.valueOf(phong.getGiaGio()));


        return convertView;
    }
}
