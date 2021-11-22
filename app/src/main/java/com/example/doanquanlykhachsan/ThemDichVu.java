package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.DichVu;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class ThemDichVu extends AppCompatActivity {
    private Button btnThem, btnTroVe;
    private TextView tvMaDV;
    private EditText txtTenDV, txtGiaDV,txtChiTietDV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_themdichvu);
        setControl();
        SetEvent();
    }
    private void setControl() {
        btnThem = findViewById(R.id.btnThem);
        btnTroVe = findViewById(R.id.btnTroVe);
        tvMaDV = findViewById(R.id.tvMaDichVu);
        txtTenDV = findViewById(R.id.txtTenDichVu);
        txtGiaDV = findViewById(R.id.txtGiaDichVu);
        txtChiTietDV = findViewById(R.id.txtChiTietDichVu);
    }
    private void SetEvent() {

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ma = tvMaDV.getText().toString();
                String ten = txtTenDV.getText().toString();
                float gia = Float.parseFloat(txtGiaDV.getText().toString());
                String chitiet = txtChiTietDV.getText().toString();
                DichVu dv = new DichVu(ma,ten,gia,chitiet);
                StaticConfig.mDichVu.child(ma).setValue(dv);

            }
        });


    }




}