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
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.KhachHang;
import com.example.doanquanlykhachsan.model.PhongDaDat;
import com.example.doanquanlykhachsan.nhanvien_thungan.NVTN_XacNhanHoaDon;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class custom_NVTN_thongbao_traPhong extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<PhongDaDat> data;

    public custom_NVTN_thongbao_traPhong(@NonNull Context context, int resource, ArrayList<PhongDaDat> data) {
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
        TextView hoten = convertView.findViewById(R.id.hoten);
        TextView ngay = convertView.findViewById(R.id.ngay);
        Button btnChiTiet = convertView.findViewById(R.id.btnChiTiet);
        Button btnXacNhan = convertView.findViewById(R.id.btnxacnhan);

        PhongDaDat thongbao = data.get(position);
        stt.setText(thongbao.getStt()+"");
        StaticConfig.mKhachHang.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    KhachHang u = ds.getValue(KhachHang.class);
                    if (thongbao.getMaKH().equals(u.getMaFB())) {
                        hoten.setText(u.getTenKH());
                        ngay.setText(thongbao.getThoiGianNhanPH());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NVTN_XacNhanHoaDon.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("chitiet", thongbao);
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
