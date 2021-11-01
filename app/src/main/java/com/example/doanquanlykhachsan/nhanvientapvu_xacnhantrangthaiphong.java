package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Room;
import com.example.doanquanlykhachsan.model.nvtv_qlphong;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class nhanvientapvu_xacnhantrangthaiphong extends AppCompatActivity {
    TextView tvMaPhong,tvLoaiPhong,tvTrangThai;
    Button btnXacNhan,btnTroVe;
    Room chitiet;
    CheckBox ckDonPhong;
    boolean check= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvientapvu_xacnhantrangthaiphong);
        setControl();
        setEvent();
        khoiTao();
    }

    private void setEvent() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Khi bấn xác nhận phòng thì lưu checkbox xem phòng đã dọn hay chưa
                StaticConfig.mQLPhong.child(chitiet.getMa()).setValue(new nvtv_qlphong(chitiet.getMa(),check));
                Toast.makeText(getApplicationContext(), "Thông tin phòng đã được lưu", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        ckDonPhong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(ckDonPhong.isChecked())
                {
                    check = true;
                }
                else {
                    check = false;
                }
            }
        });


    }

    private void khoiTao() {
        //
        chitiet = (Room) getIntent().getSerializableExtra("Room");//Lấy thông tin từ customAdapter
        //Gán dữ liệu
        tvMaPhong.setText(chitiet.getMa()+"");
        tvLoaiPhong.setText(chitiet.getLoai()+"");
        tvTrangThai.setText(chitiet.getTinhtrang()+"");

        //Truy xuất từ bản QLPHong
        StaticConfig.mQLPhong.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren())
                {
                    //Kiểm tra chi tiết phòng hiện tại
                    if(chitiet.getMa().equals(ds.child("phong").getValue(String.class))){
                        //Kiểm tra true ,false
                        if(ds.child("kiemtra").getValue(boolean.class).equals(true)){
                            ckDonPhong.setChecked(true);
                        }
                        else {
                            ckDonPhong.setChecked(false);
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void setControl() {
        tvMaPhong = findViewById(R.id.tvMaPhong);
        tvLoaiPhong = findViewById(R.id.tvLoaiPhong);
        tvTrangThai = findViewById(R.id.tvTrangThai);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        btnTroVe = findViewById(R.id.btnTroVe);
        ckDonPhong = findViewById(R.id.ckDonPhong);


    }
}