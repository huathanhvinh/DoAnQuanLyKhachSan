package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Phong;
import com.example.doanquanlykhachsan.model.Room;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KH_danh_sach_phong_da_dat extends AppCompatActivity {
    private ListView listView;
    private Button btntrove, btndoiphonng;
    private ArrayList<Phong> data = new ArrayList<>();
    private Phong_adapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_danh_sach_phong_da_dat);
        setControl();
        setEvnet();
    }

    private void setEvnet() {
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Phong phong = data.get(position);
                for (int i = 0; i < listView.getChildCount(); i++) {
                    if (position == i) {
                        listView.getChildAt(i).setBackgroundColor(Color.WHITE);
//                        chon.add(data.get(i));
                        StaticConfig.chon = data.get(i);
                    } else {
//                        chon.remove(data.get(i));
                        listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        });
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btndoiphonng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StaticConfig.chon == null) {
                    Toast.makeText(getApplicationContext(), "chon di ", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(getApplicationContext(), chon.getMa(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), KH_danh_sach_phong_trong.class));
                }
            }
        });
    }

    private void setControl() {
        btntrove = findViewById(R.id.btntrove);
        btndoiphonng = findViewById(R.id.btndoiphong);
        listView = findViewById(R.id.lvDanhSachPhong);
        adapter = new Phong_adapter(getApplicationContext(), R.layout.item_phong, data);
        listView.setAdapter(adapter);
        Log.e("id", StaticConfig.currentuser );

        khoitao();
        adapter.notifyDataSetChanged();
    }

    private void khoitao() {
        StaticConfig.mRoomRented.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("maKH").getValue().toString().equals(StaticConfig.currentuser)){
                        String maphong = ds.child("maPhong").getValue(String.class);
                        data.clear();
                        StaticConfig.mRoom.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    Phong phong = ds.getValue(Phong.class);
                                    if (phong.getMaPhong().equals(maphong)) {
                                        data.add(phong);
                                    }
                                    adapter.notifyDataSetChanged();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        StaticConfig.chon = null;
    }
}