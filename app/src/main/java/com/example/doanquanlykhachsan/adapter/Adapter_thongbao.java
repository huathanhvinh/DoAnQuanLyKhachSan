package com.example.doanquanlykhachsan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.model.PhongDaDat;

import java.util.ArrayList;

public class Adapter_thongbao extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<PhongDaDat> data;

    public Adapter_thongbao(@NonNull Context context, int resource, ArrayList<PhongDaDat> data) {
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

        return convertView;
    }
}
