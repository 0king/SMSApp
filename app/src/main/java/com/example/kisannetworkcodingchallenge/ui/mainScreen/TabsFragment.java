package com.example.kisannetworkcodingchallenge.ui.mainScreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kisannetworkcodingchallenge.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TabsFragment extends Fragment {

    private BottomNavigationView mBottomBar;
    private ViewPager2 mPager;
    public static final int TAB_COUNT = 2;

    public TabsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabs, container, false);
        initUi(view);
        return view;
    }

    private void initUi(View v){
        mBottomBar = v.findViewById(R.id.bottom_navigation);
        mPager = v.findViewById(R.id.view_pager);
        mPager.setAdapter(new TabsPagerAdapter(this));
        setupBottomNavBarWithViewpager();
    }

    private void setupBottomNavBarWithViewpager(){
        mBottomBar.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.navigation_contacts:
                    mPager.setCurrentItem(0, true);
                    return true;
                case R.id.navigation_messages:
                    mPager.setCurrentItem(1, true);
                    return true;
            }
            return false;
        });
        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (position==0) mBottomBar.setSelectedItemId(R.id.navigation_contacts);
                else mBottomBar.setSelectedItemId(R.id.navigation_messages);
            }
        });
    }
}
