package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    int i;
    public static String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_xem_dich_vu);
         startActivity(new Intent(getApplicationContext(),sign_in.class));
//        i = 0;
//        StaticConfig.mRoom.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    if (ds.exists()) {
//                        i++;
//                        key = "L0" + i;
//                    }
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        Room room = new Room(key, "phog1", "", "", 0, 0);
//        StaticConfig.mRoom.child(key).setValue(room);
//        Log.e("test", StaticConfig.roomkey);
    }
}