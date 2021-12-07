package com.example.doanquanlykhachsan.nhanvien_thungan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.adapter.VP_adapter;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

//import com.example.doanquanlykhachsan.ui.main.SectionsPagerAdapter;


public class NVTN_Thong_Bao extends AppCompatActivity {
    ViewPager viewpager;
    TabLayout tabLayout;
    Button btnTrove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nvtn_thongbao);

        tabLayout = findViewById(R.id.tablayout);
        viewpager = findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewpager);
        btnTrove=findViewById(R.id.btnTroVe);

        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(),NVTN_MenuNhanVienThuNgan.class));
            }
        });

        VP_adapter adapter = new VP_adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new NVTN_ThongBao_DatPhong(), "Đặt Phòng");
        adapter.addFragment(new NVTN_ThongBao_TraPhong(), "Trả Phòng");
        viewpager.setAdapter(adapter);
    }
}