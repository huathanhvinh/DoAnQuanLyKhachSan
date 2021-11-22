package com.example.doanquanlykhachsan;

import static com.example.doanquanlykhachsan.helpers.StaticConfig.mRoom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.Phong;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class KH_DatPhongTheoNgay extends AppCompatActivity {

    TextView tvTenPHong, tvLau, tvLoai, tvSoluong, tvGia, tvChiTiet, tvSoPhongTrong, tvNgayNhanPhong, tvNgaytraPhong;
    ImageView imNhanPhong, imTraPhong;
    ListView lvDanhSachPhongNgay;
    Button btnTroVe, btnDatPhong;
    CheckBox cboChonHet;


    ArrayList<Phong> arrayList = new ArrayList<>();
    KH_CusTomPhongTheoGiaNgay customRoom;
    Phong phong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_dat_phong_theo_ngay);
        StaticConfig.arrayListTemporaryRoom.clear();

        setConTrol();
        //lay ngay hien tai
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        calendar.set(nam, thang, ngay);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        tvNgayNhanPhong.setText(simpleDateFormat.format(calendar.getTime()));
        //lay ngay hien tai sau 1 ngay
        calendar.set(nam, thang, ngay + 1);
        tvNgaytraPhong.setText(simpleDateFormat.format(calendar.getTime()));
        //phan biet man hinh
        StaticConfig.sXacNhan = "ngay";

        //ngay tu chi tiet
        StaticConfig.NgayNhanXacNhanPhong = tvNgayNhanPhong.getText() + "";
        StaticConfig.NgayNhanXacTraPhong = tvNgaytraPhong.getText() + "";

        setEvent();

    }

    private void setEvent() {

        customRoom = new KH_CusTomPhongTheoGiaNgay(getApplicationContext(), R.layout.kh_item_ds_phong_gia_ngay, arrayList);
        lvDanhSachPhongNgay.setAdapter(customRoom);

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
                chonNgayNhanPhong();
            }
        });

        imTraPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgayTraPhong();
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
                if (CheckDates(tvNgayNhanPhong.getText() + "", tvNgaytraPhong.getText() + "") == false) {
                    AlertDialog.Builder b = new AlertDialog.Builder(KH_DatPhongTheoNgay.this);
                    b.setTitle("Thông báo");
                    b.setMessage("chọn ngày không hợp lệ");
                    b.setPositiveButton("Đồng ý", null);
                    //Tạo dialog
                    AlertDialog dialog = b.create();
                    dialog.show();
                } else {
                    if (StaticConfig.arrayListTemporaryRoom.isEmpty()) {
                        Toast.makeText(KH_DatPhongTheoNgay.this, "xin chon phong", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), KH_XacNhanDatPhong.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("ngaynhan", tvNgayNhanPhong.getText().toString());
                        bundle.putString("ngaytra", tvNgaytraPhong.getText().toString());
                        intent.putExtras(bundle);
                        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
            }
        });
        lvDanhSachPhongNgay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(KH_DatPhongTheoNgay.this, "dung" + position, Toast.LENGTH_LONG).show();
            }
        });
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

    }

    private void chonNgayNhanPhong() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                tvNgayNhanPhong.setText(simpleDateFormat.format(calendar.getTime()));
                //ngay tu chi tiet
                StaticConfig.NgayNhanXacNhanPhong = tvNgayNhanPhong.getText() + "";

            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    private void chonNgayTraPhong() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                tvNgaytraPhong.setText(simpleDateFormat.format(calendar.getTime()));
                //ngay tu chi tiet
                StaticConfig.NgayNhanXacTraPhong = tvNgaytraPhong.getText() + "";
            }
        }, nam, thang, ngay + 1);
        datePickerDialog.show();
    }

    private void setConTrol() {
        lvDanhSachPhongNgay = findViewById(R.id.lvDanhSachPhongNgay);
        tvTenPHong = findViewById(R.id.tvTenPhong);
        tvLau = findViewById(R.id.tvLau);
        tvLoai = findViewById(R.id.tvLoai);
        tvSoluong = findViewById(R.id.tvMoTa);
        tvGia = findViewById(R.id.tvGiaNgay);
        tvSoPhongTrong = findViewById(R.id.tvSoPhongTrong);
        tvChiTiet = findViewById(R.id.tvChiTiet);
        tvNgayNhanPhong = findViewById(R.id.tvNgayNhanPhong);
        tvNgaytraPhong = findViewById(R.id.tvNgayTraPhong);
        imNhanPhong = findViewById(R.id.imNhanPhong);
        imTraPhong = findViewById(R.id.imTraPhong);
        btnTroVe = findViewById(R.id.btnTroVe);
        btnDatPhong = findViewById(R.id.btnDatPhong);
        cboChonHet = findViewById(R.id.cboChonHet);
    }

    //dinh dang chuoi thanh ngay
    public static boolean CheckDates(String startDate, String endDate) {

        SimpleDateFormat dfDate = new SimpleDateFormat("dd/MM/yyyy");

        boolean b = false;

        try {
            if (dfDate.parse(startDate).before(dfDate.parse(endDate))) {
                b = true;  // If start date is before end date.
            } else if (dfDate.parse(startDate).equals(dfDate.parse(endDate))) {
                b = false;  // If two dates are equal.
            } else {
                b = false; // If start date is after the end date.
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return b;
    }
}