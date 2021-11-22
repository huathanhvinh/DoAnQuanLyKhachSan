package com.example.doanquanlykhachsan.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.khach_hang.KH_ChiTietPhong;
import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Phong;

import java.util.ArrayList;

public class KH_CusTomPhongTheoGiaNgay extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<Phong> data;

    public KH_CusTomPhongTheoGiaNgay(@NonNull Context context, int resource, ArrayList<Phong> data) {
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
        TextView tvTenPhong = convertView.findViewById(R.id.tvTenPhong);
        TextView tvLau = convertView.findViewById(R.id.tvLau);
        TextView tvLoai = convertView.findViewById(R.id.tvLoai);
        TextView tvMoTa = convertView.findViewById(R.id.tvMoTa);
        TextView tvGiaNgay = convertView.findViewById(R.id.tvGiaNgay);
        TextView tvChiTiet = convertView.findViewById(R.id.tvChiTiet);
        CheckBox cboNgay = convertView.findViewById(R.id.cboNgay);
//        TextView tvNgayNhanPhong = convertView.findViewById(R.id.tvNgayNhanPhong);
//        TextView tvNgaytraPhong = convertView.findViewById(R.id.tvNgayTraPhong);
        Phong phong = data.get(position);

        tvTenPhong.setText(phong.getTenPhong());
        tvLau.setText(phong.getLau() + "");
        tvLoai.setText(phong.getLoai() + "");
        tvMoTa.setText(phong.getMoTa() + "");
        tvGiaNgay.setText(String.valueOf(phong.getGiaNgay()));


        //checkAll
        if (StaticConfig.isCheckAll == true) {
            StaticConfig.arrayListTemporaryRoom.clear();
            cboNgay.setChecked(true);
            StaticConfig.arrayListTemporaryRoom.addAll(data);
        } else {
            for (int i = 0; i < StaticConfig.arrayListTemporaryRoom.size(); i++) {
                if (StaticConfig.arrayListTemporaryRoom.get(i).getMaFB().equals(phong.getMaFB())) {
                    cboNgay.setChecked(true);
                }
                if (StaticConfig.arrayListTemporaryRoom.size() == data.size()) {
                    cboNgay.setChecked(false);
                    StaticConfig.arrayListTemporaryRoom.clear();
                    break;
                }
            }

        }

        tvChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KH_ChiTietPhong.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("chitiet", phong);
                bundle.putFloat("Gia", phong.getGiaNgay());
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

                //them phong tu chi tiet phong
                StaticConfig.arrayListTemporaryRoom.clear();
                StaticConfig.arrayListTemporaryRoom.add(phong);

            }
        });
        //checkbox tung phong
        cboNgay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cboNgay.isChecked()) {
                    StaticConfig.arrayListTemporaryRoom.add(phong);
                } else {
                    StaticConfig.arrayListTemporaryRoom.remove(phong);
                }
            }
        });
        return convertView;
    }
}
