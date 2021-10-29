package com.example.doanquanlykhachsan;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doanquanlykhachsan.helpers.dialog;
import com.example.doanquanlykhachsan.model.Room;

import java.util.ArrayList;

public class KH_danh_sach_phong_trong extends AppCompatActivity {
    private ListView listView;
    private Phong_adapter adapter;
    private Button btntrove;
    private ArrayList<Room> data = new ArrayList<>();
    dialog dl = new dialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_danh_sach_phong_trong);
        setControl();
        setEvnet();
    }

    private void setEvnet() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Room room =data.get(position);
                Toast.makeText(getApplicationContext(), room.getMa(), Toast.LENGTH_SHORT).show();
            }
        });
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void setControl() {
        btntrove=findViewById(R.id.btntrove);
        listView = findViewById(R.id.lvDanhSachPhong);
        adapter = new Phong_adapter(getApplicationContext(), R.layout.item_phong, data);
        listView.setAdapter(adapter);
        khoitao();
    }

    private void khoitao() {
        dl.getAllRoom(data,adapter);
    }
}