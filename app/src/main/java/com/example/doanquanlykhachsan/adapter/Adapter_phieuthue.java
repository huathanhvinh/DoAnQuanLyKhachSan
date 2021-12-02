package com.example.doanquanlykhachsan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.model.Phong;

import java.util.ArrayList;

public class Adapter_phieuthue extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Phong> data;

    public Adapter_phieuthue(@NonNull Context context, int resource, ArrayList<Phong> data) {
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
        Button btnPhong = convertView.findViewById(R.id.sophong);
        Phong p = data.get(position);
        btnPhong.setText(p.getSoPhong());
        btnPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), p.getTenPhong(), Toast.LENGTH_SHORT).show();
            }
        });
        if(p.getTrangThai().equals("Trống")){
            btnPhong.setBackgroundColor(Color.parseColor("#2DE155"));
        }
        if(p.getTrangThai().equals("Đã Đặt Phòng")){
            btnPhong.setBackgroundColor(Color.parseColor("#e12d2d"));
        }
        if(p.getTrangThai().equals("Chưa xử lý")){
            btnPhong.setBackgroundColor(Color.parseColor("#eee627"));
        }
        if(p.getTrangThai().equals("Trả Phòng")){
            btnPhong.setBackgroundColor(Color.parseColor("#0602b2"));
        }

        return convertView;
    }
}
