package com.example.doanquanlykhachsan.admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.doanquanlykhachsan.R;

import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.*;

public class AD_SuaDichVu extends AppCompatActivity {

    EditText edMadv, edGiaDV, edDVT, edMota;
    Spinner spTendv;
    Button btnLuu, btnTrove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_sua_dich_vu);
        setControl();
        setEvent();

    }

    private void setEvent() {
        //ngăn không cho sửa mã dịch vụ
        edMadv.setFocusable(false);
        //lấy thông tin dịch vụ từ listview
        layThongTinDichVu();
        //Xử lý nút trở về
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DichVu thongTinDichVu = (DichVu) getIntent().getSerializableExtra("ThongTinDichVu");
                String giaDV = thongTinDichVu.getGiaDV()+"";
                if (thongTinDichVu.getTenDV().equals(spTendv.getSelectedItem().toString()) == false ||
                giaDV.equals(edGiaDV.getText().toString()) == false ||
                thongTinDichVu.getDvt().equals(edDVT.getText().toString()) == false ||
                thongTinDichVu.getMota().equals(edMota.getText().toString())== false)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AD_SuaDichVu.this);
                    builder.setTitle("Sửa Dịch Vụ");
                    builder.setMessage("Bạn chưa lưu, Bạn có muốn thoát không ?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
                else
                {
                    finish();
                }
            }
        });
        //Xử Lý Nút Lưu
        btnLuu.setOnClickListener(new View.OnClickListener() {
            DichVu thongTinDichVu = (DichVu) getIntent().getSerializableExtra("ThongTinDichVu");
            @Override
            public void onClick(View v) {
                int gia = Integer.parseInt(edGiaDV.getText().toString());
                DichVu dv = new DichVu(thongTinDichVu.getStt(),thongTinDichVu.getMaFB(),spTendv.getSelectedItem().toString()
                        ,gia,edDVT.getText().toString(),0,edMota.getText().toString());

                StaticConfig.mDichVu.child(thongTinDichVu.getMaFB()).setValue(dv);
                new AlertDialog.Builder(AD_SuaDichVu.this)
                        .setTitle("Sửa dịch vụ")
                        .setMessage("lưu thành công!!")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
    }

    private void layThongTinDichVu() {
        DichVu thongTinDichVu = (DichVu) getIntent().getSerializableExtra("ThongTinDichVu");
        edMadv.setText("DV100" + thongTinDichVu.getStt());

//        <item>Dịch vụ giặc ủi quần áo</item>
//        <item>Dịch vụ spa</item>
//        <item>Dịch vụ trông trẻ</item>
//        <item>Dịch vụ phục vụ phòng</item>

        if (thongTinDichVu.getTenDV().toString().equals("Dịch vụ giặc ủi quần áo"))
            spTendv.setSelection(0);
        if (thongTinDichVu.getTenDV().toString().equals("Dịch vụ spa"))
            spTendv.setSelection(1);
        if (thongTinDichVu.getTenDV().toString().equals("Dịch vụ trông trẻ"))
            spTendv.setSelection(2);
        if (thongTinDichVu.getTenDV().toString().equals("Dịch vụ phục vụ phòng"))
            spTendv.setSelection(3);
        //tvTenDV.setText(thongTinDichVu.getTenDV());
        edGiaDV.setText(thongTinDichVu.getGiaDV()+"");
        edDVT.setText(thongTinDichVu.getDvt());
        edMota.setText(thongTinDichVu.getMota());
    }

    private void setControl() {
        edMadv = findViewById(R.id.edMaDichVu);
        edGiaDV = findViewById(R.id.edGiaDichVu);
        edDVT = findViewById(R.id.edDVT);
        edMota = findViewById(R.id.edMota);
        spTendv = findViewById(R.id.spTenDichVu);
        btnLuu = findViewById(R.id.btnLuu);
        btnTrove = findViewById(R.id.btnTroVe);

    }
}