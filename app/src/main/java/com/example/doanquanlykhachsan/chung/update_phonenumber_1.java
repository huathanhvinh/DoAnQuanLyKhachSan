package com.example.doanquanlykhachsan.chung;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.User;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class update_phonenumber_1 extends AppCompatActivity {
    private EditText oldphone, cacha;
    private TextView tvcacha;
    private Button btnTrove, btntieptuc;
    FirebaseAuth mAuth;
    String maxacnhancu = "";
    String maxacnhanmoi = "";
    private ImageView changeCacha;
    int soluong = 0;
    private boolean isphone;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_phonenumber_1);
        setControl();
        setEvent();
    }

    private void setControl() {
        oldphone = findViewById(R.id.etsdt);
        cacha = findViewById(R.id.Capcha);
        tvcacha = findViewById(R.id.tvCapcha);
        btntieptuc = findViewById(R.id.btntieptuc);
        btnTrove = findViewById(R.id.btnTroVe);
        mAuth = FirebaseAuth.getInstance();
        changeCacha = findViewById(R.id.imgChangeCapcha);
        tvcacha.setText(RamdomCapcha() + "");
    }

    private void setEvent() {
        oldphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                StaticConfig.mUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            User user = ds.getValue(User.class);
                            if (oldphone.getText().toString().equals(user.getSdt().toString())) {
                                isphone = false;
                                break;
                            } else {
                                isphone = true;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
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
                if (isphone == true) {
                    if (!oldphone.getText().toString().isEmpty() && cacha.getText().toString().equals(tvcacha.getText().toString())) {
                        //gửi otp
                         sendOTPCode(oldphone.getText().toString());

                    } else {
                        Toast.makeText(getApplicationContext(), "Xin nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    new AlertDialog.Builder(update_phonenumber_1.this)
                            .setTitle("Thông báo ")
                            .setMessage("So dien da ton tai ")
                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
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
        bundle.putString("phonenumber", oldphone.getText().toString());
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
                            tvcacha.setText(RamdomCapcha() + "");
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
