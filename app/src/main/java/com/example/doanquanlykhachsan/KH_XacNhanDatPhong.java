package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Room;
import com.example.doanquanlykhachsan.model.RoomRented;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class KH_XacNhanDatPhong extends AppCompatActivity {
    ListView lvDanhSachPhongXN;
    Button btTroVe, btnDatPhong;
    TextView tvXnTenKhachHang, tvSoDienThoai, tvXnSoLuongPhong, tvDichVu, tvXnNhanPhong, tvXnTraPhong;

    ArrayList<Room> roomArrayList = new ArrayList<>();
    KH_CusTomXacNhanDatPhong customRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_xac_nhan_dat_phong);
        setConTrol();
        setEvent();

        //so luong phong
        tvXnSoLuongPhong.setText(StaticConfig.arrayListCheckItem.size() + "");

        //ngay nhan phong
        String ngaynhan = (String) getIntent().getStringExtra("ngaynhan");
        tvXnNhanPhong.setText(ngaynhan);

        //ngay tra phong
        String ngaytra = (String) getIntent().getStringExtra("ngaytra");
        tvXnTraPhong.setText(ngaytra);

        //thong tin khach hang
        String tenhientai = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        StaticConfig.mUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("email").getValue(String.class).equals(tenhientai)) {
                        StaticConfig.currentuser = ds.child("id").getValue(String.class);
                        tvXnTenKhachHang.setText(ds.child("name").getValue(String.class));
                        tvSoDienThoai.setText((ds.child("sdt").getValue(String.class)));
                    }
                }
                if (tvXnTenKhachHang.getText().toString().isEmpty()) {
                    tvXnTenKhachHang.setText(tenhientai);
                }
                if (tvSoDienThoai.getText().toString().isEmpty()) {
                    tvSoDienThoai.setText(tenhientai);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    private void setEvent() {
        customRoom = new KH_CusTomXacNhanDatPhong(getApplicationContext(), R.layout.kh_item_ds_xac_nhan_dat_phong, StaticConfig.arrayListCheckItem);
        lvDanhSachPhongXN.setAdapter(customRoom);
        btnDatPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cap nhat trang thai phong , luu danh sach phong da dat cua khach hang do
                for (int i = 0; i < StaticConfig.arrayListCheckItem.size(); i++) {
                    StaticConfig.mRoom.child(StaticConfig.arrayListCheckItem.get(i).getMa()).child("tinhtrang").setValue("da dat");
                    String key = StaticConfig.mRoomRented.push().getKey();
                    RoomRented roomRented = new RoomRented(key, "KH2", StaticConfig.arrayListCheckItem.get(i).getMa(),
                            tvXnNhanPhong.getText().toString(), tvXnTraPhong.getText().toString(), StaticConfig.sXacNhan);
                    StaticConfig.mRoomRented.child(key).setValue(roomRented);
                }


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
    }
}