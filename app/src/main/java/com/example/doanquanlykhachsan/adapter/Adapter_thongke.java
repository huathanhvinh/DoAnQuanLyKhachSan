package com.example.doanquanlykhachsan.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.HoaDon;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_thongke extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<HoaDon> data;

    public Adapter_thongke(@NonNull Context context, int resource, ArrayList<HoaDon> data) {
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
        TextView maHD = convertView.findViewById(R.id.tvMaHoaDon2);
        TextView thoiGianDen = convertView.findViewById(R.id.tvNgayDen2);
        TextView thoiGianDi = convertView.findViewById(R.id.tvNgayDi2);
        TextView tenNhanVien = convertView.findViewById(R.id.tvNhanVienThu2);
        TextView tongTien = convertView.findViewById(R.id.tvTongTien2);

        HoaDon hd = data.get(position);
        maHD.setText(hd.getNgaylap());
        thoiGianDen.setText(hd.getNgaylap());
        thoiGianDi.setText(hd.getNgaylap());

        StaticConfig.mNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    NhanVien nv = ds.getValue(NhanVien.class);
                    if (nv.getMaFB().equals(hd.getMaNV())) {
                        tenNhanVien.setText(nv.getTenNV());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DecimalFormat toTheFormat = new DecimalFormat("###,###,###.#");
        tongTien.setText(toTheFormat.format(hd.getTongTien()) + " VNĐ");

        return convertView;
    }

}
