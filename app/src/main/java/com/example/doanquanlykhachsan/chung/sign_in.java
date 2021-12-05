package com.example.doanquanlykhachsan.chung;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanquanlykhachsan.MainActivity;
import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.khach_hang.menu_khachhang;
import com.example.doanquanlykhachsan.nhanvien_tapvu.Nhanvientapvu_manhinhchinh;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class sign_in extends AppCompatActivity {
    private Button btnSignIn, btnRegister;
    private TextView forgot_pass;
    private EditText txtUserName, txtPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setControl();
        SetEvent();
    }

    private void SetEvent() {

        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), forgot_password.class));
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), register.class));
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtPassWord.getText().toString().isEmpty() || txtUserName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nhập email và password", Toast.LENGTH_SHORT).show();
                } else {
                    StaticConfig.fAuth.signInWithEmailAndPassword(txtUserName.getText().toString(), txtPassWord.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //dang nhap thanh cong

                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
//                                Toast.makeText(getApplicationContext(), "Sai email hoặc password!!", Toast.LENGTH_SHORT).show();
                                new AlertDialog.Builder(sign_in.this)
                                        .setTitle("Thông báo")
                                        .setMessage("Sai email hoặc mật khẩu")
                                        // Specifying a listener allows you to take an action before dismissing the dialog.
                                        // The dialog is automatically dismissed when a dialog button is clicked.
                                        .setPositiveButton(android.R.string.yes,null)
                                        // A null listener allows the button to dismiss the dialog and take no further action.
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void setControl() {
        txtUserName = findViewById(R.id.txtUserName);
        txtPassWord = findViewById(R.id.txtPassWord);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnRegister = findViewById(R.id.btnRegister);
        forgot_pass = findViewById(R.id.tvQuenMK);
    }


}