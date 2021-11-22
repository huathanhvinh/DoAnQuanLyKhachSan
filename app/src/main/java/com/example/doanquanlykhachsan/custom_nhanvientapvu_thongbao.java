package com.example.doanquanlykhachsan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.model.ThongBao;

import java.util.ArrayList;

public class custom_nhanvientapvu_thongbao extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ThongBao> data;
    public custom_nhanvientapvu_thongbao(@NonNull Context context, int resource, ArrayList<ThongBao> data) {
        super(context, resource, data);
        this.context=context;
        this.resource = resource;
        this.data= data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);

        TextView tvID = convertView.findViewById(R.id.tvID);
        TextView tvNoiDung = convertView.findViewById(R.id.tvNoiDung);
        TextView tvNgay = convertView.findViewById(R.id.tvNgay);
        TextView tvGio = convertView.findViewById(R.id.tvGio);
        TextView tvDangKyDichVu = convertView.findViewById(R.id.tvDangKyDichVu);
        Button btnChiTiet = convertView.findViewById(R.id.btnChiTiet);
        Button btnXacNhan = convertView.findViewById(R.id.btnXacNhan);

        ThongBao thongbao =  data.get(position);
        tvID.setText(thongbao.getID());
        tvNoiDung.setText(thongbao.getNoiDung());
        tvGio.setText(thongbao.getGio());
        tvNgay.setText(thongbao.getNgay());
        tvDangKyDichVu.setText(thongbao.getDangKyDichVu());
        btnChiTiet.setText(thongbao.getChiTiet());
        btnXacNhan.setText(thongbao.getXacNhan());

        return convertView;
    }
}
