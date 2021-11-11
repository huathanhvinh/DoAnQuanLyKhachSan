package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
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
    private TextView phongdathue, hoten, songayo, ngaynhan, ngaytra;
    int ngay, thang, nam;
    String thoigian;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_tra_phong);
        setControl();
        setEvnet();
    }

    private void setEvnet() {
        btntraphong.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                //tra phong ve trang thai tronng và xoá khỏi danh sách phòng thuê
//                StaticConfig.mRoomRented.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot ds : snapshot.getChildren())
//                            if (ds.child("sMaKH").getValue().toString().equals(StaticConfig.currentuser)){
//                                StaticConfig.mRoom.child(ds.child("sMaPH").getValue(String.class)).child("tinhtrang").setValue("trống");
//                                StaticConfig.mRoomRented.child(ds.child("sMa").getValue(String.class)).setValue(null);
//                            }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//                new AlertDialog.Builder(KH_tra_phong.this)
//                        .setTitle("Trả phòng ")
//                        .setMessage("Bạn có chắc trả phòng không??")
//                        // Specifying a listener allows you to take an action before dismissing the dialog.
//                        // The dialog is automatically dismissed when a dialog button is clicked.
//                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                            @RequiresApi(api = Build.VERSION_CODES.M)
//                            public void onClick(DialogInterface dialog, int which) {
//                                thongbao();
//                            }
//                        })
//
//                        // A null listener allows the button to dismiss the dialog and take no further action.
//                        .setNegativeButton(android.R.string.no, null)
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .show();
//                startActivity(new Intent(getApplicationContext(), menu_khachhang.class));
                thongbao();
            }
        });
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //Chưa show
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void thongbao() {
//        NotificationCompat.Builder notification = new NotificationCompat.Builder(KH_tra_phong.this, "Thong bao")
//                .setSmallIcon(R.drawable.logo)
//                .setContentTitle("Trả phòng")
//                .setContentText("Trả Phòng thành công")
//                .setPriority(NotificationCompat.PRIORITY_HIGH);
//        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        manager.notify(0, notification.build());
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, "channelId")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background))
                        .setContentTitle(getString(R.string.project_Id))
                        .setContentText("Trả phòng")
                        .setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setPriority(NotificationManager.IMPORTANCE_HIGH);


        NotificationManager notificationManager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "channelId",
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notificationBuilder.build());
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//        builder.setSmallIcon(R.drawable.logo)
//                .setContentTitle(getString(R.string.hello))
//                .setContentIntent(notificationPendingIntent)
//                .setDefaults(NotificationCompat.DEFAULT_ALL)
//                .setStyle(bigText)
//                .setPriority(NotificationCompat.PRIORITY_HIGH);
//        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        manager.notify(0, notification.build());

    }

    private void setControl() {
        phongdathue = findViewById(R.id.tvsophong);
        hoten = findViewById(R.id.tvhoten);
        songayo = findViewById(R.id.tvsongay);
        ngaynhan = findViewById(R.id.tvNgayNhanPhong);
        ngaytra = findViewById(R.id.tvNgayTraPhong);
        btntraphong = findViewById(R.id.btntraphong);
        khoitao();
        btntrove = findViewById(R.id.btntrove);
    }

    private void khoitao() {
        String tenhientai = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        StaticConfig.mUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("email").getValue(String.class).equals(tenhientai)) {
                        StaticConfig.currentuser = ds.child("id").getValue(String.class);
                        hoten.setText(ds.child("name").getValue(String.class));
                    }
                }
                if (hoten.getText().toString().isEmpty()) {
                    hoten.setText(tenhientai);
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
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("sMaKH").getValue().toString().equals(StaticConfig.currentuser)) {
                        ngaynhan.setText(ds.child("sNgayNhan").getValue(String.class));
                        ngaytra.setText(ds.child("sNgayTra").getValue(String.class));
                        phongthue += ds.child("sMaPH").getValue(String.class) + " ";

                        if (ds.child("sManHinh").getValue(String.class).equals("ngay")) {
                            songayo.setText(thoigian + " ngay");
                            DateDifference();
                        } else {
                            songayo.setText(thoigian + " gio");
                            try {
                                TimeDifference();
                            } catch (ParseException e) {
                                e.printStackTrace();
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
            Toast.makeText(getApplicationContext(), "Khong co phong de tra", Toast.LENGTH_SHORT).show();
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
            thoigian = daysBetween(cal1.getTime(), cal2.getTime()) + 1 + "";
        } else {
            Toast.makeText(getApplicationContext(), "Khong co phong de tra", Toast.LENGTH_SHORT).show();
        }

    }

    public int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public int timeBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60)) % 24;
    }

}