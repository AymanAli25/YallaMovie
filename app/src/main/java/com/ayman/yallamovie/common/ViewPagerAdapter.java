package com.ayman.yallamovie.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ayman.yallamovie.ui.FavouritesFragment;
import com.ayman.yallamovie.ui.MoviesFragment;
import com.ayman.yallamovie.ui.SearchFragment;
import com.ayman.yallamovie.ui.TMDbFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman on 2019-02-05.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragments;
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(new MoviesFragment());
        fragments.add(new SearchFragment());
        fragments.add(new FavouritesFragment());
        fragments.add(new TMDbFragment());
    }

    /**
     * Navigate between the 3 fragments based on which tab was clicked
     * @param position tab that was chosen
     * @return Fragment associated with chosen tab
     */
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    /**
     * number of tabs
     * @return
     */
    @Override
    public int getCount() {
        return fragments.size();
    }

    /**
     * Title of fragment at @param position tab
     * @param position tab chosen
     * @return Fragment name
     */
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Latest Movies";
                break;
            case 1:
                title = "Search";
                break;
            case 2:
                title = "Favourite";
                break;
            case 3:
                title = "TMDb Source";
                break;
        }
        return title;
    }
}
