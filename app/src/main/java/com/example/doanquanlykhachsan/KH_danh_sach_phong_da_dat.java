package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.dialog;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Dangky;
import com.example.doanquanlykhachsan.model.Room;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KH_danh_sach_phong_da_dat extends AppCompatActivity {
    private ListView listView;
    private Button btntrove, btndoiphonng;
    private ArrayList<Room> data = new ArrayList<>();
    private Phong_adapter adapter;
    dialog dl = new dialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_danh_sach_phong_da_dat);
        setControl();
        setEvnet();
    }

    private void setEvnet() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Room room = data.get(position);
                Toast.makeText(getApplicationContext(), room.getMa(), Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(getApplicationContext(), KH_danh_sach_phong_trong.class));
                Log.e("max Room id", dl.getroomid());
            }
        });
    }

    private void setControl() {
        btntrove = findViewById(R.id.btntrove);
        btndoiphonng = findViewById(R.id.btndoiphong);
        listView = findViewById(R.id.lvDanhSachPhong);
        adapter = new Phong_adapter(getApplicationContext(), R.layout.item_phong, data);
        listView.setAdapter(adapter);
        khoitao();
        adapter.notifyDataSetChanged();
    }

    private void khoitao() {
        dl.getroomid();
        // dl.getAllRoom(data,adapter);
        //StaticConfig.mDangky.child("L1").setValue(new Dangky("L1","KH1","20/5/2020"));
        ArrayList<Dangky> dangky = new ArrayList<>();
        dangky.clear();
        data.clear();
        StaticConfig.mDangky.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Dangky dk = ds.getValue(Dangky.class);
                    if (dk.getMakh().equals(StaticConfig.currentuser)) {
                        dangky.add(dk);
                        StaticConfig.mRoom.orderByChild("sophong").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    Room room = ds.getValue(Room.class);
                                    if (ds.child("ma").getValue(String.class).equals(dk.getMaphong())) {
                                        data.add(room);
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

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // adapter.notifyDataSetChanged();

//        ArrayList<Room> data = new ArrayList<>();
//        StaticConfig.mRoom.orderByChild("sophong").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    Room room = ds.getValue(Room.class);
//                    if (ds.child("tinhtrang").getValue(String.class).equals("trống")) {
//                        data.add(room);
//                    }
//                    adapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });

    }
}