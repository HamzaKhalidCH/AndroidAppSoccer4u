package com.example.soccer4u.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.soccer4u.LiveMatchesTab;
import com.example.soccer4u.MatchesListTab;
import com.example.soccer4u.PlayerStatTab;
import com.example.soccer4u.R;
import com.example.soccer4u.TeamStandingTab;
/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3,R.string.tab_text_4};
    private final Context mContext;
    private final int total_tabs = 4;
    private String name;

    public SectionsPagerAdapter(Context context, FragmentManager fm,String name) {
        super(fm);
        mContext = context;
        this.name = name;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MatchesListTab tab1 = new MatchesListTab(mContext,name);
                return tab1;
            case 1:
                LiveMatchesTab tab2 = new LiveMatchesTab(mContext,name);
                return tab2;
            case 2:
                TeamStandingTab tab3 = new TeamStandingTab(mContext,name);
                return tab3;
            case 3:
                PlayerStatTab tab4 = new PlayerStatTab(mContext,name);
                return tab4;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return total_tabs;
    }
}