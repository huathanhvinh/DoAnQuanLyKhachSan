package com.example.doanquanlykhachsan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

        Dichvu dv = data.get(position);
        ten.setText(dv.getTen());
        return convertView;
    }
}
