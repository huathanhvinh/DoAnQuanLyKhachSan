package com.example.doanquanlykhachsan.chung;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanquanlykhachsan.MainActivity;
import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.KhachHang;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.example.doanquanlykhachsan.model.NhanVien_LichLamViec;
import com.example.doanquanlykhachsan.model.NhanVien_Luong;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class update_phonenumber_2 extends AppCompatActivity {
    private EditText otp_oldphone, cacha;
    private TextView tvcacha;
    private Button btnTrove, btntieptuc;
    private ImageView imgChangeCapcha;
    String maxacnhan_dienthoaicu = "";
    String sodienthoai = "";

    FirebaseAuth mAuth;
    String idUser = "";
    String idKH = "";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_phonenumber_2);
        setControl();
        setEvent();
    }

    private void setControl() {
        tvcacha = findViewById(R.id.tvCapcha);
        otp_oldphone = findViewById(R.id.otp_oldphone);
        cacha = findViewById(R.id.Capcha);
        btnTrove = findViewById(R.id.btnTroVe);
        btntieptuc = findViewById(R.id.btntieptuc);
        imgChangeCapcha = findViewById(R.id.imgChangeCapcha);
        mAuth = FirebaseAuth.getInstance();
        tvcacha.setText(RamdomCapcha() + "");
        maxacnhan_dienthoaicu = (String) getIntent().getSerializableExtra("oldphone");
        sodienthoai = (String) getIntent().getSerializableExtra("phonenumber");

        Log.e("mã xác nhận sdt cu", maxacnhan_dienthoaicu);
        //truy bang user
        StaticConfig.mUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("sdt").getValue(String.class).equals(StaticConfig.currentphone)) {
                        idUser = ds.child("maFB").getValue(String.class);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        StaticConfig.mKhachHang.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("sdtKH").getValue(String.class).equals(StaticConfig.currentphone)) {
                        idKH = ds.child("maFB").getValue(String.class);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void setEvent() {
        imgChangeCapcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvcacha.setText(RamdomCapcha() + "");
            }
        });
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btntieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cacha.getText().toString().equals(tvcacha.getText().toString())) {
                    try {
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(maxacnhan_dienthoaicu, otp_oldphone.getText().toString());
                        signInWithPhoneAuthCredential(credential);
                    } catch (Exception e) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Mã xác nhận đã sai, mời nhập lại", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        Log.e("eror", e.getMessage());
                    }
                }
                else {
                    new AlertDialog.Builder(update_phonenumber_2.this)
                            .setTitle("Thông báo ")
                            .setMessage("Nhập mã sai")
                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    tvcacha.setText(RamdomCapcha() + "");
                }
//
            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        // FirebaseUser user = task.getResult().getUser();
                        // Update phone
                        Update();
                        StaticConfig.mUser.child(idUser).child("sdt").setValue(sodienthoai);
                        StaticConfig.fAuth.signOut();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        // Sign in failed, display a message and update the UI
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            //Thông báo OTP sai
                            Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void Update() {
        if (StaticConfig.role == 4) {
            StaticConfig.mKhachHang.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    if (snapshot.child("cmnd").getValue().toString().equals(StaticConfig.currentCmnd)) {
                        KhachHang kh = snapshot.getValue(KhachHang.class);
                        kh.setSdtKH(sodienthoai);
                        StaticConfig.mKhachHang.child(kh.getMaFB()).setValue(kh);
                    }
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            StaticConfig.mNhanVien.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    if (snapshot.child("cmnd").getValue().toString().equals(StaticConfig.currentCmnd)) {
                        NhanVien kh = snapshot.getValue(NhanVien.class);
                        kh.setSoDienThoai(sodienthoai);
                        StaticConfig.mNhanVien.child(kh.getMaFB()).setValue(kh);
                    }
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            StaticConfig.mNhanVien_Luong.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    if (snapshot.child("cmnd").getValue().toString().equals(StaticConfig.currentCmnd)) {
                        NhanVien_Luong kh = snapshot.getValue(NhanVien_Luong.class);
                        kh.setSoDienThoai(sodienthoai);
                        StaticConfig.mNhanVien_Luong.child(kh.getMaFB()).setValue(kh);
                    }
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            StaticConfig.mNhanVien_LichLamViec.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    if (snapshot.child("cmnd").getValue().toString().equals(StaticConfig.currentCmnd)) {
                        NhanVien_LichLamViec kh = snapshot.getValue(NhanVien_LichLamViec.class);
                        kh.setSoDienThoai(sodienthoai);
                        StaticConfig.mNhanVien_LichLamViec.child(kh.getMaFB()).setValue(kh);
                    }
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            new AlertDialog.Builder(update_phonenumber_2.this)
                    .setTitle("Thông báo ")
                    .setMessage("Cập Nhận Thành công")
                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    int RamdomCapcha() {
        int CapchaCode;
        CapchaCode = 1000 + (int) (Math.random() * ((9999 - 1000) + 1));
        return CapchaCode;
    }
}
