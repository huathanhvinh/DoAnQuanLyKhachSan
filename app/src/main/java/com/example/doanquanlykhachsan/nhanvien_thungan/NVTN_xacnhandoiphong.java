package com.example.doanquanlykhachsan.nhanvien_thungan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.adapter.Adapter_HD_DV;
import com.example.doanquanlykhachsan.adapter.Adapter_PhongThue;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.DichVu;
import com.example.doanquanlykhachsan.model.DoiPhong;
import com.example.doanquanlykhachsan.model.KhachHang;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.example.doanquanlykhachsan.model.Phong;
import com.example.doanquanlykhachsan.model.PhongDaDat;
import com.example.doanquanlykhachsan.model.ThongBao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class NVTN_xacnhandoiphong extends AppCompatActivity {
    Button btnXacNhan, btnTroVe, btnHuy;
    TextView tvmachon, tvmadoi, tvTenKH, tvTongtien, tvsdt;
    private TextView tvGhiChu;
    Button call;
    PhongDaDat chitiet;
    private String thoigian;
    int ngay, thang, nam;
    private float Tongtien;
    int solan = 1;

    private int id;
    private String tennv;
    private String ma;
    ArrayList<Phong> data = new ArrayList<>();
    ArrayList<DichVu> dichvu = new ArrayList<>();
    Adapter_PhongThue adapter;
    Adapter_HD_DV adapterdv;
    ListView lv;
    ListView lvdv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_xacnhandoiphong);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new androidx.appcompat.app.AlertDialog.Builder(NVTN_xacnhandoiphong.this)
                        .setTitle("Đổi Phòng")
                        .setMessage("Huỷ yêu cầu  ??")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String key = StaticConfig.mThongBao.push().getKey();
                                ThongBao tb = new ThongBao(id + 1, key, "Huỷ Đổi Phòng", "Chưa xác nhận", "", tennv, chitiet.getMaKH());
                                StaticConfig.mThongBao.child(key).setValue(tb);
                                //remove doi phong
                                StaticConfig.mDoiPhong.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot ds : snapshot.getChildren()) {
                                            DoiPhong dp = ds.getValue(DoiPhong.class);
                                            if (dp.getMaPhieu().equals(chitiet.getMaFB())) {
                                                StaticConfig.mDoiPhong.child(dp.getMaFB()).removeValue();
                                                startActivity(new Intent(getApplicationContext(), NVTN_Thong_Bao.class));
                                            }

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new androidx.appcompat.app.AlertDialog.Builder(NVTN_xacnhandoiphong.this)
                        .setTitle("Đổi Phòng")
                        .setMessage("xác nhận ??")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String key = StaticConfig.mThongBao.push().getKey();
                                ThongBao tb = new ThongBao(id + 1, key, "Đổi Phòng ", "Chưa xác nhận", "", tennv, chitiet.getMaKH());
                                StaticConfig.mThongBao.child(key).setValue(tb);
                                //remove doi phong
                                StaticConfig.mDoiPhong.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot ds : snapshot.getChildren()) {
                                            DoiPhong dp = ds.getValue(DoiPhong.class);
                                            if (dp.getMaPhieu().equals(chitiet.getMaFB())) {
                                               //cap nhap
                                                StaticConfig.mRoom.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        for(DataSnapshot ds:snapshot.getChildren()){
                                                            Phong p = ds.getValue(Phong.class);
                                                            if(p.getMaPhong().equals(dp.getMaPhongchon())){
                                                                StaticConfig.mRoom.child(p.getMaFB()).child("trangThai").setValue("Trống");
                                                            }
                                                            if(p.getMaPhong().equals(dp.getGetMaPhong())){
                                                                StaticConfig.mRoom.child(p.getMaFB()).child("trangThai").setValue("Đã Đặt Phòng");
                                                            }

                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });
                                                String str = chitiet.getMaPhong();
                                                String replacedStr = str.replaceAll(tvmachon.getText().toString(), tvmadoi.getText().toString());
                                                StaticConfig.mRoomRented.child(chitiet.getMaFB()).child("maPhong").setValue(replacedStr);

                                                StaticConfig.mDoiPhong.child(dp.getMaFB()).removeValue();
                                                startActivity(new Intent(getApplicationContext(), NVTN_Thong_Bao.class));
                                            }

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
    }

    private void tenNhanvien() {
        StaticConfig.mNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    NhanVien nv = ds.getValue(NhanVien.class);
                    if (nv.getSoDienThoai().equals(StaticConfig.currentphone)) {
                        tennv = nv.getTenNV();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setControl() {
        layid();
        tenNhanvien();
        chitiet = (PhongDaDat) getIntent().getSerializableExtra("chitiet");
        ma = getIntent().getStringExtra("ma");
        btnHuy = findViewById(R.id.btnHuy);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        tvTenKH = findViewById(R.id.tvKH);
        tvmachon = findViewById(R.id.machon);
        tvmadoi = findViewById(R.id.madoi);


        lv = findViewById(R.id.lvthue);
        lvdv = findViewById(R.id.lvDv);
        btnHuy = findViewById(R.id.btnHuy);
        tvTongtien = findViewById(R.id.tvTongTien);
        tvsdt = findViewById(R.id.txtSdt);
        btnTroVe = findViewById(R.id.btnTroVe);
        call = findViewById(R.id.btncall);
        tvGhiChu = findViewById(R.id.tvGhiChu);

        adapter = new Adapter_PhongThue(getApplicationContext(), R.layout.items_phongthue, data);
        lv.setAdapter(adapter);
        adapterdv = new Adapter_HD_DV(getApplicationContext(), R.layout.item_dv, dichvu);
        lvdv.setAdapter(adapterdv);
        khoitao();

    }

    private void khoitao() {
        layid();
        chitiet = (PhongDaDat) getIntent().getSerializableExtra("chitiet");
        StaticConfig.mKhachHang.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    KhachHang kh = ds.getValue(KhachHang.class);
                    if (kh.getSdtKH().equals(chitiet.getSdt())) {
                        tvTenKH.setText(kh.getTenKH());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //lấy thời gian
        if (chitiet.getManHinh().equals("ngay")) {
            DateDifference();
            StaticConfig.songay = thoigian;
        } else {
            TimeDifference();
            StaticConfig.songay = thoigian;
        }
        if (chitiet.getManHinh().equals("ngay")) {
            StaticConfig.Loai = "ngay";
        }
        //Lấy Phòng thuê
        StaticConfig.mRoomRented.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    PhongDaDat da = ds.getValue(PhongDaDat.class);
                    if (da.getMaFB().equals(chitiet.getMaFB())) {
                        String[] parts1;
                        String chuoimaphong = da.getMaPhong();
                        parts1 = chuoimaphong.split(" ");
                        for (String maPh : parts1) {
                            StaticConfig.mRoom.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot ds : snapshot.getChildren()) {
                                        Phong p = ds.getValue(Phong.class);
                                        if (p.getMaPhong().equals(maPh)) {
                                            data.add(p);
                                            if (StaticConfig.Loai.equals("ngay")) {
                                                Tongtien += p.getGiaNgay();
                                            } else {
                                                Tongtien += p.getGiaGio();
                                            }
                                        }
                                    }

                                    solan = Integer.parseInt(StaticConfig.songay);
                                    Tongtien = Tongtien * solan;

                                    adapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                        String[] parts2;
                        String chuoiDichvu = da.getMaDichVu();
                        parts2 = chuoiDichvu.split(" ");
                        for (String maDV : parts2) {
                            StaticConfig.mDichVu.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot ds : snapshot.getChildren()) {
                                        DichVu dv = ds.getValue(DichVu.class);
                                        if (dv.getMaFB().equals(maDV)) {
                                            dichvu.add(dv);
                                            Tongtien += dv.getGiaDV();
                                        }
                                    }
                                    adapterdv.notifyDataSetChanged();
                                    //Tong tien
                                    DecimalFormat toTheFormat = new DecimalFormat("###,###,###.#");
                                    tvTongtien.setText(toTheFormat.format(Tongtien) + " VNĐ");
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        tvsdt.setText(chitiet.getSdt());
        //lay phong doi
        StaticConfig.mDoiPhong.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    DoiPhong dp = ds.getValue(DoiPhong.class);
                    if (dp.getMaPhieu().equals(chitiet.getMaFB())) {
                        tvGhiChu.setText(dp.getLydo());
                        tvmachon.setText(dp.getMaPhongchon());
                        tvmadoi.setText(dp.getGetMaPhong());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void TimeDifference() {
        if (!chitiet.getThoiGianNhanPH().isEmpty() && !chitiet.getThoiGianTraPH().isEmpty()) {
            String time1 = chitiet.getThoiGianNhanPH();
            String time2 = chitiet.getThoiGianTraPH();

            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            Date date1 = null;
            Date date2 = null;
            try {
                date1 = format.parse(time1);
                date2 = format.parse(time2);
                thoigian = timeBetween(date1, date2) + "";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            new AlertDialog.Builder(NVTN_xacnhandoiphong.this)
                    .setTitle("Lỗi ")
                    .setMessage("Lỗi ?")
                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }

    }

    public void DateDifference() {
        if (!chitiet.getThoiGianNhanPH().isEmpty() && !chitiet.getThoiGianTraPH().isEmpty()) {
            Calendar cal1 = new GregorianCalendar();
            Calendar cal2 = new GregorianCalendar();
            chitiet.getThoiGianNhanPH();
            String[] parts;
            parts = chitiet.getThoiGianNhanPH().split("\\/");
            ngay = Integer.parseInt(parts[0]);
            thang = Integer.parseInt(parts[1]);
            nam = Integer.parseInt(parts[2]);
            cal1.set(nam, thang, ngay);
            parts = chitiet.getThoiGianTraPH().split("\\/");
            ngay = Integer.parseInt(parts[0]);
            thang = Integer.parseInt(parts[1]);
            nam = Integer.parseInt(parts[2]);
            cal2.set(nam, thang, ngay);
            thoigian = daysBetween(cal1.getTime(), cal2.getTime()) + "";
        } else {
            Toast.makeText(getApplicationContext(), "Khong co phong", Toast.LENGTH_SHORT).show();
        }

    }

    public int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public int timeBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60)) % 24;
    }

    private void layid() {
        StaticConfig.mThongBao.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    ThongBao tb = ds.getValue(ThongBao.class);
                    id = tb.getStt();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}