package com.example.doanquanlykhachsan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Change_passwork extends AppCompatActivity {
    private Button btnChangePassWord, btnReturn;
    private EditText txtUserName, txtPassWord, txtNhapLaiMK,txtCapcha;
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
        tvCapCha.setText(rdCapCha+"");
    }

    private void setEvent() {

        btnChangePassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUserName.getText().toString();
                String password  = txtPassWord.getText().toString();
                String nhaplaimk = txtNhapLaiMK.getText().toString();
                String capcha = txtCapcha.getText().toString();
                if (username.isEmpty()||password.isEmpty()||nhaplaimk.isEmpty()||capcha.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Có thông tin còn trống, vui lòng nhập đầy đủ thông tin",Toast.LENGTH_LONG).show();
                }
                if(password != nhaplaimk){
                    Toast.makeText(getApplicationContext(),"Nhập sai mật khẩu vui lòng nhập lại",Toast.LENGTH_LONG).show();
                }
                if(capcha != tvCapCha.getText().toString()){
                    Toast.makeText(getApplicationContext(),"Nhập sai mã capcha, vui lòng nhập lại",Toast.LENGTH_LONG).show();
                    rdCapCha = RamdomCapcha();
                    tvCapCha.setText(rdCapCha+"");
                }
            }
        });

    }

    private void setControl() {
        btnChangePassWord = findViewById(R.id.btnDoiMK);
        btnReturn = findViewById(R.id.btnTroVe);
        txtUserName = findViewById(R.id.txtUserName);
        txtPassWord = findViewById(R.id.txtNewPassword);
        txtNhapLaiMK = findViewById(R.id.txtNhapLaiNewPassword);
        txtCapcha = findViewById(R.id.txtMaCapCha);
        tvCapCha = findViewById(R.id.tvCapcha);
        imgChangeCapcha = findViewById(R.id.imgChangeCapcha);
    }


    int RamdomCapcha(){
        int CapchaCode;
        CapchaCode = 1000+(int)(Math.random()*((9999-1000)+1));
        return CapchaCode;
    }
}
