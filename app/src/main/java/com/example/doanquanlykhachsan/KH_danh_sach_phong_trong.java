package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Phong;
import com.example.doanquanlykhachsan.model.Room;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KH_danh_sach_phong_trong extends AppCompatActivity {
    private ListView listView;
    private Doi_Phong_adapter adapter;
    private Button btntrove;
    private ArrayList<Phong> data = new ArrayList<>();


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
                Phong phong = data.get(position);
                Intent intent = new Intent(getApplicationContext(), KH_doi_phong.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("chitiet", phong);
                intent.putExtras(bundle);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
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
        btntrove = findViewById(R.id.btntrove);
        listView = findViewById(R.id.lvDanhSachPhong);
        adapter = new Doi_Phong_adapter(getApplicationContext(), R.layout.item_phong, data);
        listView.setAdapter(adapter);
        khoitao();
    }

    private void khoitao() {
        data.clear();
        StaticConfig.mRoom.orderByChild("soPhong").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Phong phong = ds.getValue(Phong.class);
                    if (ds.child("trangThai").getValue(String.class).equals("trống")) {
                        data.add(phong);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }

        });
    }
}