package com.example.doanquanlykhachsan.khach_hang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.adapter.KH_CusTomXacNhanDatPhong;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.khach_hang.menu_khachhang;
import com.example.doanquanlykhachsan.model.DichVuDaChon;
import com.example.doanquanlykhachsan.model.Phong;
import com.example.doanquanlykhachsan.model.PhongDaDat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KH_XacNhanDatPhong extends AppCompatActivity {
    ListView lvDanhSachPhongXN;
    Button btTroVe, btnDatPhong;
    TextView tvXnTenKhachHang, tvSoDienThoai, tvXnSoLuongPhong, tvDichVu, tvXnNhanPhong, tvXnTraPhong;
    EditText edtGhiChuKH;

    ArrayList<Phong> arrayList = new ArrayList<>();
    KH_CusTomXacNhanDatPhong customRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_xac_nhan_dat_phong);

        setConTrol();
        setEvent();
        //Toast.makeText(getApplicationContext(), StaticConfig.arrayListTemporaryService.size()+"", Toast.LENGTH_SHORT).show();

        //so luong phong
        tvXnSoLuongPhong.setText(StaticConfig.arrayListTemporaryRoom.size() + "");

        //ngay nhan phong
        String ngaynhan = (String) getIntent().getStringExtra("ngaynhan");
        tvXnNhanPhong.setText(ngaynhan);

        //ngay tra phong
        String ngaytra = (String) getIntent().getStringExtra("ngaytra");
        tvXnTraPhong.setText(ngaytra);

        //dịch vụ
//        String dv = "";
//        for(int i = 0;i< StaticConfig.arrayListTemporaryService.size();i++){
//            dv += StaticConfig.arrayListTemporaryService.get(i).getTenDV()+ ", ";
//        }
        tvDichVu.setText(StaticConfig.arrayListTemporaryService.size() + "");

        //taiKhoanHienTai
        String taiKhoanHienTai = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        StaticConfig.mUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("email").getValue(String.class).equals(taiKhoanHienTai)) {
                        StaticConfig.currentuser = ds.child("maFB").getValue(String.class);
                        tvXnTenKhachHang.setText(ds.child("name").getValue(String.class));
                        tvSoDienThoai.setText((ds.child("sdt").getValue(String.class)));
                    }
                }
                if (tvXnTenKhachHang.getText().toString().isEmpty()) {
                    tvXnTenKhachHang.setText(taiKhoanHienTai);
                }
                if (tvSoDienThoai.getText().toString().isEmpty()) {
                    tvSoDienThoai.setText(taiKhoanHienTai);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    private void setEvent() {
        customRoom = new KH_CusTomXacNhanDatPhong(getApplicationContext(), R.layout.kh_item_ds_xac_nhan_dat_phong, StaticConfig.arrayListTemporaryRoom);
        lvDanhSachPhongXN.setAdapter(customRoom);
        btnDatPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cap nhat trang thai phong , luu danh sach phong da dat cua khach hang hien tai
                for (int i = 0; i < StaticConfig.arrayListTemporaryRoom.size(); i++) {
                    StaticConfig.mRoom.child(StaticConfig.arrayListTemporaryRoom.get(i).getMaFB()).child("trangThai").setValue("đã đặt");
                    String key = StaticConfig.mRoomRented.push().getKey();
                    PhongDaDat phongDaDat = new PhongDaDat(key, StaticConfig.currentuser, StaticConfig.arrayListTemporaryRoom.get(i).getMaPhong(),
                            tvXnNhanPhong.getText().toString(), tvXnTraPhong.getText().toString(), StaticConfig.sXacNhan, edtGhiChuKH.getText().toString());
                    StaticConfig.mRoomRented.child(key).setValue(phongDaDat);

                    for (int j = 0; j < StaticConfig.arrayListTemporaryService.size(); j++) {
                        String key2 = StaticConfig.mDichVuDaChon.push().getKey();
                        DichVuDaChon dichVuDaChon = new DichVuDaChon(key2,
                                StaticConfig.arrayListTemporaryService.get(j).getMaFB(), StaticConfig.arrayListTemporaryService.get(j).getTenDV(),
                                StaticConfig.arrayListTemporaryRoom.get(i).getMaPhong());
                        StaticConfig.mDichVuDaChon.child(key2).setValue(dichVuDaChon);
                    }
                }

                startActivity(new Intent(getApplicationContext(), menu_khachhang.class));
                Toast.makeText(getApplicationContext(), "Đặt phòng thành công", Toast.LENGTH_LONG).show();
            }
        });
        btTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setConTrol() {
        lvDanhSachPhongXN = findViewById(R.id.lvDanhSachPhongXN);
        btTroVe = findViewById(R.id.btnTroVe);
        btnDatPhong = findViewById(R.id.btnDatPhong);
        tvXnTenKhachHang = findViewById(R.id.tvXnTenKhachHang);
        tvXnSoLuongPhong = findViewById(R.id.tvXnSoLuongPhong);
        tvDichVu = findViewById(R.id.tvDichVu);
        tvSoDienThoai = findViewById(R.id.tvSoDienThoai);
        tvXnNhanPhong = findViewById(R.id.tvXnNhanPhong);
        tvXnTraPhong = findViewById(R.id.tvXnTraPhong);
        edtGhiChuKH = findViewById(R.id.edtGhiChuKH);
    }
}