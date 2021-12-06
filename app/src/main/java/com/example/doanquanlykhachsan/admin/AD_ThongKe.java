package com.example.doanquanlykhachsan.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.HoaDon;
import com.example.doanquanlykhachsan.adapter.*;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AD_ThongKe extends AppCompatActivity {
    Button btnTroVe;
    GridView gridView;
    ImageView imTimKiem;
    ArrayList<HoaDon> data = new ArrayList<>();
    Adapter_thongke adapter;
    TextView tvTongtien;
    TextView tvNgayBatDau, tvNgayKetThuc;
    ImageButton imNgaybatdau, imNgayKetThuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_thong_ke);
        setControl();
        setEvent();
    }

    private void setControl() {
        btnTroVe = findViewById(R.id.btnTroVe);
        gridView = findViewById(R.id.listhoadon);
        tvTongtien = findViewById(R.id.tvTongTien);
        imTimKiem = findViewById(R.id.imTimKiem);
        tvNgayBatDau = findViewById(R.id.tvNgayBatDau);
        tvNgayKetThuc = findViewById(R.id.txNgayKetThuc);
        imNgaybatdau = findViewById(R.id.imNgayBatDau);
        imNgayKetThuc = findViewById(R.id.imNgayKetThuc);
        adapter = new Adapter_thongke(getApplicationContext(), R.layout.item_hoadon, data);
        gridView.setAdapter(adapter);

    }

    private void khoitao() {
        StaticConfig.mHoaDon.orderByChild("stt").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    HoaDon hd = ds.getValue(HoaDon.class);
                    if (CheckDates(tvNgayBatDau.getText().toString(), hd.getNgaylap()) &&
                            CheckDates(hd.getNgaylap(), tvNgayKetThuc.getText().toString())) {
                        data.add(0,hd);

                    }
                }
                //Tong tien
                float Tong = 0;
                for (int i = 0; i < data.size(); i++) {
                    Tong += data.get(i).getTongTien();
                }
                DecimalFormat toTheFormat = new DecimalFormat("###,###,###.#");
                tvTongtien.setText(toTheFormat.format(Tong) + " VNĐ");
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void seachDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_seach);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setGravity(Gravity.TOP);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        EditText seach = dialog.findViewById(R.id.editTimKiem);
        ImageView imTimkiem = dialog.findViewById(R.id.imTimkiem);
        imTimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timkiem = seach.getText().toString().toLowerCase();
                ArrayList<HoaDon> temp = new ArrayList<>();
                StaticConfig.mHoaDon.orderByChild("stt").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        data.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            HoaDon hd = ds.getValue(HoaDon.class);
                            StaticConfig.mNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot ds : snapshot.getChildren()) {
                                        NhanVien nv = ds.getValue(NhanVien.class);
                                        if (nv.getMaFB().equals(hd.getMaNV())) {
                                            if (nv.getTenNV().toLowerCase().contains(timkiem)) {
                                                if (CheckDates(tvNgayBatDau.getText().toString(), hd.getNgaylap()) &&
                                                        CheckDates(hd.getNgaylap(), tvNgayKetThuc.getText().toString())) {
                                                    data.add(0,hd);
                                                }
                                            }
                                        }
                                    }

                                    //Tong tien
                                    float Tong = 0;
                                    for (int i = 0; i < data.size(); i++) {
                                        Tong += data.get(i).getTongTien();
                                    }
                                    DecimalFormat toTheFormat = new DecimalFormat("###,###,###.#");
                                    tvTongtien.setText(toTheFormat.format(Tong) + " VNĐ");
                                    adapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }

                        dialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        dialog.show();
    }

    private void setEvent() {
        khoitao();
        layngayhientai();
        layngaysau1thang();
        imNgaybatdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonNgayBatDau();

            }
        });
        imNgayKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonNgayKetThuc();

            }
        });
        imTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seachDialog();
            }
        });
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void chonNgayBatDau() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                tvNgayBatDau.setText(simpleDateFormat.format(calendar.getTime()));
                //ngay tu chi tiet
                StaticConfig.NgayNhanXacNhanPhong = tvNgayBatDau.getText() + "";
                khoitao();
            }
        }, nam, thang, ngay);
        datePickerDialog.show();

    }

    private void chonNgayKetThuc() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                tvNgayKetThuc.setText(simpleDateFormat.format(calendar.getTime()));
                //ngay tu chi tiet
                StaticConfig.NgayNhanXacTraPhong = tvNgayKetThuc.getText() + "";
                khoitao();
            }
        }, nam, thang, ngay + 1);
        datePickerDialog.show();

    }

    private void layngaysau1thang() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        calendar.set(nam, thang + 1, ngay);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        tvNgayKetThuc.setText(simpleDateFormat.format(calendar.getTime()));
    }

    private void layngayhientai() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        calendar.set(nam, thang, ngay);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        tvNgayBatDau.setText(simpleDateFormat.format(calendar.getTime()));
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

}