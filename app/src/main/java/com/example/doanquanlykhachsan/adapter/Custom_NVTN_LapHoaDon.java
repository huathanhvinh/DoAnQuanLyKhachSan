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
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.KhachHang;
import com.example.doanquanlykhachsan.model.PhongDaDat;
import com.example.doanquanlykhachsan.model.User;
import com.example.doanquanlykhachsan.nhanvien_thungan.NVTN_XacNhanHoaDon;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Custom_NVTN_LapHoaDon extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<PhongDaDat> arrHoaDon;

    public Custom_NVTN_LapHoaDon(@NonNull Context context, int resource, @NonNull ArrayList<PhongDaDat> arrHoaDon) {
        super(context, resource, arrHoaDon);
        this.context = context;
        this.resource = resource;
        this.arrHoaDon = arrHoaDon;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        TextView tvTenKH = convertView.findViewById(R.id.tvTenKH);
        Button btnLapHD = convertView.findViewById(R.id.btnLapHD);

        PhongDaDat laphoadon = arrHoaDon.get(position);
        StaticConfig.mUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    User u = ds.getValue(User.class);
                    if (u.getMaFB().equals(laphoadon.getMaKH())) {
                        String sdt = u.getSdt();
                        StaticConfig.mKhachHang.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    KhachHang kh = ds.getValue(KhachHang.class);
                                    if ((sdt.equals(kh.getSdtKH()))) {
                                        tvTenKH.setText(kh.getTenKH());

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //
        btnLapHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NVTN_XacNhanHoaDon.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("chitiet", laphoadon);
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
