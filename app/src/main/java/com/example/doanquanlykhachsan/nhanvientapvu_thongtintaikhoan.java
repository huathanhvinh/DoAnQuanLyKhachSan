package com.example.doanquanlykhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
public class nhanvientapvu_thongtintaikhoan extends AppCompatActivity {
    Button btnTroVe,btnLuu;
    ImageView imgEditName,imgEditPhone,imgEditCMND;
    EditText editName,editPhone,editCMND;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvientapvu_thongtintaikhoan);
        setControl();
        setEvent();
    }

    private void setControl() {
        //Ánh xạ editText
        editPhone=findViewById(R.id.editPhone);
        editName = findViewById(R.id.editName);
        editCMND = findViewById(R.id.editCMND);

        //Ánh xạ img
        imgEditName = findViewById(R.id.imgEditName);
        imgEditPhone = findViewById(R.id.imgEditPhone);
        imgEditCMND = findViewById(R.id.imgEditCMND);
        //Ánh xạ button
        btnLuu = findViewById(R.id.btnLuu);
        btnTroVe = findViewById(R.id.btnTroVe);
    }

    private void setEvent() {
        imgEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editName.setEnabled(true);
                editName.setText("");
                editName.requestFocus();
                String str = editName.getText().toString();
                editName.setText(str);

            }
        });

        imgEditPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPhone.setEnabled(true);
                editPhone.setText("");
                editPhone.requestFocus();
                String str = editPhone.getText().toString();
                editPhone.setText(str);
            }
        });

        imgEditCMND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCMND.setEnabled(true);
                editCMND.setText("");
                editCMND.requestFocus();
                String str = editCMND.getText().toString();
                editCMND.setText(str);
            }
        });
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Nhanvientapvu_manhinhchinh.class));
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editName.getText().toString().trim().length() > 0)
                {
                    Toast.makeText( nhanvientapvu_thongtintaikhoan.this,"Lưu thông tin tài khoản" , Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText( nhanvientapvu_thongtintaikhoan.this,"Tên không được bỏ trống" , Toast.LENGTH_SHORT).show();
                }
                if(editPhone.getText().toString().trim().length() > 0)
                {
                    Toast.makeText( nhanvientapvu_thongtintaikhoan.this,"Lưu thông tin tài khoản" , Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText( nhanvientapvu_thongtintaikhoan.this,"Tên không được bỏ trống" , Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


}