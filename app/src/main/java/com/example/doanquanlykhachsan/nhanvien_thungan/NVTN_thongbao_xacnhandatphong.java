package com.example.doanquanlykhachsan.nhanvien_thungan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.adapter.Adapter_HD_DV;
import com.example.doanquanlykhachsan.adapter.Adapter_PhongThue;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.DichVu;
import com.example.doanquanlykhachsan.model.HoaDon;
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

public class NVTN_thongbao_xacnhandatphong extends AppCompatActivity {
    Button btnXacNhan, btnTroVe, btnHuy;
    TextView tvtenNV, tvTenKH, tenNV, tenKH, tvTongtien, tvsdt;
    Button call;
    PhongDaDat chitiet;
    ArrayList<Phong> data = new ArrayList<>();
    ArrayList<DichVu> dichvu = new ArrayList<>();
    Adapter_PhongThue adapter;
    Adapter_HD_DV adapterdv;
    ListView lv;
    ListView lvdv;
    private String thoigian;
    int ngay, thang, nam;
    private float Tongtien;
    int solan = 1;
    private String tennv;
    private TextView tvGhiChu;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_thongbao_xacnhandatphong);
        setControl();
        setEvnent();
    }

    private void setEvnent() {
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Integer.parseInt(tvsdt.getText().toString())));
                startActivity(intentDial);
            }
        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new androidx.appcompat.app.AlertDialog.Builder(NVTN_thongbao_xacnhandatphong.this)
                        .setTitle("?????t Ph??ng")
                        .setMessage("x??c nh???n ??")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                StaticConfig.mRoomRented.child(chitiet.getMaFB()).child("xacnhan").setValue("???? X??c Nh???n");
                                String chuoiMaPhong = chitiet.getMaPhong();
                                String[] parts;
                                parts = chuoiMaPhong.split(" ");
                                for (String w : parts) {
                                    StaticConfig.mRoom.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot ds : snapshot.getChildren()) {
                                                Phong p = ds.getValue(Phong.class);
                                                if (p.getMaPhong().equals(w)) {
                                                    StaticConfig.mRoom.child(p.getMaFB()).child("trangThai").setValue("???? ?????t Ph??ng");
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
                                String key = StaticConfig.mThongBao.push().getKey();
                                ThongBao tb = new ThongBao(id + 1, key, "?????t Ph??ng", "Ch??a x??c nh???n", "", tennv, chitiet.getMaKH());
                                StaticConfig.mThongBao.child(key).setValue(tb);
                                finish();
                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new androidx.appcompat.app.AlertDialog.Builder(NVTN_thongbao_xacnhandatphong.this)
                        .setTitle("?????t Ph??ng")
                        .setMessage("x??c nh???n hu??? Ph??ng ??")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
//                                Tr??? Ph??ng
                                String chuoiPhongDadat = "";
                                StaticConfig.mRoomRented.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot ds : snapshot.getChildren()) {
                                            PhongDaDat da = ds.getValue(PhongDaDat.class);
                                            if (da.getMaKH().equals(chitiet.getMaKH()) && da.getMaFB().equals(chitiet.getMaFB())) {
                                                StaticConfig.mRoomRented.child(da.getMaFB()).removeValue();
                                                String chuoimaphong = ds.child("maPhong").getValue(String.class);
                                                String[] parts;
                                                parts = chuoimaphong.split(" ");
                                                for (String maPh : parts) {
                                                    StaticConfig.mRoom.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            for (DataSnapshot ds2 : snapshot.getChildren()) {
                                                                Phong p = ds2.getValue(Phong.class);
                                                                if (maPh.equals(p.getMaPhong())) {
                                                                    StaticConfig.mRoom.child(p.getMaFB()).child("trangThai").setValue("Tr???ng");
                                                                    //remove quan ly phong
                                                                    StaticConfig.mQLPhong.child(p.getMaFB()).removeValue();
                                                                }
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {

                                                        }
                                                    });
                                                    String key = StaticConfig.mThongBao.push().getKey();
                                                    ThongBao tb = new ThongBao(id + 1, key, "???? b??? hu??? y??u c???u", "Ch??a x??c nh???n", "", tennv, chitiet.getMaKH());
                                                    StaticConfig.mThongBao.child(key).setValue(tb);
                                                    finish();

                                                }

                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                                finish();
                            }

                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
    }

    private void khoitao() {
        layid();
        tenNhanvien();
        chitiet = (PhongDaDat) getIntent().getSerializableExtra("chitiet");
        StaticConfig.mNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    NhanVien nv = ds.getValue(NhanVien.class);
                    if (nv.getSoDienThoai().equals(StaticConfig.currentphone)) {
                        tenNV.setText(nv.getTenNV());
                        tvtenNV.setText(nv.getTenNV());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        StaticConfig.mKhachHang.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    KhachHang kh = ds.getValue(KhachHang.class);
                    if (kh.getSdtKH().equals(chitiet.getSdt())) {
                        tenKH.setText(kh.getTenKH());
                        tvTenKH.setText(kh.getTenKH());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //l???y th???i gian
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
        //L???y Ph??ng thu??
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
                                    //ghi chu
                                    tvGhiChu.setText(da.getGhiChuKH());
                                    //Tong tien
                                    DecimalFormat toTheFormat = new DecimalFormat("###,###,###.#");
                                    tvTongtien.setText(toTheFormat.format(Tongtien) + " VN??");
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
    }

    private void setControl() {
        btnXacNhan = findViewById(R.id.btnXacNhan);
        btnTroVe = findViewById(R.id.btnTroVe);
        tvTenKH = findViewById(R.id.tvKH);
        tvtenNV = findViewById(R.id.tvTenNV);
        tenNV = findViewById(R.id.tvNhanVien);
        tenKH = findViewById(R.id.tvTenKH);
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
            new AlertDialog.Builder(NVTN_thongbao_xacnhandatphong.this)
                    .setTitle("L???i ")
                    .setMessage("L???i ?")
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