package com.example.doanquanlykhachsan;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class update_phonenumber_2 extends AppCompatActivity {
    private EditText otp_oldphone, otp_newphone, cacha;
    private TextView tvcacha;
    private Button btnTrove, btntieptuc;
    String maxacnhan_dienthoaicu = "";
    String maxacnhan_dienthoaimoi = "";
    FirebaseAuth mAuth;
    boolean is_ok = false;

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
        Log.e("mã xác nhận sdt cu",maxacnhan_dienthoaicu);
        Log.e("mã xác nhận sdt moi",maxacnhan_dienthoaimoi);
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
                PhoneAuthCredential Sdtcu = PhoneAuthProvider.getCredential(maxacnhan_dienthoaicu, otp_oldphone.getText().toString());
                signInWithPhoneAuthCredential(Sdtcu);


                if (is_ok == true) {
                    StaticConfig.mUser.child(FirebaseAuth.getInstance().getUid()).child("sdt").setValue(maxacnhan_dienthoaimoi);
                    Toast.makeText(getApplicationContext(), "Đổi thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        // FirebaseUser user = task.getResult().getUser();
                        // Toast.makeText(getApplicationContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
                        is_ok = true;
                        // Update UI
                    } else {
                        // Sign in failed, display a message and update the UI
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            //Thông báo OTP sai
                            // Toast.makeText(getApplicationContext(), "that bai", Toast.LENGTH_SHORT).show();
                            is_ok = false;
                        }
                    }
                });
    }
}
