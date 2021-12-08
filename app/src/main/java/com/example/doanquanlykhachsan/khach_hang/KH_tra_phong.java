package com.example.doanquanlykhachsan.khach_hang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.MainActivity;
import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.DichVu;
import com.example.doanquanlykhachsan.model.KhachHang;
import com.example.doanquanlykhachsan.model.PhongDaDat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class KH_tra_phong extends AppCompatActivity {
    private Button btntrove, btntraphong;
    private TextView phongdathue, hoten, songayo, ngaynhan, ngaytra, dichvu;
    int ngay, thang, nam;
    String thoigian;
    String tenDichVu = "";
    String chuoiPhongDadat = "";
    String chuoiMaTraPhong = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_tra_phong);
        setControl();
        setEvnet();
    }

    private void setEvnet() {
        btntraphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    new AlertDialog.Builder(KH_tra_phong.this)
                            .setTitle("Trả Phòng")
                            .setMessage("Bạn có chắc trả Phòng không??")
                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    traPhong();
                                }
                            })
                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

            }
        });
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void traPhong() {
        StaticConfig.mRoomRented.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    PhongDaDat da = ds.getValue(PhongDaDat.class);
                    String[] parts;
                    parts = chuoiPhongDadat.split(" ");
                    for (String maPh : parts) {
                        if (StaticConfig.currentuser.equals(da.getMaKH()) && da.getMaFB().equals(maPh)) {
                            StaticConfig.mRoomRented.child(da.getMaFB()).child("xacnhan").setValue("Trả Phòng");
                        }
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        new AlertDialog.Builder(KH_tra_phong.this)
                .setTitle("Trả Phòng")
                .setMessage("Vui lòng chờ nhân viên xác nhận")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), menu_khachhang.class));
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void setControl() {
        phongdathue = findViewById(R.id.tvsophong);
        hoten = findViewById(R.id.tvhoten);
        songayo = findViewById(R.id.tvsongay);
        ngaynhan = findViewById(R.id.tvNgayNhanPhong);
        ngaytra = findViewById(R.id.tvNgayTraPhong);
        btntraphong = findViewById(R.id.btntraphong);
        dichvu = findViewById(R.id.tvcacdichvu);
        khoitao();
        btntrove = findViewById(R.id.btntrove);
    }

    private void khoitao() {
        StaticConfig.mKhachHang.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()) {
                    KhachHang kh = ds.getValue(KhachHang.class);
                    if(kh.getSdtKH().equals(StaticConfig.currentphone))
                    {
                        hoten.setText(kh.getTenKH());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        StaticConfig.mRoomRented.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String phongthue = "";
                String chuoiDichVu = "";
                for (DataSnapshot ds : snapshot.getChildren()) {
                    PhongDaDat da = ds.getValue(PhongDaDat.class);
                    if (da.getMaKH().equals(StaticConfig.currentuser)) {
                        if (da.getXacnhan().equals("Đã Xác Nhận") || da.getXacnhan().equals("Trả Phòng")) {
                            if (da.getXacnhan().equals("Đã Xác Nhận")) {
                                chuoiPhongDadat += da.getMaFB() + " ";
                            }
                            tenDichVu = "";
                            ngaynhan.setText(da.getThoiGianNhanPH());
                            ngaytra.setText(da.getThoiGianTraPH());
                            phongthue += da.getMaPhong() + " ";
                            chuoiDichVu = da.getMaDichVu();
                            String[] parts;
                            parts = chuoiDichVu.split(" ");
                            for (String w : parts) {
                                StaticConfig.mDichVu.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot ds : snapshot.getChildren()) {
                                            DichVu dv = ds.getValue(DichVu.class);
                                            if (w.equals(dv.getMaFB())) {
                                                tenDichVu += dv.getTenDV() + "\n";
                                                dichvu.setText(tenDichVu);
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        throw error.toException();
                                    }
                                });
                            }

                            if (da.getManHinh().equals("ngay")) {
                                DateDifference();
                                songayo.setText(thoigian + " Ngày");
                            } else {
                                try {
                                    TimeDifference();
                                    songayo.setText(thoigian + " Giờ");
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }


                phongdathue.setText(phongthue);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void TimeDifference() throws ParseException {
        if (!ngaynhan.getText().toString().isEmpty() && !ngaytra.getText().toString().isEmpty()) {
            String time1 = ngaynhan.getText().toString();
            String time2 = ngaytra.getText().toString();

            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            Date date1 = format.parse(time1);
            Date date2 = format.parse(time2);
            thoigian = timeBetween(date1, date2) + "";
        } else {
            new AlertDialog.Builder(KH_tra_phong.this)
                    .setTitle("Trả Phòng ")
                    .setMessage("Không có phòng để trả !!")
                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }

    }

    public void DateDifference() {
        if (!ngaynhan.getText().toString().isEmpty() && !ngaytra.getText().toString().isEmpty()) {
            Calendar cal1 = new GregorianCalendar();
            Calendar cal2 = new GregorianCalendar();
            ngaynhan.getText().toString();
            String[] parts;
            parts = ngaynhan.getText().toString().split("\\/");
            ngay = Integer.parseInt(parts[0]);
            thang = Integer.parseInt(parts[1]);
            nam = Integer.parseInt(parts[2]);
            cal1.set(nam, thang, ngay);
            parts = ngaytra.getText().toString().split("\\/");
            ngay = Integer.parseInt(parts[0]);
            thang = Integer.parseInt(parts[1]);
            nam = Integer.parseInt(parts[2]);
            cal2.set(nam, thang, ngay);
            thoigian = daysBetween(cal1.getTime(), cal2.getTime()) + "";
        } else {
            Toast.makeText(getApplicationContext(), "Không có phòng để trả", Toast.LENGTH_SHORT).show();
        }

    }

    public int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public int timeBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60)) % 24;
    }

}