package com.example.doanquanlykhachsan.nhanvien_thungan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.DichVu;
import com.example.doanquanlykhachsan.model.HoaDon;
import com.example.doanquanlykhachsan.model.KhuyenMai;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.example.doanquanlykhachsan.model.Phong;
import com.example.doanquanlykhachsan.adapter.*;
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

public class NVTN_XacNhanHoaDon extends AppCompatActivity {
    Button btnXacNhan, btnTroVe;
    TextView maHD, tvtenNV, tvTenKH, tenNV, tenKH, ngayLap, tvTongtien, tvThanhTien, tvTienkm, tvsdt;
    Button call, btnKm;
    EditText edmaKm;
    PhongDaDat chitiet;
    String sdt = "";
    String sdtNV = "";
    ArrayList<Phong> data = new ArrayList<>();
    ArrayList<DichVu> dichvu = new ArrayList<>();
    Adapter_PhongThue adapter;
    Adapter_HD_DV adapterdv;
    ListView lv;
    ListView lvdv;
    float Tongtien = 0;
    float ThanhTien = 0;
    String thoigian;
    int ngay, thang, nam;
    int solan = 1;
    int stt = 0;
    String maKH = "";
    String maNV = "";
    int idthongbao = 0;
    private String tennv = "";
    private String ngayhientai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvtn_xac_nhan_hoa_don);
        setControl();
        setEvent();

    }

    private void setEvent() {
        btnKm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layngayhientai();
                StaticConfig.mKhuyenMai.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            KhuyenMai km = ds.getValue(KhuyenMai.class);
                            if (CheckDates(ngayhientai, km.getNgayKetThuc())) {
                                if (edmaKm.getText().toString().equals(km.getMaKM())) {
                                    float tien = Tongtien;
                                    tien = Tongtien * (100 - km.getMucGiamGia()) / 100;
                                    float tietkm = Tongtien - tien;
                                    //Tong tien
                                    DecimalFormat toTheFormat = new DecimalFormat("###,###,###.#");
                                    tvTongtien.setText(toTheFormat.format(tien) + " VNĐ");
                                    tvTienkm.setText(toTheFormat.format(tietkm) + " VNĐ");
                                    ThanhTien = tien;
                                    break;
                                } else {
                                    //Tong tien
                                    ThanhTien = Tongtien;
                                    DecimalFormat toTheFormat = new DecimalFormat("###,###,###.#");
                                    tvTongtien.setText(toTheFormat.format(Tongtien) + " VNĐ");
                                    tvTienkm.setText(0 + " VNĐ");
                                }
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new androidx.appcompat.app.AlertDialog.Builder(NVTN_XacNhanHoaDon.this)
                        .setTitle("Trả Phòng")
                        .setMessage("xác nhận ??")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //thông báo cho khách hàng
                                String id = StaticConfig.mThongBao.push().getKey();
                                ThongBao tb = new ThongBao(idthongbao + 1, id, "Thanh toán", "Chưa xác nhận", "", tennv, chitiet.getMaKH());
                                StaticConfig.mThongBao.child(id).setValue(tb);
                                //thêm vào hoá đơn
                                String key = StaticConfig.mHoaDon.push().getKey();
                                Log.e("solan", stt + 1 + "");
                                HoaDon hd = new HoaDon();
                                String Tongthoigian = "";
                                if (chitiet.getManHinh().equals("ngay")) {
                                    Tongthoigian = thoigian + " Ngày";
                                }
                                if (chitiet.getManHinh().equals("gio")) {
                                    Tongthoigian = thoigian + " Giờ";
                                }
                                hd = new HoaDon(stt + 1, key, maKH, maNV, ngayLap.getText().toString(), chitiet.getThoiGianNhanPH(), chitiet.getThoiGianTraPH(), Tongthoigian, "", ThanhTien);
                                StaticConfig.mHoaDon.child(key).setValue(hd);
//                                Trả Phòng
                                String chuoiPhongDadat = "";
                                StaticConfig.mRoomRented.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot ds : snapshot.getChildren()) {
                                            PhongDaDat da = ds.getValue(PhongDaDat.class);
                                            if (da.getMaFB().equals(chitiet.getMaFB())) {
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
                                                                    StaticConfig.mRoom.child(p.getMaFB()).child("trangThai").setValue("Chưa Dọn");
                                                                    //remove quan ly phong
                                                                    StaticConfig.mQLPhong.child(p.getMaFB()).removeValue();
                                                                }
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {

                                                        }
                                                    });
                                                }
                                                StaticConfig.mRoomRented.child(chitiet.getMaFB()).removeValue();

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
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setControl() {
        btnXacNhan = findViewById(R.id.btnXacNhan);
        btnTroVe = findViewById(R.id.btnTroVe);
        tvTenKH = findViewById(R.id.tvKH);
        tvtenNV = findViewById(R.id.tvTenNV);
        maHD = findViewById(R.id.tvMaHD);
        ngayLap = findViewById(R.id.tvNgayLapHD);
        tenNV = findViewById(R.id.tvNhanVien);
        tenKH = findViewById(R.id.tvTenKH);
        lv = findViewById(R.id.lvthue);
        lvdv = findViewById(R.id.lvDv);
        tvTongtien = findViewById(R.id.tvTongTien);
        btnKm = findViewById(R.id.btnKm);
        edmaKm = findViewById(R.id.edMaKm);
        tvThanhTien = findViewById(R.id.tvThanhTien);
        tvTienkm = findViewById(R.id.tvTienkm);

        adapter = new Adapter_PhongThue(getApplicationContext(), R.layout.items_phongthue, data);
        lv.setAdapter(adapter);
        adapterdv = new Adapter_HD_DV(getApplicationContext(), R.layout.item_dv, dichvu);
        lvdv.setAdapter(adapterdv);
        khoitao();
        getstt();
    }

    private void getstt() {
        StaticConfig.mHoaDon.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    HoaDon h = ds.getValue(HoaDon.class);
                    stt = h.getStt();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void khoitao() {
        tenNhanvien();
        layid();
        chitiet = (PhongDaDat) getIntent().getSerializableExtra("chitiet");
        tvTenKH.setText(chitiet.getTen());
        tenKH.setText(chitiet.getTen());
        if (chitiet.getStt() < 9) {
            maHD.setText("HD0" + chitiet.getStt());
        } else {
            maHD.setText("HD" + chitiet.getStt());
        }
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
        StaticConfig.mNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    NhanVien nv = ds.getValue(NhanVien.class);
                    if (nv.getSoDienThoai().equals(StaticConfig.currentphone)) {
                        tenNV.setText(nv.getTenNV());
                        tvtenNV.setText(nv.getTenNV());
                        maNV = nv.getMaFB();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //lay ngay hien tai
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        calendar.set(nam, thang, ngay);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ngayLap.setText(simpleDateFormat.format(calendar.getTime()));
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
                                    tvThanhTien.setText(toTheFormat.format(Tongtien) + " VNĐ");
                                    ThanhTien = Tongtien;
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
            new AlertDialog.Builder(NVTN_XacNhanHoaDon.this)
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
                    idthongbao = tb.getStt();
                }
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