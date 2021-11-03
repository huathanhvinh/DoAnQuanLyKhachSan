package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.KhachHang;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageTask;

public class NVTN_ThemKhachHang extends AppCompatActivity {
    Button btnThemKH,btnTroVe;
    EditText edtTenKH,edtSoDT,edtDiaChi,edtCMND;
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
                String tenkh =edtTenKH.getText().toString();
                String diachi=edtDiaChi.getText().toString();
                String sodt=edtSoDT.getText().toString();
                String cmnd=edtCMND.getText().toString();
                String key = StaticConfig.mKhachHang.push().getKey();
                KhachHang kh = new KhachHang(maxSTT,key,tenkh,sodt,diachi,cmnd);
                StaticConfig.mKhachHang.child(key).setValue(kh);
                Toast.makeText(getApplicationContext(), "Bạn Đã Thêm Thành Công", Toast.LENGTH_SHORT).show();
                edtTenKH.setText("");
                edtDiaChi.setText("");
                edtSoDT.setText("");
                edtCMND.setText("");
                edtTenKH.requestFocus();
                maxSTT= maxSTT+1;
                tvMaKH.setText("KH"+maxSTT);

            }
        });
    }

    private void setconTrol() {
        tvMaKH=findViewById(R.id.tvMaKH);
        edtDiaChi = findViewById(R.id.edtDiachi);
        edtSoDT = findViewById(R.id.edtSoDT);
        edtCMND = findViewById(R.id.edtCMND);
        edtTenKH = findViewById(R.id.edtTenKH);
        btnThemKH = findViewById(R.id.btnThemKH);
        btnTroVe = findViewById(R.id.btnTroVe);
        maxID();
    }

    private void maxID() {
        StaticConfig.mKhachHang.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds: snapshot.getChildren())
                {
                    maxSTT = ds.child("stt").getValue(int.class);
                }
                maxSTT= maxSTT+1;
                tvMaKH.setText("KH"+maxSTT);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}