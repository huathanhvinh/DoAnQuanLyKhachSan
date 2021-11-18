package com.example.doanquanlykhachsan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class update_phonenumber_1 extends AppCompatActivity {
    private EditText oldphone, newphone, cacha;
    private TextView tvcacha;
    private Button btnTrove, btntieptuc;
    FirebaseAuth mAuth;
    String maxacnhancu = "";
    String maxacnhanmoi = "";
    private ImageView changeCacha;
    int soluong = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_phonenumber_1);
        setControl();
        setEvent();
    }

    private void setControl() {
        oldphone = findViewById(R.id.etsdt);
        newphone = findViewById(R.id.etnewsdt);
        cacha = findViewById(R.id.Capcha);
        tvcacha = findViewById(R.id.tvCapcha);
        btntieptuc = findViewById(R.id.btntieptuc);
        btnTrove = findViewById(R.id.btnTroVe);
        mAuth = FirebaseAuth.getInstance();
        changeCacha = findViewById(R.id.imgChangeCapcha);
        tvcacha.setText(RamdomCapcha() + "");
    }

    private void setEvent() {
        changeCacha.setOnClickListener(new View.OnClickListener() {
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
                if (!oldphone.getText().toString().isEmpty() &&
                        !newphone.getText().toString().isEmpty() && cacha.getText().toString().equals(tvcacha.getText().toString())) {
                    //gửi otp
                    if (!oldphone.getText().toString().equals(newphone.getText().toString())) {
                        sendOTPCode(oldphone.getText().toString());

                        //0818260857
                    } else {
                        Toast.makeText(getApplicationContext(), "sdt không được trùng nhau", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Nhap du thong tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void sendOTPCode(String toString) {
        String phoneNumber = String.valueOf(toString);
        phoneNumber = "+84".concat(phoneNumber.substring(1, phoneNumber.length()));
        Log.d("TAG", "sendOTPCode: " + phoneNumber);
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(update_phonenumber_1.this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        Log.d("TAG", " completed");
                        String code = phoneAuthCredential.getSmsCode();
                        Log.d("code", code);
                        signInWithPhoneAuthCredential(phoneAuthCredential);

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Log.d("TAG", " " + e.getMessage());
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        maxacnhancu = s;
                        Chuyenmanhinh();
                        super.onCodeSent(s, forceResendingToken);

                    }
                })
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void Chuyenmanhinh() {
        Intent intent = new Intent(getApplicationContext(), update_phonenumber_2.class);
        Bundle bundle = new Bundle();
        bundle.putString("oldphone", maxacnhancu);
        bundle.putString("newphone", newphone.getText().toString());
        bundle.putString("oldphonenumber", oldphone.getText().toString());
        bundle.putString("newphonenumber", newphone.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        // FirebaseUser user = task.getResult().getUser();
                        Log.d("login", "thanh cong");
                        // Update UI


                    } else {
                        // Sign in failed, display a message and update the UI
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            //Thông báo OTP sai
                            Log.d("login", "sai");
                        }
                    }
                });
    }

    int RamdomCapcha() {
        int CapchaCode;
        CapchaCode = 1000 + (int) (Math.random() * ((9999 - 1000) + 1));
        return CapchaCode;
    }
}

