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

import com.example.doanquanlykhachsan.admin.AD_ThongTinNhanVien;
import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.model.NhanVien;

import java.util.ArrayList;

public class Adapter_NhanVien extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<NhanVien> listNhanVien;

    public Adapter_NhanVien(@NonNull Context context, int resource, ArrayList<NhanVien> listNhanVien) {
        super(context, resource, listNhanVien);
        this.context = context;
        this.resource = resource;
        this.listNhanVien = listNhanVien;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);

        TextView maNV = convertView.findViewById(R.id.maNV);
        TextView tenNV = convertView.findViewById(R.id.tenNV);
        Button btnChiTiet = convertView.findViewById(R.id.btnChiTietNhanVien);

        NhanVien nv = listNhanVien.get(position);
        maNV.setText("NV100"+nv.getStt());
        tenNV.setText(nv.getTenNV());

        //
        btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AD_ThongTinNhanVien.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ThongTinNhanVien", nv);
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public int getCount() {
        return listNhanVien.size();
    }

}
