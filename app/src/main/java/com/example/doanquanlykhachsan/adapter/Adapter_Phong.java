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

import com.example.doanquanlykhachsan.admin.AD_ThongTinPhong;
import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.model.Phong;

import java.util.ArrayList;

public class Adapter_Phong extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Phong> arrPhong;
    public Adapter_Phong(@NonNull Context context, int resource, ArrayList<Phong> arrPhong) {
        super(context, resource, arrPhong);
        this.context = context;
        this.resource = resource;
        this.arrPhong = arrPhong;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);

        TextView tvMaPhong = convertView.findViewById(R.id.tvMaPhong);
        TextView tvTenPhong = convertView.findViewById(R.id.tvTenPhong);
        Button btnChiTiet = convertView.findViewById(R.id.btnChiTietPhong);

        Phong p = arrPhong.get(position);

        tvMaPhong.setText(p.getMaPhong());
        tvTenPhong.setText(p.getTenPhong());

        btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AD_ThongTinPhong.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ThongTinPhong", p);
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public int getCount() {
        return arrPhong.size();
    }
}
