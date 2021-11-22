package com.example.doanquanlykhachsan.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.doanquanlykhachsan.nhanvien_letan.NVTN_ThongBao_DKDichVu;
import com.example.doanquanlykhachsan.nhanvien_letan.NVTN_ThongBao_DatPhong;
import com.example.doanquanlykhachsan.nhanvien_letan.NVTN_ThongBao_TraPhong;
import com.example.doanquanlykhachsan.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fra = null;
        switch (position)
        {
            case 0:
                fra =new NVTN_ThongBao_DatPhong();
                break;
            case 1:
                fra =new NVTN_ThongBao_DKDichVu();
                break;
            case 2:
                fra =new NVTN_ThongBao_TraPhong();
                break;
        }
        return fra;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}