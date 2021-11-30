package com.example.doanquanlykhachsan.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.doanquanlykhachsan.MainActivity;
import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.KhachHang;
import com.example.doanquanlykhachsan.model.Phong;
import com.example.doanquanlykhachsan.model.PhongDaDat;
import com.example.doanquanlykhachsan.model.ThongBao;
import com.example.doanquanlykhachsan.model.User;
import com.example.doanquanlykhachsan.nhanvien_letan.NVTN_MenuNhanVienThuNgan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class custom_NVTN_thongbao_datphong extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<PhongDaDat> data;

    public custom_NVTN_thongbao_datphong(@NonNull Context context, int resource, ArrayList<PhongDaDat> data) {
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
        TextView stt = convertView.findViewById(R.id.stt);
        TextView hoten = convertView.findViewById(R.id.hoten);
        TextView ngay = convertView.findViewById(R.id.ngay);
        Button btnChiTiet = convertView.findViewById(R.id.btnChiTiet);
        Button btnXacNhan = convertView.findViewById(R.id.btnxacnhan);

        PhongDaDat thongbao = data.get(position);
        stt.setText(thongbao.getStt()+"");
        StaticConfig.mKhachHang.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    KhachHang u = ds.getValue(KhachHang.class);
                    if (thongbao.getMaKH().equals(u.getMaFB())) {
                        hoten.setText(u.getTenKH());
                        ngay.setText(thongbao.getThoiGianNhanPH());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Đặt Phòng")
                        .setMessage("xác nhận ??")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                StaticConfig.mRoomRented.child(thongbao.getMaFB()).child("xacnhan").setValue("Đã Xác Nhận");
                                String chuoiMaPhong = thongbao.getMaPhong();
                                String[] parts;
                                parts = chuoiMaPhong.split(" ");
                                for (String w : parts) {
                                    StaticConfig.mRoom.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot ds : snapshot.getChildren()) {
                                                Phong p = ds.getValue(Phong.class);
                                                if (p.getMaPhong().equals(w)) {
                                                    StaticConfig.mRoom.child(p.getMaFB()).child("trangThai").setValue("Đã Đặt Phòng");
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
        return convertView;
    }
}
