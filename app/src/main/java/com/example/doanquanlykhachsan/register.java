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
    ArrayList<Long> data = new ArrayList<>();


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
                if (password != nhaplaimk){
                    Toast.makeText(getApplicationContext(),"Mật khẩu không chính xác vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                }
                if (ckbDieuKhoan.isChecked() == false){
                    Toast.makeText(getApplicationContext(),"Bạn chưa đồng ý điều khoản", Toast.LENGTH_SHORT).show();

                }
                register(txtUserName.getText().toString(), txtPassWord.getText().toString());
            }
        });
    }

    private void register(String Email, String Pass) {
        StaticConfig.fAuth.fetchSignInMethodsForEmail(txtUserName.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                        boolean isNewUser = task.getResult().getSignInMethods().isEmpty();

                        if (isNewUser) {
                            StaticConfig.fAuth.createUserWithEmailAndPassword(Email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isComplete()) {
                                        UpdateUser();
                                        Toast.makeText(getApplicationContext(), "dang ky thanh cong", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Error:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                        } else {
                            Toast.makeText(getApplicationContext(), "email da ton tai", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


//check email already exist or not.

    private void UpdateUser() {

        if (data.size() > 0) {
            Long max = data.get(0);
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).compareTo(max) > 0) {
                    max = data.get(i);
                }
            }
            max++;
            String ma = "KH" + max;
        }

        User user = new User(ma, txtUserName.getText().toString(), txtsdt.getText().toString());
        StaticConfig.mUser.child(ma).setValue(user);
        Intent intent = new Intent(getApplicationContext(), sign_in.class);
        startActivity(intent);

    }

    private void setControl() {
        txtsdt = findViewById(R.id.txtPhone);
        txtUserName = findViewById(R.id.txtUserName);
        txtPassWord = findViewById(R.id.txtPassWord);
        txtNhapLaiMK = findViewById(R.id.txtNhapLaiMK);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnReturn = findViewById(R.id.btnReturn);
        ckbDieuKhoan = findViewById(R.id.ckbDieuKhoan);
        maxid();

    }

    private void maxid() {
        StaticConfig.mUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        long mau = 0;
                        ma = ds.child("id").getValue(String.class);
                        String[] parts;
                        parts = ma.split("KH");
                        mau = Long.parseLong(parts[1]);
                        data.add(mau);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}