package com.example.doanquanlykhachsan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.DichVu;
import com.example.doanquanlykhachsan.model.Phong;

import java.util.ArrayList;

public class Adapter_HD_DV extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<DichVu> data;
    DichVu p;

    public Adapter_HD_DV(@NonNull Context context, int resource, ArrayList<DichVu> data) {
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
        TextView tenDV = convertView.findViewById(R.id.tvTenDichVu);
        TextView tien = convertView.findViewById(R.id.tvtien);
        TextView solan = convertView.findViewById(R.id.tvsolan);

        p = data.get(position);
        tenDV.setText(p.getTenDV());
        tien.setText(p.getGiaDV() + "");
        solan.setText(StaticConfig.songay + " Lần");
        return convertView;
    }

}
