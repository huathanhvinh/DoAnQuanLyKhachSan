package com.example.doanquanlykhachsan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.model.DangKyDichVu;

import java.util.ArrayList;

public class custom_nhanvientapvu_dangkydichvu extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<DangKyDichVu> data;

    public custom_nhanvientapvu_dangkydichvu(@NonNull Context context, int resource, ArrayList<DangKyDichVu>data) {
        super(context, resource, data);
        this.context= context;
        this.resource = resource;
        this.data =data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);
        TextView tvtenDichVu = convertView.findViewById(R.id.tvtenDichVu);
        Button btnXemPhong = convertView.findViewById(R.id.btnXemPhong);

        DangKyDichVu dangKyDichVu = data.get(position);
        tvtenDichVu.setText(dangKyDichVu.getTenDichVu());
        btnXemPhong.setText(dangKyDichVu.getXemPhong());


        return convertView;
    }
}
