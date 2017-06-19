package com.tomaszek.timeplace.modules.navigation.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.tomaszek.timeplace.R;
import com.tomaszek.timeplace.modules.navigation.adapter.SampleFragmentPagerAdapter;
import com.tomaszek.timeplace.modules.navigation.fragment.GeoFencesListFragment;
import com.tomaszek.timeplace.modules.navigation.fragment.HomeFragment;
import com.tomaszek.timeplace.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NavigationActivity extends BaseActivity {

    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.sliding_tabs)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);
        init();
    }

    private void init() {
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                NavigationActivity.this, prepareFragmentsForPager()));
        viewPager.setOffscreenPageLimit(5);
        tabLayout.setupWithViewPager(viewPager);
        addIconsToPager();
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    for (int i = 0; i < tabLayout.getTabCount(); i++) {
                        tabLayout.getTabAt(i).getIcon().setTint(getResources().getColor(R.color.white));
                    }
                    tabLayout.getTabAt(position).getIcon().setTint(getResources().getColor(R.color.colorAccent));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });
        viewPager.setCurrentItem(1);
    }

    private List<Fragment> prepareFragmentsForPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(GeoFencesListFragment.newInstance());
        fragments.add(HomeFragment.newInstance());
        fragments.add(HomeFragment.newInstance());
        return fragments;
    }

    public void addIconsToPager() {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            int iconId = -1;
            switch (i) {
                case 0:
                    iconId = R.drawable.map_marker_multiple;
                    break;
                case 1:
                    iconId = R.drawable.home;
                    break;
                case 2:
                    iconId = R.drawable.settings;
                    break;
            }
            tabLayout.getTabAt(i).setIcon(iconId);
        }
    }

}
