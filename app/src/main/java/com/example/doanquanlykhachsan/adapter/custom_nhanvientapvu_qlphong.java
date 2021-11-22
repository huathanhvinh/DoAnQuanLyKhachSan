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
import com.example.doanquanlykhachsan.model.Phong;
import com.example.doanquanlykhachsan.nhanvien_tapvu.nhanvientapvu_xacnhantrangthaiphong;

import java.util.ArrayList;

public class custom_nhanvientapvu_qlphong extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<Phong> data;
    //ListView lvQuanLyPhong;
    public custom_nhanvientapvu_qlphong(@NonNull Context context, int resource, ArrayList<Phong> data) {
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
        convertView = LayoutInflater.from(context).inflate(resource,null);
        TextView tvPhong = convertView.findViewById(R.id.tvPhong);
        Button btnKiemTra = convertView.findViewById(R.id.btnKiemTra);

        //ListView lvQuanLyPhong = convertView.findViewById(R.id.lvQuanLyPhong);

        Phong room = data.get(position);
        tvPhong.setText(room.getTenPhong());

       btnKiemTra.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               //Toast.makeText(getContext(), room.getTen(), Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(getContext(), nhanvientapvu_xacnhantrangthaiphong.class);//Chuyen man hinh
               Bundle bundle = new Bundle();//Tạo bundle
               bundle.putSerializable("Room", room);//Gán giá trị bên màn hình kia
               intent.putExtras(bundle);
               intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(intent);//Qua màn khác
           }
       });




        return convertView;

    }
}
