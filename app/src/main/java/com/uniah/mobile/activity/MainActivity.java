package com.uniah.mobile.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewPagerAdapter;
import com.uniah.mobile.base.BaseFragmentActivity;
import com.uniah.mobile.fragment.LifeFragment;
import com.uniah.mobile.fragment.MessageFragment;
import com.uniah.mobile.fragment.MineFragment;
import com.uniah.mobile.view.UniViewPager;
import com.uniah.mobile.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseFragmentActivity {

    private UniViewPager vp;
    private MenuItem menuItem;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new HomeFragment());
        fragments.add(new MessageFragment());
        fragments.add(new LifeFragment());
        fragments.add(new MineFragment());
        BaseViewPagerAdapter adapter = new BaseViewPagerAdapter(getSupportFragmentManager(), fragments);

        vp = findViewById(R.id.main_view_pager);
        vp.setAdapter(adapter);
        vp.setOffscreenPageLimit(4);
        vp.setScrollable(false);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void setBottomViewItem(int item) {
        if (menuItem != null) {
            menuItem.setChecked(false);
        } else {
            navigation.getMenu().getItem(0).setChecked(false);
        }
        menuItem = navigation.getMenu().getItem(item);
        menuItem.setChecked(true);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    vp.setCurrentItem(0, false);
                    return true;
                case R.id.navigation_message:
                    vp.setCurrentItem(1, false);
                    return true;
                case R.id.navigation_life:
                    vp.setCurrentItem(2, false);
                    return true;
                case R.id.navigation_mine:
                    vp.setCurrentItem(3, false);
                    return true;
            }
            return false;
        }
    };
}
