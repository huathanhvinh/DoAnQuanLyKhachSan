package com.example.doanquanlykhachsan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.model.DichVuDaChon;

import java.util.ArrayList;


public class CusTomDichVuDaChon extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<DichVuDaChon> data;

    public CusTomDichVuDaChon(@NonNull Context context, int resource, @NonNull ArrayList<DichVuDaChon> data) {
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
        CheckBox cboDichVu = convertView.findViewById(R.id.cboDichVu);
        DichVuDaChon dv = data.get(position);

//        cboDichVu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (cboDichVu.isChecked()) {
//                    StaticConfig.arrayListTemporaryService.add(dv);
//                    Toast.makeText(getContext(), "11", Toast.LENGTH_LONG).show();
//                } else {
//                    StaticConfig.arrayListTemporaryService.remove(dv);
//                }
//            }
//        });
        return convertView;
    }
}
