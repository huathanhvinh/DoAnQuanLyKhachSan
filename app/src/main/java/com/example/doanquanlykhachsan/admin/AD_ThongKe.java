package com.example.doanquanlykhachsan.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.ListView;
import android.widget.TextView;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.HoaDon;
import com.example.doanquanlykhachsan.adapter.*;
import com.example.doanquanlykhachsan.model.NhanVien;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AD_ThongKe extends AppCompatActivity {
    PieChart pieChart;
    TextView tvNgayBatDau, tvNgayKetThuc, tvToanhDoanhThu, tvNamThongKe;
    ImageView imNgayBatDau, imNgayKetThuc, imTimKiem;
    Button btnTrove;
    ListView lvDshd;
    Adapter_thongke adapter_thongke;
    ArrayList<HoaDon> arrHoaDon = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_thong_ke);
        setControl();
        setEvent();
    }

    private void setControl() {
        pieChart = findViewById(R.id.activity_main_piechart);
        tvNgayBatDau = findViewById(R.id.tvNgayBatDau1);
        tvNamThongKe = findViewById(R.id.namThongKe);
        tvNgayKetThuc = findViewById(R.id.tvNgayKetThuc1);
        tvToanhDoanhThu = findViewById(R.id.tvDoanhThu1);
        imNgayBatDau = findViewById(R.id.imNgayBatDau1);
        imNgayKetThuc = findViewById(R.id.imNgayKetThuc1);
        imTimKiem = findViewById(R.id.imTimKiem1);
        btnTrove = findViewById(R.id.btnTroVe1);
        lvDshd = findViewById(R.id.lvDanhSachHoaDon1);

        adapter_thongke = new Adapter_thongke(getApplicationContext(), R.layout.item_hoadon, arrHoaDon);
        lvDshd.setAdapter(adapter_thongke);
    }

    private void setEvent() {
        //Thống kê theo Quý của Năm
        thongKeTheoQuyCuaNam();
//        setupPieChart();
//        loadPieChartData();
        //nút trở về
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //lấy ra tháng hiện tại
        layRaThangHienTaiVaThangTiepTheo();
        //thêm dữ liệu tháng đó khi mở thống kê
        themDuLieu();
        //thêm dữ liệu sau khi chọn ngày bất đầu
        imNgayBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imButtonLichNgayBatDau();
            }
        });
        //thêm dữ liệu sau khi chọn ngày kết thúc
        imNgayKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imButtonLichNgayKetThuc();
            }
        });
        //tìm kiếm chi tiết
        imTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrHoaDon.clear();
                adapter_thongke.clear();
                int checkNgay = 0;
                Date ngayBatDau;
                Date ngayKetThuc;
                //kiểm tra thời gian bất đầu với thời gian kết thúc
                try {
                    ngayBatDau = new SimpleDateFormat("dd/MM/yyyy").parse(tvNgayBatDau.getText().toString());
                    ngayKetThuc = new SimpleDateFormat("dd/MM/yyyy").parse(tvNgayKetThuc.getText().toString());
                    if (ngayBatDau.compareTo(ngayKetThuc) > 0)
                        checkNgay = 1;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (checkNgay != 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AD_ThongKe.this);
                    builder.setTitle("Thông Báo");
                    builder.setMessage("Thời gian tìm kiếm không hợp lệ !!!");
                    builder.setPositiveButton("oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();
                } else {
                    StaticConfig.mHoaDon.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            float tongTien = 0;
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                HoaDon hd = ds.getValue(HoaDon.class);
                                try {
                                    Date ngayLap = new SimpleDateFormat("dd/MM/yy").parse(hd.getNgaylap());
                                    Date ngayBatDau1 = new SimpleDateFormat("dd/MM/yy").parse(tvNgayBatDau.getText().toString());
                                    Date ngayKetThuc1 = new SimpleDateFormat("dd/MM/yy").parse(tvNgayKetThuc.getText().toString());
                                    if (ngayLap.compareTo(ngayBatDau1) >= 0 && ngayLap.compareTo(ngayKetThuc1) <= 0) {
                                        arrHoaDon.add(hd);
                                        tongTien += hd.getTongTien();
                                        adapter_thongke.notifyDataSetChanged();

                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                            DecimalFormat toTheFormat = new DecimalFormat("###,###,###.#");
                            tvToanhDoanhThu.setText(toTheFormat.format(tongTien) + " VNĐ");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }

    private void thongKeTheoQuyCuaNam() {
        StaticConfig.mHoaDon.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                float tongTienQuy1 = 0;
                float tongTienQuy2 = 0;
                float tongTienQuy3 = 0;
                float tongTienQuy4 = 0;
                float tongDoanhThu = 0;
                String namHienTai = new SimpleDateFormat("yy", Locale.getDefault()).format(new Date());
                String namTiepTheo = (Integer.parseInt(namHienTai) + 1) + "";
                for (DataSnapshot ds : snapshot.getChildren()) {
                    HoaDon hd = ds.getValue(HoaDon.class);
                    try {
                        Date ngayLap = new SimpleDateFormat("dd/MM/yy").parse(hd.getNgaylap());
                        Date ngayBatDauQ1 = new SimpleDateFormat("dd/MM/yy").parse("01/01/" + namHienTai);
                        Date ngayKetThucQ1 = new SimpleDateFormat("dd/MM/yy").parse("01/04/" + namHienTai);
                        Date ngayBatDauQ2 = new SimpleDateFormat("dd/MM/yy").parse("01/04/" + namHienTai);
                        Date ngayKetThucQ2 = new SimpleDateFormat("dd/MM/yy").parse("01/07/" + namHienTai);
                        Date ngayBatDauQ3 = new SimpleDateFormat("dd/MM/yy").parse("01/07/" + namHienTai);
                        Date ngayKetThucQ3 = new SimpleDateFormat("dd/MM/yy").parse("01/10/" + namHienTai);
                        Date ngayBatDauQ4 = new SimpleDateFormat("dd/MM/yy").parse("01/10/" + namHienTai);
                        Date ngayKetThucQ4 = new SimpleDateFormat("dd/MM/yy").parse("01/01/" + namTiepTheo);

                        if (ngayLap.compareTo(ngayBatDauQ1) >= 0 && ngayLap.compareTo(ngayKetThucQ1) < 0) {
                            tongTienQuy1 += hd.getTongTien();
                        }
                        if (ngayLap.compareTo(ngayBatDauQ2) >= 0 && ngayLap.compareTo(ngayKetThucQ2) < 0) {
                            tongTienQuy2 += hd.getTongTien();
                        }
                        if (ngayLap.compareTo(ngayBatDauQ3) >= 0 && ngayLap.compareTo(ngayKetThucQ3) < 0) {
                            tongTienQuy3 += hd.getTongTien();
                        }
                        if (ngayLap.compareTo(ngayBatDauQ4) >= 0 && ngayLap.compareTo(ngayKetThucQ4) < 0) {
                            tongTienQuy4 += hd.getTongTien();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                //////////////////////////////////////////////
                tongDoanhThu = tongTienQuy1 + tongTienQuy2 + tongTienQuy3 + tongTienQuy4;
                DecimalFormat toTheFormat = new DecimalFormat("###,###,###.#");
                String tongDoanhThuFM = toTheFormat.format(tongDoanhThu) + " VNĐ";
                pieChart.setDrawHoleEnabled(true);
                pieChart.setUsePercentValues(true);
                pieChart.setEntryLabelTextSize(12);
                pieChart.setEntryLabelColor(Color.BLACK);
                pieChart.setCenterText("Tổng Doanh Thu\n" + tongDoanhThuFM);
                pieChart.setCenterTextSize(18);
                pieChart.getDescription().setEnabled(false);

                Legend l = pieChart.getLegend();
                l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
                l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
                l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
                l.setDrawInside(false);
                l.setEnabled(true);
                //////////////////////////////
                ArrayList<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(tongTienQuy1, "Quý 1"));
                entries.add(new PieEntry(tongTienQuy2, "Quý 2"));
                entries.add(new PieEntry(tongTienQuy3, "Quý 3"));
                entries.add(new PieEntry(tongTienQuy4, "Quý 4"));

                ArrayList<Integer> colors = new ArrayList<>();
                for (int color : ColorTemplate.MATERIAL_COLORS) {
                    colors.add(color);
                }

                for (int color : ColorTemplate.VORDIPLOM_COLORS) {
                    colors.add(color);
                }

                PieDataSet dataSet = new PieDataSet(entries, "Thống kê năm 20" + namHienTai);
                dataSet.setColors(colors);

                PieData data = new PieData(dataSet);
                data.setDrawValues(true);
                data.setValueFormatter(new PercentFormatter(pieChart));
                data.setValueTextSize(12f);
                data.setValueTextColor(Color.BLACK);

                pieChart.setData(data);
                pieChart.invalidate();

                pieChart.animateY(1400, Easing.EaseInOutQuad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void themDuLieu() {
        StaticConfig.mHoaDon.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                float tongTien = 0;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    HoaDon hd = ds.getValue(HoaDon.class);
                    try {
                        Date ngayLap = new SimpleDateFormat("dd/MM/yy").parse(hd.getNgaylap());
                        Date ngayBatDau1 = new SimpleDateFormat("dd/MM/yy").parse(tvNgayBatDau.getText().toString());
                        Date ngayKetThuc1 = new SimpleDateFormat("dd/MM/yy").parse(tvNgayKetThuc.getText().toString());
                        if (ngayLap.compareTo(ngayBatDau1) >= 0 && ngayLap.compareTo(ngayKetThuc1) <= 0) {
                            arrHoaDon.add(hd);
                            tongTien += hd.getTongTien();
                            adapter_thongke.notifyDataSetChanged();

                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                DecimalFormat toTheFormat = new DecimalFormat("###,###,###.#");
                tvToanhDoanhThu.setText(toTheFormat.format(tongTien) + " VNĐ");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void imButtonLichNgayKetThuc() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
                tvNgayKetThuc.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    private void imButtonLichNgayBatDau() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
                tvNgayBatDau.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    private void layRaThangHienTaiVaThangTiepTheo() {
        String namHienTai = new SimpleDateFormat("yy", Locale.getDefault()).format(new Date());
        String namTiepTheo = namHienTai;
        String thangHienTai = new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
        String thangTiepTheo = (Integer.parseInt(thangHienTai) + 1) + "";
        if (thangTiepTheo.equals("13")) {
            thangTiepTheo = "1";
            namTiepTheo = (Integer.parseInt(namHienTai) + 1) + "";
        }
        if (Integer.parseInt(thangTiepTheo) < 10) {
            thangTiepTheo = "0" + thangTiepTheo;
        }
        //thêm tháng mặc địch vào ô tìm kiếm
        tvNamThongKe.setText("BIỂU ĐỒ THỐNG KÊ THEO QUÝ NĂM 20" + namHienTai);
        tvNgayBatDau.setText("01" + "/" + thangHienTai + "/" + namHienTai);
        tvNgayKetThuc.setText("01" + "/" + thangTiepTheo + "/" + namTiepTheo);
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Tổng Doanh Thu\n" + "1");
        pieChart.setCenterTextSize(18);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private void loadPieChartData() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(100000, "Quý 1"));
        entries.add(new PieEntry(100000, "Quý 2"));
        entries.add(new PieEntry(100000, "Quý 3"));
        entries.add(new PieEntry(100000, "Quý 4"));

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color : ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Thống kê năm 2021");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }

}