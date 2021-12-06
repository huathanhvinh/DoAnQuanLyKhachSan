package com.example.doanquanlykhachsan.chung;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;

public class forgot_password extends AppCompatActivity {
    Button btnGetPassWord, btnReturn;
    EditText txtUserName, txtPhone, txtCapcha;
    ImageView imgChangeCapcha;
    TextView tvCapCha;
    int rdCapCha;
    String pass = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        setControl();
        setEvent();

        rdCapCha = RamdomCapcha();
        tvCapCha.setText(rdCapCha + "");

    }

    private void setEvent() {
        btnGetPassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUserName.getText().toString();
                String phone = txtPhone.getText().toString();
                String capcha = txtCapcha.getText().toString();


                // kiểm tra điều kiện nếu uername trống
                if (username.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Không được để trống ô tài khoản", Toast.LENGTH_SHORT).show();
                }
                // kiểm tra điều kiện nếu phone number trống
                if (phone.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Không được để trống ô số điện thoại", Toast.LENGTH_SHORT).show();
                }
                // kiểm tra điều kiện nếu capcha trống
                if (capcha.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Không được để trống ô capcha", Toast.LENGTH_SHORT).show();
                }
                // kiểm tra điều kiện nếu nhập sai capcha
                if (!capcha.equals(tvCapCha.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "nhập mã capcha sai", Toast.LENGTH_SHORT).show();
                    rdCapCha = RamdomCapcha();
                    tvCapCha.setText(rdCapCha + "");
                } else {
                    StaticConfig.fAuth.sendPasswordResetEmail(username);
                    Toast.makeText(getApplicationContext(), "Vui Lòng kiểm tra email!!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        imgChangeCapcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvCapCha.setText(RamdomCapcha() + "");
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

    int RamdomCapcha() {
        int CapchaCode;
        CapchaCode = 1000 + (int) (Math.random() * ((9999 - 1000) + 1));
        return CapchaCode;
    }

    void SendSMS(String PhoneNumber, String Message) {
        PhoneNumber.trim();
        Message.trim();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(PhoneNumber, null, Message, null, null);
            Toast.makeText(getApplicationContext(), "Mật khẩu đã được gửi qua tin nhắn của bạn ", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Gửi tin nhắn thất bại", Toast.LENGTH_SHORT).show();
        }


    }


}