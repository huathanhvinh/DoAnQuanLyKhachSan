package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class forgot_password extends AppCompatActivity {
    Button btnGetPassWord,btnReturn;
    EditText txtUserName,txtPhone,txtCapcha;
    ImageView imgChangeCapcha;
    TextView tvCapCha;
    int rdCapCha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        setControl();
        setEvent();

        rdCapCha = RamdomCapcha();
        tvCapCha.setText(rdCapCha);
    }

    private void setEvent() {
        btnGetPassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUserName.getText().toString();
                String phone = txtPhone.getText().toString();
                String capcha = txtCapcha.getText().toString();
                if (username.isEmpty()){
                    Toast.makeText(getApplicationContext(), "để trống ô tài khoản", Toast.LENGTH_SHORT).show();
                }
                if (phone.isEmpty()){
                    Toast.makeText(getApplicationContext(), "để trống ô số điện thoại", Toast.LENGTH_SHORT).show();
                }
                if (capcha.isEmpty()){
                    Toast.makeText(getApplicationContext(), "để trống ô capcha", Toast.LENGTH_SHORT).show();
                }
                if (capcha != tvCapCha.getText().toString()){
                    Toast.makeText(getApplicationContext(), "nhập mã capcha sai", Toast.LENGTH_SHORT).show();
                    rdCapCha = RamdomCapcha();
                    tvCapCha.setText(rdCapCha);
                }
            }
        });
        imgChangeCapcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rdCapCha = RamdomCapcha();
                tvCapCha.setText(rdCapCha);
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), sign_in.class));
            }
        });
    }

    private void setControl() {
        btnGetPassWord = findViewById(R.id.btnLayMK);
        btnReturn = findViewById(R.id.btnTroVe);
        txtUserName = findViewById(R.id.txtTaiKhoan);
        txtPhone = findViewById(R.id.txtPhone);
        txtCapcha = findViewById(R.id.txtMaCapCha);
        imgChangeCapcha = findViewById(R.id.imgChangeCapcha);
        tvCapCha = findViewById(R.id.tvCapcha);

    }
    int RamdomCapcha(){
        int CapchaCode;
        CapchaCode = 1000+(int)(Math.random()*((9999-1000)+1));
        return CapchaCode;
    }


}