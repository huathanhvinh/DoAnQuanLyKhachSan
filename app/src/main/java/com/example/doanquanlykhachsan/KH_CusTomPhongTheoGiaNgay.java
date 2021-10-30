package com.example.doanquanlykhachsan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Room;

import java.util.ArrayList;

public class KH_CusTomPhongTheoGiaNgay extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<Room> data;

    public KH_CusTomPhongTheoGiaNgay(@NonNull Context context, int resource, ArrayList<Room> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        TextView tvTenPhong = convertView.findViewById(R.id.tvTenPhong);
        TextView tvLau = convertView.findViewById(R.id.tvLau);
        TextView tvLoai = convertView.findViewById(R.id.tvLoai);
        TextView tvSoLuong = convertView.findViewById(R.id.tvSoLuong);
        TextView tvGiaNgay = convertView.findViewById(R.id.tvGiaNgay);
        TextView tvChiTiet = convertView.findViewById(R.id.tvChiTiet);
        CheckBox cboNgay = convertView.findViewById(R.id.cboNgay);
//        TextView tvNgayNhanPhong = convertView.findViewById(R.id.tvNgayNhanPhong);
//        TextView tvNgaytraPhong = convertView.findViewById(R.id.tvNgayTraPhong);


        Room room = data.get(position);

        tvTenPhong.setText(room.getTen());
        tvLau.setText(room.getSophong() + "");
        tvLoai.setText(room.getLoai() + "");
        tvSoLuong.setText(room.getSoluong() + "");
        tvGiaNgay.setText(String.valueOf(room.getGiangay()));

        //checkAll
        if (StaticConfig.isCheckAll == true) {
            StaticConfig.arrayListCheckItem.clear();
            cboNgay.setChecked(true);
            StaticConfig.arrayListCheckItem.addAll(data);
        } else {
            cboNgay.setChecked(false);
            StaticConfig.arrayListCheckItem.clear();
        }

        tvChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), KH_ChiTietPhong.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("chitiet", room);
                bundle.putFloat("Gia", room.getGiangay());
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

                //them phong tu chi tiet phong
                StaticConfig.arrayListCheckItem.clear();
                StaticConfig.arrayListCheckItem.add(room);
            }
        });
        cboNgay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cboNgay.isChecked()) {
                    StaticConfig.arrayListCheckItem.add(room);
                } else {
                    StaticConfig.arrayListCheckItem.remove(room);
                }
            }
        });
        return convertView;
    }
}
