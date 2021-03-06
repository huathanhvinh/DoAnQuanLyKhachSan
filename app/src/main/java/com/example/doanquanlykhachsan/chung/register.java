package com.example.doanquanlykhachsan.chung;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanquanlykhachsan.MainActivity;
import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.khach_hang.menu_khachhang;
import com.example.doanquanlykhachsan.model.KhachHang;
import com.example.doanquanlykhachsan.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class register extends AppCompatActivity {
    private Button btnSignIn, btnReturn;
    private EditText txtUserName, txtPassWord, txtsdt, txtNhapLaiMK;
    private CheckBox ckbDieuKhoan;
    String ma = "KH0";
    boolean issdt;
    int maxSTT = 0;
    int solan = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setControl();
        setEvent();

    }

    private void setControl() {
        txtsdt = findViewById(R.id.txtPhone);
        txtUserName = findViewById(R.id.txtUserName);
        txtPassWord = findViewById(R.id.txtPassWord);
        txtNhapLaiMK = findViewById(R.id.txtNhapLaiMK);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnReturn = findViewById(R.id.btnReturn);
        ckbDieuKhoan = findViewById(R.id.ckbDieuKhoan);
        maxID();
    }


    private void setEvent() {
        txtsdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                StaticConfig.mUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            User user = ds.getValue(User.class);
                            if (txtsdt.getText().toString().equals(user.getSdt().toString())) {
                                issdt = false;
                                Toast.makeText(getApplicationContext(), "S??? ??i???n tho???i ???? t???n t???i", Toast.LENGTH_SHORT).show();
                                break;
                            } else {
                                issdt = true;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
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
                if (username.isEmpty() || password.isEmpty() || nhaplaimk.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "C?? th??ng tin ????? tr???ng,vui l??ng nh???p ?????y ????? th??ng tin", Toast.LENGTH_SHORT).show();
                }
                if (!password.equals(nhaplaimk)) {
                    Toast.makeText(getApplicationContext(), "M???t kh???u kh??ng ch??nh x??c vui l??ng nh???p l???i", Toast.LENGTH_SHORT).show();
                }
                if (ckbDieuKhoan.isChecked() == false) {
                    Toast.makeText(getApplicationContext(), "B???n ch??a ?????ng ?? ??i???u kho???n", Toast.LENGTH_SHORT).show();

                } else {
                    if (issdt == true) {
                        Log.e("ketqua", issdt + "");
                        register(txtUserName.getText().toString(), txtPassWord.getText().toString());
                    } else {
                        Log.e("ketqua", issdt + "");
                    }

                }

            }
        });
    }


    //check email already exist or not.
    private void register(String Email, String Pass) {
        //check email already exist or not.
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
                                        UpdateKhachhang();
                                        UpdateUser();
                                        Toast.makeText(getApplicationContext(), "????ng k?? Th??nh C??ng", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "L???i :" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                        } else {
                            Toast.makeText(getApplicationContext(), "Email ???? t???n t???i", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void UpdateUser() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (!id.equals(null)) {
            User user = new User(id, txtUserName.getText().toString(), txtsdt.getText().toString(),
                    "000000000", 4);
            StaticConfig.mUser.child(id).setValue(user);
            startActivity(intent);
        }
    }

    private void UpdateKhachhang() {
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (!id.equals(null)) {
            String key = StaticConfig.mKhachHang.push().getKey();
            KhachHang kh = new KhachHang(maxSTT, key, txtUserName.getText().toString(), txtsdt.getText().toString(), "", "000000000");
            StaticConfig.mKhachHang.child(key).setValue(kh);
        }
    }

    private void maxID() {
        StaticConfig.mKhachHang.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    maxSTT = ds.child("stt").getValue(int.class);
                }
                maxSTT = maxSTT + 1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}