package com.example.doanquanlykhachsan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Dichvu;
import com.example.doanquanlykhachsan.model.SelectedService;

import java.util.ArrayList;


public class CusTomSelectedService extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<SelectedService> data;

    public CusTomSelectedService(@NonNull Context context, int resource, @NonNull ArrayList<SelectedService> data) {
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
        SelectedService dv = data.get(position);

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
