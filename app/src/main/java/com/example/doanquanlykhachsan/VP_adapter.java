package com.example.doanquanlykhachsan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class VP_adapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> data = new ArrayList<>();
    private ArrayList<String> Arraytieude = new ArrayList<>();

    public VP_adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }
    public void addFragment(Fragment fragment,String tieude){
        data.add(fragment);
        Arraytieude.add(tieude);
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return Arraytieude.get(position);
    }
}
