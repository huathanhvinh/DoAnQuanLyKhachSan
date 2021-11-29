package com.example.doanquanlykhachsan.nhanvien_letan;

import android.os.Bundle;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.adapter.VP_adapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

//import com.example.doanquanlykhachsan.ui.main.SectionsPagerAdapter;


public class NVTN_Thong_Bao extends AppCompatActivity {
    ViewPager viewpager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nvtn_thongbao);

        tabLayout = findViewById(R.id.tablayout);
        viewpager = findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewpager);

        VP_adapter adapter = new VP_adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new NVTN_ThongBao_DatPhong(), "Đặt Phòng");
        adapter.addFragment(new NVTN_ThongBao_DKDichVu(), "Đăng ký dịch Vụ");
        adapter.addFragment(new NVTN_ThongBao_TraPhong(), "Trả Phòng");
        viewpager.setAdapter(adapter);
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Thongbao();
            }
        }, 0, 60000);
    }
    private void Thongbao() {

//        String key= StaticConfig.mThongbao.push().getKey();
//        ThongBao tb = new ThongBao(1,key,"h","xác nhận","mô tả",StaticConfig.currentuser);
//        StaticConfig.mThongbao.child(key).setValue(tb);
    }
}