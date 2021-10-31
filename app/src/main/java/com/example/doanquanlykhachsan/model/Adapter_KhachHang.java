package com.example.doanquanlykhachsan.model;

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

import com.example.doanquanlykhachsan.AD_ThongTinKhachHang;
import com.example.doanquanlykhachsan.AD_ThongTinNhanVien;
import com.example.doanquanlykhachsan.R;

import java.util.ArrayList;

public class Adapter_KhachHang extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<KhachHang> listKhachHang;
    public Adapter_KhachHang(@NonNull Context context, int resource, ArrayList<KhachHang> listKhachHang) {
        super(context, resource, listKhachHang);
        this.context = context;
        this.resource = resource;
        this.listKhachHang = listKhachHang;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);

        TextView maKH = convertView.findViewById(R.id.maKH);
        TextView tenKH = convertView.findViewById(R.id.tenKH);
        Button btnChiTiet = convertView.findViewById(R.id.btnChiTietKhachHang);

        KhachHang kh = listKhachHang.get(position);
        maKH.setText("KH100"+kh.getStt());
        tenKH.setText(kh.getTenKH());

        btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AD_ThongTinKhachHang.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ThongTinKhachHang", kh);
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return listKhachHang.size();
    }
}
