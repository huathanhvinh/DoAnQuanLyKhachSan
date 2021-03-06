package com.example.doanquanlykhachsan.adapter;

import android.content.Context;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.DoiPhong;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.example.doanquanlykhachsan.model.Phong;
import com.example.doanquanlykhachsan.model.PhongDaDat;
import com.example.doanquanlykhachsan.model.ThongBao;
import com.example.doanquanlykhachsan.nhanvien_thungan.NVTN_ThongBao_DoiPhong;
import com.example.doanquanlykhachsan.nhanvien_thungan.NVTN_thongbao_xacnhandatphong;
import com.example.doanquanlykhachsan.nhanvien_thungan.NVTN_xacnhandoiphong;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class custom_NVTN_thongbao_doiphong extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<PhongDaDat> data;
    int id = 0;
    private String tennv;

    public custom_NVTN_thongbao_doiphong(@NonNull Context context, int resource, ArrayList<PhongDaDat> data) {
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
        layid();
        tenNhanvien();

        PhongDaDat da = data.get(position);
        stt.setText(da.getStt() + "");
        ngay.setText(da.getNgaybatdau());
        hoten.setText(da.getTen());
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("?????i Ph??ng")
                        .setMessage("x??c nh???n ??")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String key = StaticConfig.mThongBao.push().getKey();
                                ThongBao tb = new ThongBao(id + 1, key, "?????i Ph??ng ", "Ch??a x??c nh???n", "", tennv, da.getMaKH());
                                StaticConfig.mThongBao.child(key).setValue(tb);
                                //remove doi phong
                                StaticConfig.mDoiPhong.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot ds : snapshot.getChildren()) {
                                            DoiPhong dp = ds.getValue(DoiPhong.class);
                                            if (dp.getMaPhieu().equals(da.getMaFB())) {
                                                //cap nhap
                                                StaticConfig.mRoom.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        for(DataSnapshot ds:snapshot.getChildren()){
                                                            Phong p = ds.getValue(Phong.class);
                                                            if(p.getMaPhong().equals(dp.getMaPhongchon())){
                                                                StaticConfig.mRoom.child(p.getMaFB()).child("trangThai").setValue("Tr???ng");
                                                            }
                                                            if(p.getMaPhong().equals(dp.getGetMaPhong())){
                                                                StaticConfig.mRoom.child(p.getMaFB()).child("trangThai").setValue("???? ?????t Ph??ng");
                                                            }

                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });
                                                String str = da.getMaPhong();
                                                String replacedStr = str.replaceAll(dp.getMaPhongchon(), dp.getGetMaPhong());
                                                StaticConfig.mRoomRented.child(da.getMaFB()).child("maPhong").setValue(replacedStr);

                                                StaticConfig.mDoiPhong.child(dp.getMaFB()).removeValue();
                                                NVTN_ThongBao_DoiPhong.khoitao();
                                            }

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
        btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NVTN_xacnhandoiphong.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("chitiet", da);
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    private void tenNhanvien() {
        StaticConfig.mNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    NhanVien nv = ds.getValue(NhanVien.class);
                    if (nv.getSoDienThoai().equals(StaticConfig.currentphone)) {
                        tennv = nv.getTenNV();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void layid() {
        StaticConfig.mThongBao.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    ThongBao tb = ds.getValue(ThongBao.class);
                    id = tb.getStt();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
