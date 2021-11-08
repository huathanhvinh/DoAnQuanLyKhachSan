package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class register extends AppCompatActivity {
    private Button btnSignIn, btnReturn;
    private EditText txtUserName, txtPassWord, txtsdt,txtNhapLaiMK;
    private CheckBox ckbDieuKhoan;
    String ma = "KH0";
    ArrayList<User> userList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setControl();
        setEvent();

    }

    private void setEvent() {
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), sign_in.class));
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUserName.getText().toString();
                String password = txtPassWord.getText().toString();
                String nhaplaimk = txtNhapLaiMK.getText().toString();
                String phone = txtsdt.getText().toString();
                if (username.isEmpty()||password.isEmpty()||nhaplaimk.isEmpty()||phone.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Có thông tin để trống,vui lòng nhập đầy đủ thông tin" , Toast.LENGTH_SHORT).show();
                }
                if (!password.equals(nhaplaimk) ){
                    Toast.makeText(getApplicationContext(),"Mật khẩu không chính xác vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                }
                if (ckbDieuKhoan.isChecked() == false){
                    Toast.makeText(getApplicationContext(),"Bạn chưa đồng ý điều khoản", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }




//check email already exist or not.


    private void setControl() {
        txtsdt = findViewById(R.id.txtPhone);
        txtUserName = findViewById(R.id.txtUserName);
        txtPassWord = findViewById(R.id.txtPassWord);
        txtNhapLaiMK = findViewById(R.id.txtNhapLaiMK);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnReturn = findViewById(R.id.btnReturn);
        ckbDieuKhoan = findViewById(R.id.ckbDieuKhoan);
    }


}