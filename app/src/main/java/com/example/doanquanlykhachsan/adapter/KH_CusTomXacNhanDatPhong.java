package com.example.doanquanlykhachsan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Phong;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class KH_CusTomXacNhanDatPhong extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Phong> data;

    public KH_CusTomXacNhanDatPhong(@NonNull Context context, int resource, ArrayList<Phong> data) {
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
        TextView tvGia = convertView.findViewById(R.id.tvGia);

        Phong phong = data.get(position);

        tvTenPhong.setText(phong.getTenPhong());
        tvLau.setText(phong.getLau() + "");
        tvLoai.setText(phong.getLoai() + "");
        tvMoTa.setText(phong.getMoTa() + "");
        DecimalFormat toTheFormat = new DecimalFormat("###,###,###.#");
        if (StaticConfig.sXacNhan.equals("ngay")) {
            tvGia.setText(toTheFormat.format(phong.getGiaNgay())+" VNĐ");
        } else {
            tvGia.setText(toTheFormat.format(phong.getGiaGio())+" VNĐ");
        }

        return convertView;
    }
}
