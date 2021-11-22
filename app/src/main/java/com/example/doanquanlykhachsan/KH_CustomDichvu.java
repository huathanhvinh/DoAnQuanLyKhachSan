package com.example.doanquanlykhachsan;

import android.content.Context;
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

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Dichvu;

import java.util.ArrayList;

public class KH_CustomDichvu extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<Dichvu> data;

    public KH_CustomDichvu(@NonNull Context context, int resource, ArrayList<Dichvu> data) {
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
        CheckBox checkBox = convertView.findViewById(R.id.checkbox);
        TextView ten = convertView.findViewById(R.id.tvTendv);
        CheckBox cboDichVu = convertView.findViewById(R.id.cboDichVu);

        Dichvu dv = data.get(position);
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
                } else {
                    StaticConfig.arrayListTemporaryService.remove(dv);
                }
            }
        });

        return convertView;
    }
}
