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
import com.example.doanquanlykhachsan.model.PhongDaDat;
import com.example.doanquanlykhachsan.model.ThongBao;

import java.util.ArrayList;

public class Apapter_thongbao_kh extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ThongBao> data;

    public Apapter_thongbao_kh(@NonNull Context context, int resource, ArrayList<ThongBao> data) {
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
        TextView stt = convertView.findViewById(R.id.stt);
        TextView loai = convertView.findViewById(R.id.nloai);
        TextView tennv = convertView.findViewById(R.id.hoten);
        Button btnxacnhan = convertView.findViewById(R.id.btnxacnhan);

        ThongBao tb = data.get(position);
        stt.setText(tb.getStt()+"");
        loai.setText(tb.getLoai());
        tennv.setText(tb.getNguoidang());
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticConfig.mThongBao.child(tb.getMaFB()).child("trangThai").setValue("Đã xác nhận");
            }
        });

        return convertView;
    }
}
