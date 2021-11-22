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
import com.example.doanquanlykhachsan.model.HoaDon;
import com.example.doanquanlykhachsan.nhanvien_letan.NVTN_XacNhanHoaDon;

import java.util.ArrayList;

public class Custom_NVTN_LapHoaDon extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<HoaDon>arrHoaDon;
    public Custom_NVTN_LapHoaDon(@NonNull Context context, int resource, @NonNull ArrayList<HoaDon>arrHoaDon) {
        super(context, resource, arrHoaDon);
        this.context = context;
        this.resource =resource;
        this.arrHoaDon = arrHoaDon;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);
        TextView tvTenKH = convertView.findViewById(R.id.tvTenKH);
        Button btnLapHD = convertView.findViewById(R.id.btnLapHD);

        HoaDon laphoadon = arrHoaDon.get(position);
        tvTenKH.setText(laphoadon.getTenKH());
        //
        btnLapHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NVTN_XacNhanHoaDon.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("TenKH", laphoadon);
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public int getCount() {
        return arrHoaDon.size();
    }
}
