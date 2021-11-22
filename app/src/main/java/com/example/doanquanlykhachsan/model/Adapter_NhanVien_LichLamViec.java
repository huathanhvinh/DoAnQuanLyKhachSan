package com.example.doanquanlykhachsan.model;

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

import com.example.doanquanlykhachsan.admin.AD_LichLamViecCuaNhanVien;
import com.example.doanquanlykhachsan.R;

import java.util.ArrayList;

public class Adapter_NhanVien_LichLamViec extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<NhanVien_LichLamViec> nhanVien_lichLamViec;

    public Adapter_NhanVien_LichLamViec(@NonNull Context context, int resource, ArrayList<NhanVien_LichLamViec> nhanVien_lichLamViec) {
        super(context, resource, nhanVien_lichLamViec);
        this.context = context;
        this.resource = resource;
        this.nhanVien_lichLamViec = nhanVien_lichLamViec;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);

        TextView maNV = convertView.findViewById(R.id.tvMaNVLV);
        TextView tenNV = convertView.findViewById(R.id.tvTenNVLV);
        TextView calam = convertView.findViewById(R.id.tvCalam);
        Button btnChiTiet = convertView.findViewById(R.id.btnChiTietViecLam);

        NhanVien_LichLamViec nv = nhanVien_lichLamViec.get(position);
        maNV.setText("NV100"+nv.getStt());
        tenNV.setText(nv.getTenNV());
        calam.setText(nv.getCaLam());


        btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AD_LichLamViecCuaNhanVien.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ThongTinLLVNhanVien", nv);
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return nhanVien_lichLamViec.size();
    }
}
