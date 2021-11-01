package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.doanquanlykhachsan.model.ThongBao;

import java.util.ArrayList;

public class NVTN_Thongbao extends AppCompatActivity {
    ListView lvThongBao;
    Button btnTroVe;
    ArrayList<ThongBao> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_thongbao);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        custom_NVTN_thongbao nhanvienthungan_thongbao = new custom_NVTN_thongbao(this,R.layout.listview_nvtn_thongbao,data);
        lvThongBao.setAdapter(nhanvienthungan_thongbao);
        khoiTao();
        nhanvienthungan_thongbao.notifyDataSetChanged();

    }

    private void khoiTao() {
        ThongBao thongbao = new ThongBao("STT : 01","Nội dung","9:00","25/09/2021","Đăng ký dịch vụ","Chi tiết","Xác nhận");
        ThongBao thongbao1 = new ThongBao("STT : 02","Nội dung","9:00","25/09/2021","Đăng ký dịch vụ","Chi tiết","Xác nhận");
        ThongBao thongbao2 = new ThongBao("STT : 03","Nội dung","9:00","25/09/2021","Đăng ký dịch vụ","Chi tiết","Xác nhận");
        ThongBao thongbao3 = new ThongBao("STT : 04","Nội dung","9:00","25/09/2021","Đăng ký dịch vụ","Chi tiết","Xác nhận");
        data.add(thongbao);
        data.add(thongbao1);
        data.add(thongbao2);
        data.add(thongbao3);

    }

    private void setControl() {
        lvThongBao = findViewById(R.id.lvThongBao);
        btnTroVe = findViewById(R.id.btnTroVe);

    }
}