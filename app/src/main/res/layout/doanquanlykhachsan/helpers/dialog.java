package com.example.doanquanlykhachsan.helpers;

import androidx.annotation.NonNull;

import com.example.doanquanlykhachsan.Phong_adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class dialog {
    String ma;

    ArrayList<Long> Tongid = new ArrayList<>();
    long max;

    public String getuserid() {
        StaticConfig.mUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    max = 0;
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        long mau;
                        ma = ds.child("id").getValue(String.class);
                        String[] parts;
                        parts = ma.split("KH");
                        mau = Long.parseLong(parts[1]);
                        Tongid.add(mau);
                        Long max = Tongid.get(0);
                        for (int i = 0; i < Tongid.size(); i++) {
                            if (Tongid.get(i).compareTo(max) > 0) {
                                max = Tongid.get(i);
                            }
                        }
                        max++;
                        ma = "KH" + max;

                    }
                } else {
                    ma = "KH1";
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });
        return ma;
    }

    public String getroomid() {
        StaticConfig.mRoom.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    max = 0;
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        long mau;
                        ma = ds.child("ma").getValue(String.class);
                        String[] parts;
                        parts = ma.split("L");

                        mau = Long.parseLong(parts[1]);
                        Tongid.add(mau);
                        Long max = Tongid.get(0);
                        for (int i = 0; i < Tongid.size(); i++) {
                            if (Tongid.get(i).compareTo(max) > 0) {
                                max = Tongid.get(i);
                            }
                        }
                        max++;
                        ma = "L" + max;

                    }
                } else {
                    ma = "L1";
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });
        return ma;
    }

    //them
    public void addUser(String id, User user) {
        StaticConfig.mUser.child(id).setValue(user);
    }

    public void addRoom(String id, Room room) {
        StaticConfig.mRoom.child(id).setValue(room);
    }

    //xoa
    public void delRoom(String id) {
        StaticConfig.mRoom.child(id).removeValue();
    }

    public void delUser(String id) {
        StaticConfig.mUser.child(id).removeValue();
    }

    //update
    public void updateRoom(String id, Room room) {
        StaticConfig.mRoom.child(id).setValue(room);
    }

    public void updateUser(String id, User user) {
        StaticConfig.mRoom.child(id).setValue(user);
    }

    //lay du lieu
    public void getAllRoom(ArrayList<Room> data, Phong_adapter adapter) {
        data.clear();
        StaticConfig.mRoom.orderByChild("sophong").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Room room = ds.getValue(Room.class);
                    if (ds.child("tinhtrang").getValue(String.class).equals("trống")) {
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
//
//    public ArrayList getAllRoom1() {
//        ArrayList<Room> test = new ArrayList<>();
//        StaticConfig.mRoom.orderByChild("sophong").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    Room room = ds.getValue(Room.class);
//                    test.add(room);
//                    Log.d("room", room.getMa());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                throw error.toException();
//            }
//        });
//        return test;
//    }

}
