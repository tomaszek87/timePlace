package com.tomaszek.timeplace.modules.navigation.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

    private int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "", "", "" };
    private Context context;
    private List<Fragment> fragmentList;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context, List<Fragment> fragments) {
        super(fm);
        this.context = context;
        this.fragmentList = fragments;
        this.PAGE_COUNT = fragments.size();
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    public List<Fragment> getFragmentList() {
        return fragmentList;
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }
}