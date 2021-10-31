package com.example.doanquanlykhachsan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Room;

import java.util.ArrayList;

public class KH_CusTomPhongTheoGiaGio extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Room> data;

    public KH_CusTomPhongTheoGiaGio(@NonNull Context context, int resource, ArrayList<Room> data) {
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
        TextView tvGiaGio = convertView.findViewById(R.id.tvGiaGio);
        TextView tvChiTiet = convertView.findViewById(R.id.tvChiTiet);
        CheckBox cboGio = convertView.findViewById(R.id.cboGio);

        Room room = data.get(position);

        tvTenPhong.setText(room.getTen());
        tvLau.setText(room.getSophong() + "");
        tvLoai.setText(room.getLoai() + "");
        tvSoLuong.setText(room.getSoluong() + "");
        tvGiaGio.setText(String.valueOf(room.getGiagio()));

        if (StaticConfig.isCheckAll == true) {
            StaticConfig.arrayListCheckItem.clear();
            cboGio.setChecked(true);
            StaticConfig.arrayListCheckItem.addAll(data);
        } else {
            for (int i = 0; i < StaticConfig.arrayListCheckItem.size(); i++) {
                if (StaticConfig.arrayListCheckItem.get(i).getMa().equals(room.getMa())){
                    cboGio.setChecked(true);
                }
                if(StaticConfig.arrayListCheckItem.size()==data.size()){
                    cboGio.setChecked(false);
                    StaticConfig.arrayListCheckItem.clear();
                    break;
                }
            }

        }

        tvChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), KH_ChiTietPhong.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("chitiet", room);
                bundle.putFloat("Gia", room.getGiagio());
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

                StaticConfig.arrayListCheckItem.clear();
                StaticConfig.arrayListCheckItem.add(room);
            }
        });


        cboGio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cboGio.isChecked()) {

                    StaticConfig.arrayListCheckItem.add(room);
                } else {
                    StaticConfig.arrayListCheckItem.remove(room);
                }
            }
        });
        return convertView;
    }
}
