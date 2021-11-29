package com.example.doanquanlykhachsan.nhanvien_tapvu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.model.ThongBao;

import java.util.ArrayList;

public class nhanvientapvu_thongbao extends AppCompatActivity {
    ListView lvThongBao;
    Button btnTroVe;
    ArrayList<ThongBao> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvientapvu_thongbao);
        setControl();
        setEvent();
    }
    private void setControl() {
        lvThongBao = findViewById(R.id.lvThongBao);
        btnTroVe = findViewById(R.id.btnTroVe);
    }
    private void setEvent() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        custom_nhanvientapvu_thongbao nhanvientapvu_thongbao = new custom_nhanvientapvu_thongbao(this,R.layout.listview_nhanvientapvu_thongbao,data);
//        lvThongBao.setAdapter(nhanvientapvu_thongbao);
//        khoiTao();
//        nhanvientapvu_thongbao.notifyDataSetChanged();
    }


}