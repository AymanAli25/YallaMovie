package com.ayman.yallamovie.common;

import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import com.ayman.yallamovie.R;
import com.ayman.yallamovie.common.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewpager_container) ViewPager mViewPager;
    @BindView(R.id.tabs) TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupViewPagerUser();
    }

    /**
     * Setup the User view with 4 tabs
     * Home Favourites and Profile
     */
    private void setupViewPagerUser() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_movie_filter_black_24dp);
        mTabLayout.getTabAt(0).setContentDescription("Latest Movies");

        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_search_black_24dp);
        mTabLayout.getTabAt(1).setContentDescription("Search");

        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_favorite_black_24dp);
        mTabLayout.getTabAt(2).setContentDescription("Favourites");

        mTabLayout.getTabAt(3).setIcon(R.drawable.ic_android_black_24dp);
        mTabLayout.getTabAt(3).setContentDescription("TMDb");


        mTabLayout.getTabAt(0).setText("");
        mTabLayout.getTabAt(1).setText("");
        mTabLayout.getTabAt(2).setText("");
        mTabLayout.getTabAt(3).setText("");

    }
}
