package com.example.doanquanlykhachsan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.model.NVTN_HienThiDSKH;

import java.util.ArrayList;

public class Custom_NVTN_HienThiDanhSachKH extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<NVTN_HienThiDSKH>data;
    public Custom_NVTN_HienThiDanhSachKH(@NonNull Context context, int resource,ArrayList<NVTN_HienThiDSKH>data) {
        super(context, resource, data);
        this.context=context;
        this.resource=resource;
        this.data=data;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);

        TextView tvMaKH = convertView.findViewById(R.id.tvMaKH);
        TextView tvTenKH = convertView.findViewById(R.id.tvTenKH);

        NVTN_HienThiDSKH nvtn_khachkhang = data.get(position);
        tvMaKH.setText(nvtn_khachkhang.getMaKH());
        tvTenKH.setText(nvtn_khachkhang.getTenKH());
        return  convertView;
    }
}
