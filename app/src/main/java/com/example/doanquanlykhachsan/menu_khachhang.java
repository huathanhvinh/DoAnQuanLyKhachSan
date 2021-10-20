package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Room;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class menu_khachhang extends AppCompatActivity {

    private ImageView avatar;
    private TextView name;
    private Button dangxuat;
    int i;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_khachhang);
        setControl();
        setEvent();
    }

    private void setEvent() {
        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(menu_khachhang.this)
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to sign out of the app??")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                StaticConfig.fAuth.signOut();
                                startActivity(new Intent(menu_khachhang.this, MainActivity.class));
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    private void setControl() {
        avatar = findViewById(R.id.avatar);
        name = findViewById(R.id.name);
        dangxuat = findViewById(R.id.dangxuat);
//        for (i = 10; i < 30; i++) {
//            key = "L0" + i;
//            Room room = new Room(key, "phog " + i, "nor", "Free", 200, 50, i);
//            StaticConfig.mRoom.child(key).setValue(room);
//        }

        StaticConfig.mRoom.orderByChild("sophong").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()){
                    Log.e("id ",ds.child("ma").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        StaticConfig.mUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("email").getValue(String.class).equals(FirebaseAuth.getInstance().getCurrentUser().getEmail()))
                        name.setText(ds.child("sdt").getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}