package com.example.doanquanlykhachsan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.model.nvtv_qlphong;

import java.util.ArrayList;

public class custom_nhanvientapvu_qlphong extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<nvtv_qlphong> data;
    public custom_nhanvientapvu_qlphong(@NonNull Context context, int resource, ArrayList<nvtv_qlphong> data) {
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
        convertView = LayoutInflater.from(context).inflate(resource,null);
        TextView tvPhong = convertView.findViewById(R.id.tvPhong);
        Button btnKiemTra = convertView.findViewById(R.id.btnKiemTra);

        nvtv_qlphong qlphong = data.get(position);
        tvPhong.setText(qlphong.getPhong());
        btnKiemTra.setText(qlphong.getKiemtra());




        return convertView;

    }
}
