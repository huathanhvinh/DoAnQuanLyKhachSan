package com.example.doanquanlykhachsan;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class update_phonenumber_2 extends AppCompatActivity {
    private EditText otp_oldphone, otp_newphone, cacha;
    private TextView tvcacha;
    private Button btnTrove, btntieptuc;
    String maxacnhan_dienthoaicu = "";
    String maxacnhan_dienthoaimoi = "";
    String sdtcu = "";
    FirebaseAuth mAuth;
    String idUser = "";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_phonenumber_2);
        setControl();
        setEvent();
    }

    private void setControl() {
        tvcacha = findViewById(R.id.tvCapcha);
        otp_oldphone = findViewById(R.id.otp_oldphone);
        otp_newphone = findViewById(R.id.otp_newphone);
        cacha = findViewById(R.id.Capcha);
        btnTrove = findViewById(R.id.btnTroVe);
        btntieptuc = findViewById(R.id.btntieptuc);
        mAuth = FirebaseAuth.getInstance();
        maxacnhan_dienthoaicu = (String) getIntent().getSerializableExtra("oldphone");
        maxacnhan_dienthoaimoi = (String) getIntent().getSerializableExtra("newphone");
        sdtcu = (String) getIntent().getSerializableExtra("oldphonenumber");
        Log.e("mã xác nhận sdt cu", maxacnhan_dienthoaicu);
        Log.e("mã xác nhận sdt moi", maxacnhan_dienthoaimoi);
        //truy bang user
        StaticConfig.mUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("sdt").getValue(String.class).equals(sdtcu)) {
                        idUser = ds.child("maFB").getValue(String.class);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setEvent() {
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btntieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(maxacnhan_dienthoaicu, otp_oldphone.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Verification Code is wrong, try again", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    Log.e("eror", e.getMessage());
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
                        Toast.makeText(getApplicationContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
                        Log.d("login", "ok");
                        // Update phone
                        StaticConfig.mUser.child(idUser).child("sdt").setValue(maxacnhan_dienthoaimoi);
                    } else {
                        // Sign in failed, display a message and update the UI
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            //Thông báo OTP sai
                            Toast.makeText(getApplicationContext(), "that bai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
