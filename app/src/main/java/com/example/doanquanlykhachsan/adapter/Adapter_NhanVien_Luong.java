package com.example.doanquanlykhachsan.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.admin.AD_LuongCuaNhanVien;
import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.model.NhanVien_Luong;

import java.util.ArrayList;

public class Adapter_NhanVien_Luong extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<NhanVien_Luong> listNhanVien_luong;

    public Adapter_NhanVien_Luong(@NonNull Context context, int resource, ArrayList<NhanVien_Luong> listNhanVien_luong) {
        super(context, resource, listNhanVien_luong);
        this.context = context;
        this.resource = resource;
        this.listNhanVien_luong = listNhanVien_luong;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);

        TextView maNV = convertView.findViewById(R.id.tvMaNV);
        TextView tenNV = convertView.findViewById(R.id.tvTenNV);
        TextView luongNV = convertView.findViewById(R.id.tvLuongNV);
        Button btnChiTiet = convertView.findViewById(R.id.btnChiTietLuong);

        NhanVien_Luong nv_l = listNhanVien_luong.get(position);
        maNV.setText("NV100"+nv_l.getStt());
        tenNV.setText(nv_l.getTenNV());
        luongNV.setText(nv_l.getLuong());

        //
        btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AD_LuongCuaNhanVien.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ThongTinLuongNhanVien", nv_l);
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public int getCount() {
        return listNhanVien_luong.size();
    }
}
