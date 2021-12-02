package com.example.doanquanlykhachsan.nhanvien_thungan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
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

public class NVTN_ThemKhachHang extends AppCompatActivity {
    Button btnThemKH, btnTroVe;
    EditText edtTenKH, edtSoDT, edtDiaChi, edtCMND, edtEmail;
    TextView tvMaKH;
    int maxSTT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_them_khach_hang);
        setconTrol();
        setEvent();
    }

    private void setEvent() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnThemKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenkh = edtTenKH.getText().toString();
                String diachi = edtDiaChi.getText().toString();
                String sodt = edtSoDT.getText().toString();
                String cmnd = edtCMND.getText().toString();
                String email = edtEmail.getText().toString();
                if (!tenkh.isEmpty() && !diachi.isEmpty() && !sodt.isEmpty() && !tenkh.isEmpty() && !email.isEmpty()) {
                    String key = StaticConfig.mKhachHang.push().getKey();
                    KhachHang kh = new KhachHang(maxSTT, key, tenkh, sodt, diachi, cmnd);
                    StaticConfig.mKhachHang.child(key).setValue(kh);

                    maxSTT = maxSTT + 1;
                    tvMaKH.setText("KH" + maxSTT);
                    register(email, email);
                } else {
                    new AlertDialog.Builder(NVTN_ThemKhachHang.this)
                            .setTitle("Thêm Khách hàng ")
                            .setMessage("Vui Lòng nhập đủ thông tin!!")
                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                }

            }
        });
    }

    private void register(String Email, String Pass) {
        //check email already exist or not.
        StaticConfig.fAuth.fetchSignInMethodsForEmail(Email)
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
                                        new AlertDialog.Builder(NVTN_ThemKhachHang.this)
                                                .setTitle("Thêm Khách hàng ")
                                                .setMessage("Bạn đã thêm thành công")
                                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                                // The dialog is automatically dismissed when a dialog button is clicked.
                                                .setIcon(android.R.drawable.ic_dialog_alert)
                                                .show();
                                    } else {
                                        new AlertDialog.Builder(NVTN_ThemKhachHang.this)
                                                .setTitle("Thêm Khách hàng ")
                                                .setMessage(task.getException().getMessage()+" !!")
                                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                                // The dialog is automatically dismissed when a dialog button is clicked.
                                                .setIcon(android.R.drawable.ic_dialog_alert)
                                                .show();
                                    }
                                }
                            });

                        } else {
                            new AlertDialog.Builder(NVTN_ThemKhachHang.this)
                                    .setTitle("Thêm Khách hàng ")
                                    .setMessage("Email này đã tồn tại !!")
                                    // Specifying a listener allows you to take an action before dismissing the dialog.
                                    // The dialog is automatically dismissed when a dialog button is clicked.
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }

                    }
                });
    }

    private void UpdateUser() {
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        User user = new User(id, edtEmail.getText().toString(), edtSoDT.getText().toString(), edtCMND.getText().toString(), 4);
        StaticConfig.mUser.child(id).setValue(user);
        edtTenKH.setText("");
        edtDiaChi.setText("");
        edtSoDT.setText("");
        edtCMND.setText("");
        edtEmail.setText("");
        edtTenKH.requestFocus();
    }

    private void setconTrol() {
        tvMaKH = findViewById(R.id.tvMaKH);
        edtDiaChi = findViewById(R.id.edtDiachi);
        edtSoDT = findViewById(R.id.edtSoDT);
        edtCMND = findViewById(R.id.edtCMND);
        edtTenKH = findViewById(R.id.edtTenKH);
        edtEmail = findViewById(R.id.edtemail);
        btnThemKH = findViewById(R.id.btnThemKH);
        btnTroVe = findViewById(R.id.btnTroVe);
        maxID();
    }

    private void maxID() {
        StaticConfig.mKhachHang.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    maxSTT = ds.child("stt").getValue(int.class);
                }
                maxSTT = maxSTT + 1;
                tvMaKH.setText("KH" + maxSTT);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}