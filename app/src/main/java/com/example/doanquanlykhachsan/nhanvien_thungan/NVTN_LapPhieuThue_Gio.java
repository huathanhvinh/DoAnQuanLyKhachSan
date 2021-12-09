package com.example.doanquanlykhachsan.nhanvien_thungan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.adapter.KH_CustomDichvu;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.khach_hang.KH_DatPhongTheoGio;
import com.example.doanquanlykhachsan.khach_hang.KH_DatPhongTheoNgay;
import com.example.doanquanlykhachsan.model.DichVu;
import com.example.doanquanlykhachsan.model.PhongDaDat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class NVTN_LapPhieuThue_Gio extends AppCompatActivity {
    EditText edmaPhong, edtenphong, edngaythue, edgiobatdau, edgioketthuc, edtenKH, edSDT;
    Button btnTrove, btnDatPhong;
    private String ngayhientai = "";
    private String giohientai;
    private String gioisau1tieng;
    ImageView imngaybatdau, imgiobatdau, imgiokethuc;
    private String chuoima = "";
    private String chuoiten = "";
    private String chuoidichvu = "";
    private int stt;
    GridView gridView;
    ArrayList<DichVu> data = new ArrayList<>();
    KH_CustomDichvu customDichvu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_lap_phieu_thue_gio);
        seControl();
        setEvnet();
    }

    private void setEvnet() {
        khoitao();
        customDichvu = new KH_CustomDichvu(getApplicationContext(), R.layout.kh_item_ds_dich_vu, data);
        gridView.setAdapter(customDichvu);
        btnDatPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtenKH.getText().toString().equals("")) {
                    edtenKH.setError("Tên khách hàng Không được để trống");
                } else if (edSDT.getText().toString().equals("")) {
                    edSDT.setError("Số điện thoại hàng Không được để trống");
                } else if (edSDT.getText().toString().length() > 11 && edSDT.getText().toString().length() < 10) {
                    edSDT.setError("Số điện thoại hàng có ít nhất 10 số và không quá 11 số");
                } else if (CheckTimes(edgiobatdau.getText() + "", edgioketthuc.getText() + "") == false) {
                    AlertDialog.Builder b = new AlertDialog.Builder(NVTN_LapPhieuThue_Gio.this);
                    b.setTitle("Thông báo");
                    b.setMessage("chọn giờ không hợp lệ");
                    b.setPositiveButton("Đồng ý", null);
                    //Tạo dialog
                    AlertDialog dialog = b.create();
                    dialog.show();
                } else if (CheckDates(edngaythue.getText() + "", ngayhientai + "") == false) {
                    AlertDialog.Builder b = new AlertDialog.Builder(NVTN_LapPhieuThue_Gio.this);
                    b.setTitle("Thông báo");
                    b.setMessage("chọn ngày không hợp lệ!!");
                    b.setPositiveButton("Đồng ý", null);
                    //Tạo dialog
                    AlertDialog dialog = b.create();
                    dialog.show();
                } else {
                   final String key = StaticConfig.mRoomRented.push().getKey();
                    //cap nhat trang thai phong , luu danh sach phong da dat cua khach hang hien tai
                    PhongDaDat phongDaDat = new PhongDaDat();
                    for (int j = 0; j < StaticConfig.arrayListTemporaryService.size(); j++) {
                        chuoidichvu += StaticConfig.arrayListTemporaryService.get(j).getMaFB() + " ";
                    }
                    for (int i = 0; i < StaticConfig.arrayListTemporaryRoom.size(); i++) {
                        phongDaDat = new PhongDaDat(key, "".toString(), chuoima, chuoidichvu,
                                edgiobatdau.getText().toString(), edgioketthuc.getText().toString(), "gio", "", "Đã Xác Nhận", ngayhientai, edSDT.getText().toString(), edtenKH.getText().toString(),stt + 1);
                    }
                    Intent intent = new Intent(getApplicationContext(), NVTN_xacnhandatphong.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("chitiet", phongDaDat);
                    bundle.putSerializable("ma", key);
                    intent.putExtras(bundle);
                    intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            }
        });
        imgiokethuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonGioTraPhong();
            }
        });
        imgiobatdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonGioNhanPhong();
            }
        });
        imngaybatdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonNgayNhanPhong();
            }
        });
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void khoitao() {
        layngayhientai();
        laygioihientai();
        chuoima = "";
        chuoiten = "";
        for (int i = 0; i < StaticConfig.arrayListTemporaryRoom.size(); i++) {
            chuoima += StaticConfig.arrayListTemporaryRoom.get(i).getMaPhong() + " ";
            chuoiten += StaticConfig.arrayListTemporaryRoom.get(i).getTenPhong() + " ";
        }
        edmaPhong.setText(chuoima);
        edtenphong.setText(chuoiten);
        edngaythue.setText(ngayhientai);
        edgiobatdau.setText(giohientai);
        edgioketthuc.setText(gioisau1tieng);
        StaticConfig.mRoomRented.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    stt = Integer.parseInt(ds.child("stt").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        StaticConfig.mDichVu.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    DichVu dv = ds.getValue(DichVu.class);
                    data.add(dv);
                }
                customDichvu.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void seControl() {
        edmaPhong = findViewById(R.id.txtMaPhong);
        edtenphong = findViewById(R.id.txtTenPhong);
        edngaythue = findViewById(R.id.txtNgayDen);
        edgiobatdau = findViewById(R.id.txtGioBatDau);
        edgioketthuc = findViewById(R.id.txtGioKetThuc);
        edtenKH = findViewById(R.id.txtTenKH);
        edSDT = findViewById(R.id.txtSdt);
        btnTrove = findViewById(R.id.btnTroVe);
        btnDatPhong = findViewById(R.id.btnDatPhong);
        imngaybatdau = findViewById(R.id.ngaybatdau);
        imgiobatdau = findViewById(R.id.giobatdau);
        imgiokethuc = findViewById(R.id.giokethuc);
        gridView = findViewById(R.id.gv_dv);
    }

    private void layngayhientai() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        calendar.set(nam, thang, ngay);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ngayhientai = simpleDateFormat.format(calendar.getTime());
    }

    private void laygioihientai() {
        //lay gio nhan phong hien tai
        final Calendar calendar = Calendar.getInstance();
        int gio = calendar.get(Calendar.HOUR_OF_DAY);
        int phut = calendar.get(Calendar.MINUTE);
        calendar.set(0, 0, 0, gio, phut);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        giohientai = simpleDateFormat.format(calendar.getTime());

        //lay hien tai sau 1 gio
        calendar.set(0, 0, 0, gio + 1, phut);
        gioisau1tieng = simpleDateFormat.format(calendar.getTime());
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
                edngaythue.setText(simpleDateFormat.format(calendar.getTime()));
                //ngay tu chi tiet
                StaticConfig.NgayNhanXacNhanPhong = edngaythue.getText() + "";
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
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
                edgiobatdau.setText(simpleDateFormat.format(calendar.getTime()));
                //ngay tu chi tiet
                StaticConfig.NgayNhanXacNhanPhong = edgiobatdau.getText() + "";
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
                edgioketthuc.setText(simpleDateFormat.format(calendar.getTime()));
                //ngay tu chi tiet
                StaticConfig.NgayNhanXacTraPhong = edgioketthuc.getText() + "";
            }
        }, gio + 1, phut, true);
        timePickerDialog.show();
    }

    //dinh dang chuoi thanh ngay
    public static boolean CheckDates(String startDate, String endDate) {

        SimpleDateFormat dfDate = new SimpleDateFormat("dd/MM/yyyy");

        boolean b = false;

        try {
            if (dfDate.parse(startDate).before(dfDate.parse(endDate))) {
                b = true;  // If start date is before end date.
            } else if (dfDate.parse(startDate).equals(dfDate.parse(endDate))) {
                b = true;  // If two dates are equal.
            } else {
                b = false; // If start date is after the end date.
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return b;
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