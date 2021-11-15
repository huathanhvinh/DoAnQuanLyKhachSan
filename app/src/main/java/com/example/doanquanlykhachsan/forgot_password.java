package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class forgot_password extends AppCompatActivity {
    Button btnGetPassWord,btnReturn;
    EditText txtUserName,txtPhone,txtCapcha;
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
       tvCapCha.setText(rdCapCha+"");

    }

    private void setEvent() {
        btnGetPassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUserName.getText().toString();
                String phone = txtPhone.getText().toString();
                String capcha = txtCapcha.getText().toString();


                // kiểm tra điều kiện nếu uername trống
                if (username.isEmpty()){
                    Toast.makeText(getApplicationContext(), "để trống ô tài khoản", Toast.LENGTH_SHORT).show();
                }
                // kiểm tra điều kiện nếu phone number trống
                if (phone.isEmpty()){
                    Toast.makeText(getApplicationContext(), "để trống ô số điện thoại", Toast.LENGTH_SHORT).show();
                }
                // kiểm tra điều kiện nếu capcha trống
                if (capcha.isEmpty()){
                    Toast.makeText(getApplicationContext(), "để trống ô capcha", Toast.LENGTH_SHORT).show();
                }
                // kiểm tra điều kiện nếu nhập sai capcha
                if (!capcha.equals( tvCapCha.getText().toString())){
                    Toast.makeText(getApplicationContext(), "nhập mã capcha sai", Toast.LENGTH_SHORT).show();
                    rdCapCha = RamdomCapcha();
                    tvCapCha.setText(rdCapCha+"");
                }
                // kiểm quyền & gửi tin nhắn
                StaticConfig.mUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()){
                            User user = ds.getValue(User.class);
                            if (username.equals(user.getEmail())){
                                pass = user.getMatKhau();
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                        SendSMS(phone,pass);
                    }else {
                        requestPermissions(new String[] {Manifest.permission.SEND_SMS},1);
                    }
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

    void SendSMS(String PhoneNumber, String Message){
        PhoneNumber.trim();
        Message.trim();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(PhoneNumber,null,Message,null,null);
            Toast.makeText(getApplicationContext(), "Mật khẩu đã được gửi qua tin nhắn của bạn ", Toast.LENGTH_SHORT).show();

        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Gửi tin nhắn thất bại", Toast.LENGTH_SHORT).show();
        }


    }


}