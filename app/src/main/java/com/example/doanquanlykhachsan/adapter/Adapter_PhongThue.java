package com.example.doanquanlykhachsan.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.khach_hang.KH_tra_phong;
import com.example.doanquanlykhachsan.model.Phong;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Stack;

public class Adapter_PhongThue extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Phong> data;
    Phong p;

    public Adapter_PhongThue(@NonNull Context context, int resource, ArrayList<Phong> data) {
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
        TextView tenPhong = convertView.findViewById(R.id.tvPhong);
        TextView tien = convertView.findViewById(R.id.tvThanhTien);
        TextView songay = convertView.findViewById(R.id.tvTGThue);

        p = data.get(position);
        tenPhong.setText(p.getTenPhong());
        DecimalFormat toTheFormat = new DecimalFormat("###,###,###.#");

        if (StaticConfig.Loai.equals("ngay")) {
            tien.setText(toTheFormat.format(p.getGiaNgay()));
            songay.setText(StaticConfig.songay + " Ngày");
        } else {
            tien.setText(toTheFormat.format(p.getGiaGio()));
            songay.setText(StaticConfig.songay + " Giờ");
        }


        return convertView;
    }

}
