package com.example.doanquanlykhachsan.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Phong;
import com.example.doanquanlykhachsan.nhanvien_thungan.NVTN_chitietphong;
import com.example.doanquanlykhachsan.nhanvien_thungan.NV_chonphonglapphieuthue;

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
                if (p.getTrangThai().equals("Trống")) {
                    if (StaticConfig.arrayListTemporaryRoom.size() == 0) {
                        StaticConfig.arrayListTemporaryRoom.add(p);
                    }
                    for (int i = 0; i < StaticConfig.arrayListTemporaryRoom.size(); i++) {
                        if (!p.getMaFB().equals(StaticConfig.arrayListTemporaryRoom.get(i).getMaFB())) {
                            StaticConfig.arrayListTemporaryRoom.add(p);
                        }
                    }
                    Intent intent = new Intent(getContext(), NV_chonphonglapphieuthue.class);//Chuyen man hinh
                    Bundle bundle = new Bundle();//Tạo bundle
                    bundle.putSerializable("Room", p);//Gán giá trị bên màn hình kia
                    intent.putExtras(bundle);
                    intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);//Qua màn khác
                }
            }
        });
        if (p.getTrangThai().equals("Trống")) {
            btnPhong.setBackgroundColor(Color.parseColor("#2DE155"));
        }
        if (p.getTrangThai().equals("Đã Đặt Phòng")) {
            btnPhong.setBackgroundColor(Color.parseColor("#e12d2d"));
        }
        if (p.getTrangThai().equals("Chưa xử lý")) {
            btnPhong.setBackgroundColor(Color.parseColor("#eee627"));
        }
        if (p.getTrangThai().equals("Trả Phòng")) {
            btnPhong.setBackgroundColor(Color.parseColor("#0602b2"));
        }
        if (p.getTrangThai().equals("Bảo Trì")) {
            btnPhong.setBackgroundColor(Color.parseColor("#0602b2"));
        }
        if (p.getTrangThai().equals("Chưa Dọn")) {
            btnPhong.setBackgroundColor(Color.parseColor("#e1842d"));
        }
        for (int i = 0; i < StaticConfig.arrayListTemporaryRoom.size(); i++) {
            if(p.getMaFB().equals(StaticConfig.arrayListTemporaryRoom.get(i).getMaFB())){
                btnPhong.setTextColor(Color.RED);
            }
        }

        return convertView;
    }
}
