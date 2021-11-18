package com.example.doanquanlykhachsan;

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

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.DangKyDichVu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class custom_nhanvientapvu_dangkydichvu extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<DangKyDichVu> data;

    public custom_nhanvientapvu_dangkydichvu(@NonNull Context context, int resource, ArrayList<DangKyDichVu>data) {
        super(context, resource, data);
        this.context= context;
        this.resource = resource;
        this.data =data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);
        TextView tvtenDichVu = convertView.findViewById(R.id.tvtenDichVu);
        Button btnXemPhong = convertView.findViewById(R.id.btnXemPhong);


        DangKyDichVu dangKyDichVu = data.get(position);
        tvtenDichVu.setText(dangKyDichVu.getTenDichVu());
        btnXemPhong.setText(dangKyDichVu.getXemPhong());

        btnXemPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), nhanvientapvu_sudungdichvu.class);//Chuyen man hinh
                Bundle bundle = new Bundle();//Tạo bundle
                StaticConfig.mDichVu.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds:snapshot.getChildren()){
                            if (dangKyDichVu.getTenDichVu().equals(ds.child("tenDV").getValue().toString())){
                                bundle.putString("XemPhong",ds.child("maFB").getValue().toString());//Gán giá trị bên màn hình kia
                                intent.putExtras(bundle);
                                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);//Qua màn khác
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        return convertView;
    }
}
