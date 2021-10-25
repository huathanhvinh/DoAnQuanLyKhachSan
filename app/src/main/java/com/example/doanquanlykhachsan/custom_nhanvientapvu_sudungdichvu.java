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

import com.example.doanquanlykhachsan.model.SuDungDichVu;

import java.util.ArrayList;

public class custom_nhanvientapvu_sudungdichvu extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<SuDungDichVu> data;
    public custom_nhanvientapvu_sudungdichvu(@NonNull Context context, int resource, ArrayList<SuDungDichVu> data) {
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
        Button btnHuyDV = convertView.findViewById(R.id.btnHuyDV);

        SuDungDichVu suDungDichVu = data.get(position);
        tvPhong.setText(suDungDichVu.getPhong());
        btnHuyDV.setText(suDungDichVu.getHuyDichVu());

        return convertView;
    }
}
