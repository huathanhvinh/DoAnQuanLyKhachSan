package com.example.doanquanlykhachsan.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.R;

import java.util.ArrayList;

public class Adapter_NhanVien extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<NhanVien> listNhanVien;

    public Adapter_NhanVien(@NonNull Context context, int resource, ArrayList<NhanVien> listNhanVien) {
        super(context, resource, listNhanVien);
        this.context = context;
        this.resource = resource;
        this.listNhanVien = listNhanVien;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);

        TextView maNV = convertView.findViewById(R.id.maNV);
        TextView tenNV = convertView.findViewById(R.id.tenNV);

        NhanVien nv = listNhanVien.get(position);
        maNV.setText("NV100"+nv.getStt());
        tenNV.setText(nv.getTenNV());
        return convertView;
    }

    @Override
    public int getCount() {
        return listNhanVien.size();
    }

}
