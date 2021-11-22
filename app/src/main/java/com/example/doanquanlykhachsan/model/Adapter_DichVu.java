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

import com.example.doanquanlykhachsan.admin.AD_ThongTinDichVu;
import com.example.doanquanlykhachsan.R;

import java.util.ArrayList;

public class Adapter_DichVu extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<DichVu> listDichVu;
    public Adapter_DichVu(@NonNull Context context, int resource,ArrayList<DichVu> listDichVu) {
        super(context, resource, listDichVu);

        this.context =context;
        this.resource = resource;
        this.listDichVu = listDichVu;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);

        TextView tenDV = convertView.findViewById(R.id.tvTenDichVu);
        TextView giaDV = convertView.findViewById(R.id.tvgiaDV);
        TextView dvt = convertView.findViewById(R.id.tvDVT);
        Button btnChiTietDichVu = convertView.findViewById(R.id.btnChiTietDichVu);

        DichVu dv = listDichVu.get(position);

        tenDV.setText(dv.getTenDV());
        giaDV.setText(dv.getGiaDV()+"");
        dvt.setText(dv.getDvt());

        btnChiTietDichVu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AD_ThongTinDichVu.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ThongTinDichVu", dv);
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return listDichVu.size();
    }
}
