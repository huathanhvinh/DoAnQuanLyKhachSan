package com.example.doanquanlykhachsan.khach_hang;

import static com.example.doanquanlykhachsan.helpers.StaticConfig.mRoom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.adapter.KH_CusTomPhongTheoGiaGio;
import com.example.doanquanlykhachsan.model.Phong;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class KH_DatPhongTheoGio extends AppCompatActivity {
    TextView tvTenPHong, tvLau, tvLoai, tvMoTa, tvGia, tvChiTiet, tvSoPhongTrong, tvGioNhanPhong, tvGiotraPhong;
    ImageView imNhanPhong, imTraPhong;
    ListView lvDanhSachPhongGio;
    Button btnTroVe, btnDatPhong;
    CheckBox cboChonHet, cboGio;

    Boolean bTam = false;


    ArrayList<Phong> arrayList = new ArrayList<>();
    KH_CusTomPhongTheoGiaGio customRoom;
    Phong phong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_dat_phong_theo_gio);

        StaticConfig.arrayListTemporaryRoom.clear();

        setConTrol();
        //lay gio nhan phong hien tai
        final Calendar calendar = Calendar.getInstance();
        int gio = calendar.get(Calendar.HOUR_OF_DAY);
        int phut = calendar.get(Calendar.MINUTE);
        calendar.set(0, 0, 0, gio, phut);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        tvGioNhanPhong.setText(simpleDateFormat.format(calendar.getTime()));
        //lay hien tai sau 1 gio
        calendar.set(0, 0, 0, gio + 1, phut);
        tvGiotraPhong.setText(simpleDateFormat.format(calendar.getTime()));

        StaticConfig.sXacNhan = "gio";
        //ngay tu chi tiet
        StaticConfig.NgayNhanXacNhanPhong = tvGioNhanPhong.getText() + "";
        StaticConfig.NgayNhanXacTraPhong = tvGiotraPhong.getText() + "";
        setEvent();

        Log.e("loai",StaticConfig.sXacNhan);
    }

    private void setEvent() {

        customRoom = new KH_CusTomPhongTheoGiaGio(getApplicationContext(), R.layout.kh_item_ds_phong_gia_gio, arrayList);
        lvDanhSachPhongGio.setAdapter(customRoom);

        cboChonHet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cboChonHet.isChecked()) {
                    StaticConfig.isCheckAll = true;
                } else {
                    StaticConfig.isCheckAll = false;
                }
                customRoom.notifyDataSetChanged();
            }
        });


        //lay danh sach phong trong
        Query query = mRoom.orderByChild("sophong");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                arrayList.removeAll(arrayList);
                for (DataSnapshot ds : snapshot.getChildren()) {
                    phong = ds.getValue(Phong.class);
                    if (ds.child("trangThai").getValue(String.class).equals("trống")) {
                        arrayList.add(phong);
                    }
                }
                customRoom.notifyDataSetChanged();
                //so luong phong trong
                tvSoPhongTrong.setText(arrayList.size() + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });
        imNhanPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonGioNhanPhong();
            }
        });

        imTraPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonGioTraPhong();
            }
        });

        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticConfig.isCheckAll = false;
                startActivity(new Intent(getApplicationContext(), menu_khachhang.class));
            }
        });
        btnDatPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckTimes(tvGioNhanPhong.getText() + "", tvGiotraPhong.getText() + "") == false) {
                    AlertDialog.Builder b = new AlertDialog.Builder(KH_DatPhongTheoGio.this);
                    b.setTitle("Thông báo");
                    b.setMessage("chọn giờ không hợp lệ");
                    b.setPositiveButton("Đồng ý", null);
                    //Tạo dialog
                    AlertDialog dialog = b.create();
                    dialog.show();
                } else {
                    if (StaticConfig.arrayListTemporaryRoom.isEmpty()) {
                        Toast.makeText(KH_DatPhongTheoGio.this, "xin chon phong", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), KH_XacNhanDatPhong.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("ngaynhan", tvGioNhanPhong.getText().toString());
                        bundle.putString("ngaytra", tvGiotraPhong.getText().toString());
                        intent.putExtras(bundle);
                        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private void chonGioNhanPhong() {
        final Calendar calendar = Calendar.getInstance();
        int gio = calendar.get(Calendar.HOUR_OF_DAY);
        int phut = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(0, 0, 0, hourOfDay, minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                tvGioNhanPhong.setText(simpleDateFormat.format(calendar.getTime()));
                //ngay tu chi tiet
                StaticConfig.NgayNhanXacNhanPhong = tvGioNhanPhong.getText() + "";
            }
        }, gio, phut, true);
        timePickerDialog.show();
    }

    private void chonGioTraPhong() {
        final Calendar calendar = Calendar.getInstance();
        int gio = calendar.get(Calendar.HOUR_OF_DAY);
        int phut = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(0, 0, 0, hourOfDay, minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                tvGiotraPhong.setText(simpleDateFormat.format(calendar.getTime()));
                //ngay tu chi tiet
                StaticConfig.NgayNhanXacTraPhong = tvGiotraPhong.getText() + "";
            }
        }, gio + 1, phut, true);
        timePickerDialog.show();
    }

    private void setConTrol() {
        lvDanhSachPhongGio = findViewById(R.id.lvDanhSachPhongGio);
        tvTenPHong = findViewById(R.id.tvTenPhong);
        tvLau = findViewById(R.id.tvLau);
        tvLoai = findViewById(R.id.tvLoai);
        tvMoTa = findViewById(R.id.tvMoTa);
        tvGia = findViewById(R.id.tvGiaNgay);
        tvSoPhongTrong = findViewById(R.id.tvSoPhongTrong);
        tvChiTiet = findViewById(R.id.tvChiTiet);
        tvGioNhanPhong = findViewById(R.id.tvGioNhanPhong);
        tvGiotraPhong = findViewById(R.id.tvGioTraPhong);
        imNhanPhong = findViewById(R.id.imNhanPhong);
        imTraPhong = findViewById(R.id.imTraPhong);
        btnTroVe = findViewById(R.id.btnTroVe);
        btnDatPhong = findViewById(R.id.btnDatPhong);
        cboChonHet = findViewById(R.id.cboChonHet);
    }

    public static boolean CheckTimes(String startTime, String endTime) {

        SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm");

        boolean b = false;

        try {
            if (dfTime.parse(startTime).before(dfTime.parse(endTime))) {
                b = true;  // If start time is before end time.
            } else if (dfTime.parse(startTime).equals(dfTime.parse(endTime))) {
                b = false;  // If two times are equal.
            } else {
                b = false; // If start time is after the end time.
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return b;
    }
}