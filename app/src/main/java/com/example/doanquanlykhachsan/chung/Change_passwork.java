package com.example.doanquanlykhachsan.chung;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Change_passwork extends AppCompatActivity {
    private Button btnChangePassWord, btnReturn;
    private EditText txtoldPassword, txtPassWord, txtNhapLaiMK, txtCapcha;
    private TextView tvCapCha;
    private ImageView imgChangeCapcha;
    int rdCapCha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        setControl();
        setEvent();
        rdCapCha = RamdomCapcha();
        tvCapCha.setText(rdCapCha + "");
    }

    private void setEvent() {
        imgChangeCapcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rdCapCha = RamdomCapcha();
                tvCapCha.setText(rdCapCha + "");
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnChangePassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldpass = txtoldPassword.getText().toString();
                String password = txtPassWord.getText().toString();
                String nhaplaimk = txtNhapLaiMK.getText().toString();
                String capcha = txtCapcha.getText().toString();
                if (oldpass.isEmpty() || password.isEmpty() || nhaplaimk.isEmpty() || capcha.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "C?? th??ng tin c??n tr???ng, vui l??ng nh???p ?????y ????? th??ng tin", Toast.LENGTH_LONG).show();
                }
                if (!password.equals(nhaplaimk) ) {
                    Toast.makeText(getApplicationContext(), "Nh???p sai m???t kh???u vui l??ng nh???p l???i", Toast.LENGTH_LONG).show();
                }
                if (!capcha.equals(tvCapCha.getText().toString()) ) {
                    Toast.makeText(getApplicationContext(), "Nh???p sai m?? capcha, vui l??ng nh???p l???i", Toast.LENGTH_LONG).show();
                    rdCapCha = RamdomCapcha();
                    tvCapCha.setText(rdCapCha + "");
                } else {
                    if (!oldpass.isEmpty() && !password.isEmpty()) {
                        if (password.length() < 6) {
                            Toast.makeText(getApplicationContext(), "m???t kh???u ph???i c?? ??t nh???t 6 k?? t???", Toast.LENGTH_SHORT).show();
                        } else {
                            Updatepass(oldpass, password);
                        }
                    }

                }
            }
        });

    }
    private void Updatepass(String oldpass, String newpass) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //ki???m tra pass tr?????c khi ?????i
        AuthCredential auth = EmailAuthProvider.getCredential(user.getEmail(), oldpass);
        user.reauthenticate(auth)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //Th??nh c??ng
                        user.updatePassword(newpass)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getApplicationContext(), "Thay ?????i m???t kh???u th??nh c??ng", Toast.LENGTH_SHORT).show();
                                        StaticConfig.fAuth.signOut();
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Th???t b???i
                        rdCapCha = RamdomCapcha();
                        tvCapCha.setText(rdCapCha + "");
                        Toast.makeText(getApplicationContext(), "Vui L??ng ki???m tra m???t kh???u!!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void setControl() {
        btnChangePassWord = findViewById(R.id.btnDoiMK);
        btnReturn = findViewById(R.id.btnTroVe);
        txtoldPassword = findViewById(R.id.txtoldPassword);
        txtPassWord = findViewById(R.id.txtNewPassword);
        txtNhapLaiMK = findViewById(R.id.txtNhapLaiNewPassword);
        txtCapcha = findViewById(R.id.txtMaCapCha);
        tvCapCha = findViewById(R.id.tvCapcha);
        imgChangeCapcha = findViewById(R.id.imgChangeCapcha);
    }


    int RamdomCapcha() {
        int CapchaCode;
        CapchaCode = 1000 + (int) (Math.random() * ((9999 - 1000) + 1));
        return CapchaCode;
    }
}
