package com.akhileshthakur.mbank;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {



    private final List<Fragment> fragmentlist= new ArrayList<>();
    private final List<String> fragmentlistTile=new ArrayList<>();
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentlist.get(i);
    }

    @Override
    public int getCount() {
        return fragmentlistTile.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentlistTile.get(position);
    }
    public void AddFragment(Fragment fragment,String title)
    {
        fragmentlist.add(fragment);
        fragmentlistTile.add(title);

    }
}