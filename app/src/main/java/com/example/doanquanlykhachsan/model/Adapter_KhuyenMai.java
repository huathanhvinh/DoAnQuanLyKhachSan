package com.example.doanquanlykhachsan.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.R;

import java.util.ArrayList;

public class Adapter_KhuyenMai extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<KhuyenMai> listKhuyenMai;

    public Adapter_KhuyenMai(@NonNull Context context, int resource, ArrayList<KhuyenMai> listKhuyenMai) {
        super(context, resource, listKhuyenMai);
        this.context = context;
        this.resource = resource;
        this.listKhuyenMai = listKhuyenMai;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);
        //Ánh xạ
        TextView tvmakm = convertView.findViewById(R.id.tvMaKm);
        TextView tvtenkm = convertView.findViewById(R.id.tvTenKM);
        TextView tvNgayBatDau = convertView.findViewById(R.id.tvNgayBatDau);
        TextView tvNgayKetThuc = convertView.findViewById(R.id.tvNgayKetThuc);
        TextView BtnChiTiet = convertView.findViewById(R.id.btnChiTiet);
        TextView btnKetThuc = convertView.findViewById(R.id.btnKetThucNgay);
        //Set dữ liệu
        KhuyenMai km = listKhuyenMai.get(position);
        tvmakm.setText("Mã khuyến mãi: "+km.getMaKM());
        tvtenkm.setText(km.getTenKM());
        tvNgayBatDau.setText(km.getNgayBatDau());
        tvNgayKetThuc.setText(km.getNgayKetThuc());

        return convertView;
    }

    @Override
    public int getCount() {
        return listKhuyenMai.size();
    }
}
