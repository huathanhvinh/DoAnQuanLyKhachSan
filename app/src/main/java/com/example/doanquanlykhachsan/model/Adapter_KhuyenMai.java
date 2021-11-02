package com.example.doanquanlykhachsan.model;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.doanquanlykhachsan.AD_HienThiDanhSachKhuyenMai;
import com.example.doanquanlykhachsan.AD_SuaKhuyenMai;
import com.example.doanquanlykhachsan.AD_ThongTinKhuyenMai;
import com.example.doanquanlykhachsan.AD_ThongTinNhanVien;
import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Adapter_KhuyenMai extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<KhuyenMai> listKhuyenMai;

    public Adapter_KhuyenMai(@NonNull Context context, int resource, ArrayList<KhuyenMai> listKhuyenMai) {
        super(context, resource, listKhuyenMai);
        this.context = context;
        this.resource = resource;
        this.listKhuyenMai = listKhuyenMai;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);
        //Ánh xạ
        TextView tvmakm = convertView.findViewById(R.id.tvMaKm);
        TextView tvtenkm = convertView.findViewById(R.id.tvTenKM);
        TextView tvNgayBatDau = convertView.findViewById(R.id.tvNgayBatDau);
        TextView tvNgayKetThuc = convertView.findViewById(R.id.tvNgayKetThuc);
        TextView btnChiTiet = convertView.findViewById(R.id.btnChiTiet);
        TextView btnKetThuc = convertView.findViewById(R.id.btnKetThucNgay);
        //Set dữ liệu
        KhuyenMai km = listKhuyenMai.get(position);
        tvmakm.setText("Mã khuyến mãi: "+km.getMaKM());
        tvtenkm.setText(km.getTenKM());
        tvNgayBatDau.setText(km.getNgayBatDau());
        tvNgayKetThuc.setText(km.getNgayKetThuc());

        btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AD_ThongTinKhuyenMai.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ThongTinKhuyenMai", km);
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        btnKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                SimpleDateFormat ngayHienTai = new SimpleDateFormat("dd/MM/yyyy");
                tvNgayBatDau.setText(ngayHienTai.format(date.getTime()));
                tvNgayKetThuc.setText(ngayHienTai.format(date.getTime()));
                km.setNgayBatDau(ngayHienTai.format(date.getTime()));
                km.setNgayKetThuc(ngayHienTai.format(date.getTime()));
                StaticConfig.mKhuyenMai.child(km.getMaFB()).setValue(km);
                Toast.makeText(getContext(), "Kết thúc khuyến mãi thành công !", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return listKhuyenMai.size();
    }
}
