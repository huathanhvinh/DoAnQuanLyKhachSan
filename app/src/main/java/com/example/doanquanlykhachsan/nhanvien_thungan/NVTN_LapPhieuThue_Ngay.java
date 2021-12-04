package com.example.doanquanlykhachsan.nhanvien_thungan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.adapter.KH_CustomDichvu;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.DichVu;
import com.example.doanquanlykhachsan.model.PhongDaDat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class NVTN_LapPhieuThue_Ngay extends AppCompatActivity {
    EditText edmaPhong, edtenphong, edngayden, edngaydi, edtenKH, edSDT;
    Button btnTrove, btnDatPhong;
    private String ngayhientai = "";
    private String ngaysau1ngay = "";

    ImageView imNgayBatDau, imgNgayKetThuc;
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
        setContentView(R.layout.activity_nvtn_lap_phieu_thue_ngay);
        seControl();
        setEvnet();
    }

    private void seControl() {
        edmaPhong = findViewById(R.id.txtMaPhong);
        edtenphong = findViewById(R.id.txtTenPhong);
        edngayden = findViewById(R.id.txtNgayDen);
        edngaydi = findViewById(R.id.txtNgaydi);
        edtenKH = findViewById(R.id.txtTenKH);
        edSDT = findViewById(R.id.txtSdt);
        btnTrove = findViewById(R.id.btnTroVe);
        btnDatPhong = findViewById(R.id.btnDatPhong);
        imNgayBatDau = findViewById(R.id.ngaybatdau);
        imgNgayKetThuc = findViewById(R.id.giobatdau);
        gridView = findViewById(R.id.gv_dv);
    }

    private void setEvnet() {
        khoitao();
        customDichvu = new KH_CustomDichvu(getApplicationContext(), R.layout.kh_item_ds_dich_vu, data);
        gridView.setAdapter(customDichvu);
        imNgayBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonNgayNhanPhong();
            }
        });
        imgNgayKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonNgayTraPong();
            }
        });
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NV_chonphonglapphieuthue.class));
            }
        });
        btnDatPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtenKH.getText().toString().equals("")) {
                    edtenKH.setError("Tên khách hàng Không được để trống");
                } else if (edSDT.getText().toString().equals("")) {
                    edSDT.setError("Số điện thoại hàng Không được để trống");
                } else if (edSDT.getText().toString().length() > 11 && edSDT.getText().toString().length() < 10) {
                    edSDT.setError("Số điện thoại hàng có ít nhất 10 số và không quá 11 số");

                } else if (CheckDates(edngayden.getText() + "", edngaydi.getText().toString() + "") == false || CheckDate(edngayden.getText() + "", ngayhientai + "") == false) {
                    AlertDialog.Builder b = new AlertDialog.Builder(NVTN_LapPhieuThue_Ngay.this);
                    b.setTitle("Thông báo");
                    b.setMessage("chọn ngày không hợp lệ!!");
                    b.setPositiveButton("Đồng ý", null);
                    //Tạo dialog
                    AlertDialog dialog = b.create();
                    dialog.show();
                } else {
                    String key = StaticConfig.mRoomRented.push().getKey();
                    //cap nhat trang thai phong , luu danh sach phong da dat cua khach hang hien tai
                    PhongDaDat phongDaDat = new PhongDaDat();
                    for (int j = 0; j < StaticConfig.arrayListTemporaryService.size(); j++) {
                        chuoidichvu += StaticConfig.arrayListTemporaryService.get(j).getMaFB() + " ";
                    }
                    for (int i = 0; i < StaticConfig.arrayListTemporaryRoom.size(); i++) {
                        phongDaDat = new PhongDaDat(key,"", chuoima, chuoidichvu,
                                edngayden.getText().toString(), edngaydi.getText().toString(), "ngay", "", "Đã Xác Nhận", ngayhientai, edSDT.getText().toString(),  edtenKH.getText().toString(),stt + 1);
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
    }

    private void khoitao() {
        layngayhientai();
        layngaysau1nhgay();
        chuoima = "";
        chuoiten = "";
        for (int i = 0; i < StaticConfig.arrayListTemporaryRoom.size(); i++) {
            chuoima += StaticConfig.arrayListTemporaryRoom.get(i).getMaPhong() + " ";
            chuoiten += StaticConfig.arrayListTemporaryRoom.get(i).getTenPhong() + " ";
        }
        edmaPhong.setText(chuoima);
        edtenphong.setText(chuoiten);
        edngayden.setText(ngayhientai);
        edngaydi.setText(ngaysau1ngay);

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

    private void layngayhientai() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        calendar.set(nam, thang, ngay);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ngayhientai = simpleDateFormat.format(calendar.getTime());
    }

    private void layngaysau1nhgay() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        calendar.set(nam, thang, ngay + 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ngaysau1ngay = simpleDateFormat.format(calendar.getTime());
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
                edngayden.setText(simpleDateFormat.format(calendar.getTime()));
                //ngay tu chi tiet
                StaticConfig.NgayNhanXacNhanPhong = edngayden.getText() + "";
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    private void chonNgayTraPong() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edngaydi.setText(simpleDateFormat.format(calendar.getTime()));
                //ngay tu chi tiet
                StaticConfig.NgayNhanXacNhanPhong = edngaydi.getText() + "";
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
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
    //dinh dang chuoi thanh ngay
    public static boolean CheckDate(String startDate, String endDate) {

        SimpleDateFormat dfDate = new SimpleDateFormat("dd/MM/yyyy");

        boolean b = false;

        try {
            if (dfDate.parse(startDate).before(dfDate.parse(endDate))) {
                b = false;  // If start date is before end date.
            } else if (dfDate.parse(startDate).equals(dfDate.parse(endDate))) {
                b = true;  // If two dates are equal.
            } else {
                b = true; // If start date is after the end date.
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return b;
    }

}