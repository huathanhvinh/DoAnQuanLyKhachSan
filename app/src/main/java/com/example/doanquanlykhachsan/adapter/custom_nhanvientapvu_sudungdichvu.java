package com.example.doanquanlykhachsan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.DangKyDichVu;
import com.example.doanquanlykhachsan.model.DichVuDaChon;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class custom_nhanvientapvu_sudungdichvu extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<DichVuDaChon> data;
    String tenPhong="";
    public custom_nhanvientapvu_sudungdichvu(@NonNull Context context, int resource, ArrayList<DichVuDaChon> data) {
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

        DichVuDaChon suDungDichVu = data.get(position);
        StaticConfig.mRoom.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    if(suDungDichVu.getMaPhong().equals(ds.child("maPhong").getValue().toString())){
                        tenPhong=ds.child("tenPhong").getValue().toString();
                        tvPhong.setText(tenPhong);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnHuyDV.setText("Hủy sử dụng dịch vụ");
        btnHuyDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticConfig.mDichVuDaChon.child(suDungDichVu.getMaFB()).removeValue();
                
            }
        });

        return convertView;
    }
}
