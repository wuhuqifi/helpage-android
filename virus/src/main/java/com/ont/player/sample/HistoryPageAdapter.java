package com.ont.player.sample;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by betali on 2018/9/13.
 */
public class HistoryPageAdapter extends FragmentPagerAdapter {

    String[] mTitles;
    OntListView[] mViews;

    public HistoryPageAdapter(FragmentManager fm, String[] titles, OntListView[] mViews) {

        super(fm);
        this.mTitles = titles;
        this.mViews = mViews;
    }

    public void setViews(OntListView[] views) {

        this.mViews = views;
    }

    public void setTitles(String[] titles) {

        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {

        return mViews[position];
    }

    @Override
    public int getCount() {

        return mViews.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return mTitles[position];
    }
}
