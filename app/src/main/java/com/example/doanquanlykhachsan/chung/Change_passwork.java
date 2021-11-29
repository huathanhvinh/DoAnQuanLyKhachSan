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
        btnChangePassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldpass = txtoldPassword.getText().toString();
                String password = txtPassWord.getText().toString();
                String nhaplaimk = txtNhapLaiMK.getText().toString();
                String capcha = txtCapcha.getText().toString();
                if (oldpass.isEmpty() || password.isEmpty() || nhaplaimk.isEmpty() || capcha.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Có thông tin còn trống, vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                }
                if (!password.equals(nhaplaimk) ) {
                    Toast.makeText(getApplicationContext(), "Nhập sai mật khẩu vui lòng nhập lại", Toast.LENGTH_LONG).show();
                }
                if (!capcha.equals(tvCapCha.getText().toString()) ) {
                    Toast.makeText(getApplicationContext(), "Nhập sai mã capcha, vui lòng nhập lại", Toast.LENGTH_LONG).show();
                    rdCapCha = RamdomCapcha();
                    tvCapCha.setText(rdCapCha + "");
                } else {
                    if (!oldpass.isEmpty() && !password.isEmpty()) {
                        if (password.length() < 6) {
                            Toast.makeText(getApplicationContext(), "mật khẩu phải có ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
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
        //kiểm tra pass trước khi đổi
        AuthCredential auth = EmailAuthProvider.getCredential(user.getEmail(), oldpass);
        user.reauthenticate(auth)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //Thành công
                        user.updatePassword(newpass)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getApplicationContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                        StaticConfig.fAuth.signOut();
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Thất bại
                        rdCapCha = RamdomCapcha();
                        tvCapCha.setText(rdCapCha + "");
                        Toast.makeText(getApplicationContext(), "Vui Lòng kiểm tra mật khẩu!!", Toast.LENGTH_SHORT).show();
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
