package com.example.arbiterchil.smartap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class viewpage extends FragmentPagerAdapter {

    private Bundle args;
    private final List<Fragment> fl = new ArrayList<>();
    private final List<String> flt = new ArrayList<>();
    public viewpage(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {

        return fl.get(i);
    }

    @Override
    public int getCount() {
        return flt.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return flt.get(position);
    }

    public void AddFrag(Fragment fragment, String Tie){
        fl.add(fragment);
        flt.add(Tie);

    }
}
