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
import com.example.doanquanlykhachsan.model.KhuyenMai;

import java.util.ArrayList;

public class Khuyenmai_adapter extends ArrayAdapter {
    @NonNull
    Context context;
    int resource;
    ArrayList<KhuyenMai> data;

    public Khuyenmai_adapter(@NonNull Context context, int resource, ArrayList<KhuyenMai> data) {
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
        TextView ma = convertView.findViewById(R.id.ma);
        TextView tieude = convertView.findViewById(R.id.tieude);
        TextView ngaybatdau = convertView.findViewById(R.id.ngaybatdau);
        TextView ngayketthuc = convertView.findViewById(R.id.ngayketthuc);

        KhuyenMai km= data.get(position);
        ma.setText(km.getMaKM());
        tieude.setText(km.getTenKM());
        ngaybatdau.setText(km.getNgayBatDau());
        ngayketthuc.setText(km.getNgayKetThuc());

        return convertView;
    }
}
