package com.example.doanquanlykhachsan.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.DichVu;
import com.example.doanquanlykhachsan.model.PhongDaDat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KH_CustomDichvu extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<DichVu> data;
    String madichvu = "";

    public KH_CustomDichvu(@NonNull Context context, int resource, ArrayList<DichVu> data) {
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
        TextView ten = convertView.findViewById(R.id.tvTendv);
        CheckBox cboDichVu = convertView.findViewById(R.id.cboDichVu);

        DichVu dv = data.get(position);
        ten.setText(dv.getMota());
        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cboDichVu.isChecked() == true) {
                    cboDichVu.setChecked(false);
                } else {
                    cboDichVu.setChecked(true);
                }
            }
        });
        cboDichVu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cboDichVu.isChecked() == true) {
                    StaticConfig.arrayListTemporaryService.add(dv);
                } else if (cboDichVu.isChecked() == false) {
                    StaticConfig.arrayListTemporaryService.remove(dv);
                }
            }
        });
        if (StaticConfig.sXacNhan.equals("phong da thue")) {
            StaticConfig.mRoomRented.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        PhongDaDat da = ds.getValue(PhongDaDat.class);
                        if (StaticConfig.currentuser.equals(da.getMaKH()) && StaticConfig.mathue.equals(da.getMaFB())) {
                            madichvu = da.getMaDichVu();
                            String[] parts = null;
                            parts = madichvu.split(" ");
                            for (String w : parts) {
                                StaticConfig.mDichVu.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (dv.getMaFB().equals(w)) {
                                            cboDichVu.setChecked(true);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        return convertView;
    }
}
