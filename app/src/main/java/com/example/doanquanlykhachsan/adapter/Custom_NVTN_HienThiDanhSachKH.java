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

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.model.*;
import com.example.doanquanlykhachsan.nhanvien_thungan.NVTN_Thongtinkhachhang;

import java.util.ArrayList;

public class Custom_NVTN_HienThiDanhSachKH extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<KhachHang>arrKH;
    public Custom_NVTN_HienThiDanhSachKH(@NonNull Context context, int resource,ArrayList<KhachHang>arrKH) {
        super(context, resource, arrKH);
        this.context=context;
        this.resource=resource;
        this.arrKH=arrKH;
    }

    @Override
    public int getCount() {
        return arrKH.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);

        TextView tvMaKH = convertView.findViewById(R.id.tvMaKH);
        TextView tvTenKH = convertView.findViewById(R.id.tvTenKH);

        Button btnXemTT = convertView.findViewById(R.id.btnXemTT);

        KhachHang nvtn_khachkhang = arrKH.get(position);
        tvMaKH.setText("KH"+nvtn_khachkhang.getStt());
        tvTenKH.setText(nvtn_khachkhang.getTenKH());
        //
        btnXemTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NVTN_Thongtinkhachhang.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ThongTinKhachHang", nvtn_khachkhang);
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return  convertView;
    }
}
