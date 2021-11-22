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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Phong;

import java.util.ArrayList;

public class KH_CusTomPhongTheoGiaGio extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Phong> data;

    public KH_CusTomPhongTheoGiaGio(@NonNull Context context, int resource, ArrayList<Phong> data) {
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
        TextView tvMoTa  = convertView.findViewById(R.id.tvMoTa);
        TextView tvGiaGio = convertView.findViewById(R.id.tvGiaGio);
        TextView tvChiTiet = convertView.findViewById(R.id.tvChiTiet);
        CheckBox cboGio = convertView.findViewById(R.id.cboGio);

        Phong phong = data.get(position);

        tvTenPhong.setText(phong.getTenPhong());
        tvLau.setText(phong.getLau() + "");
        tvLoai.setText(phong.getLoai() + "");
        tvMoTa.setText(phong.getMoTa() + "");
        tvGiaGio.setText(String.valueOf(phong.getGiaGio()));

        if (StaticConfig.isCheckAll == true) {
            StaticConfig.arrayListTemporaryRoom.clear();
            cboGio.setChecked(true);
            StaticConfig.arrayListTemporaryRoom.addAll(data);
        } else {
            for (int i = 0; i < StaticConfig.arrayListTemporaryRoom.size(); i++) {
                if (StaticConfig.arrayListTemporaryRoom.get(i).getMaFB().equals(phong.getMaFB())) {
                    cboGio.setChecked(true);
                }
                if (StaticConfig.arrayListTemporaryRoom.size() == data.size()) {
                    cboGio.setChecked(false);
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
                bundle.putFloat("Gia", phong.getGiaGio());
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

                StaticConfig.arrayListTemporaryRoom.clear();
                StaticConfig.arrayListTemporaryRoom.add(phong);
            }
        });
        //checkbox tung phong
        cboGio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cboGio.isChecked()) {

                    StaticConfig.arrayListTemporaryRoom.add(phong);
                } else {
                    StaticConfig.arrayListTemporaryRoom.remove(phong);
                }
            }
        });
        return convertView;
    }
}
