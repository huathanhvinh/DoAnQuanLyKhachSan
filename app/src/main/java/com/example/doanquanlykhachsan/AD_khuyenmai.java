package com.example.doanquanlykhachsan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class AD_khuyenmai extends AppCompatActivity {
    Button btnTrove;
    private TabLayout tabLayout;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_khuyenmai);
        setControl();
        setEvent();
    }

    private void setEvent() {
        //Xử lý nút trở về
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setControl() {
        btnTrove = findViewById(R.id.btnTroVe);
        tabLayout = findViewById(R.id.tablayout);
        viewpager = findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewpager);
        VP_adapter adapter = new VP_adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new AD_khuyenmai_1(), "Sắp diễn ra");
        adapter.addFragment(new AD_khuyenmai_2(), "Đang diễn ra");
        adapter.addFragment(new AD_khuyenmai_3(), "Đã kết thúc");
        viewpager.setAdapter(adapter);
    }
}