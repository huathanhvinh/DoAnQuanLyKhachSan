package com.example.doanquanlykhachsan.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.khach_hang.KH_doi_phong;
import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.model.Phong;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Doi_Phong_adapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Phong> data;

    public Doi_Phong_adapter(@NonNull Context context, int resource, ArrayList<Phong> data) {
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
        TextView tenphong = convertView.findViewById(R.id.tvTenPhong);
        TextView solau = convertView.findViewById(R.id.tvLau);
        TextView loai = convertView.findViewById(R.id.tvLoai);
        TextView gia = convertView.findViewById(R.id.tvGiaNgay);
        TextView moTa = convertView.findViewById(R.id.tvMoTa);
        TextView chitiet = convertView.findViewById(R.id.tvChiTiet);

        Phong phong = data.get(position);
        tenphong.setText(phong.getTenPhong());
        solau.setText(phong.getLau()+"");
        loai.setText(phong.getLoai());
        DecimalFormat toTheFormat = new DecimalFormat("###,###,###.#");
        if(StaticConfig.Loai.equals("ngay")) {
            gia.setText(toTheFormat.format(phong.getGiaNgay()));
        }
        if(StaticConfig.Loai.equals("gio")) {
            gia.setText(toTheFormat.format(phong.getGiaGio()));
        }

        moTa.setText(phong.getSoPhong() + "");

        chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KH_doi_phong.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("chitiet", phong);
                bundle.putFloat("Gia", phong.getGiaNgay());
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
