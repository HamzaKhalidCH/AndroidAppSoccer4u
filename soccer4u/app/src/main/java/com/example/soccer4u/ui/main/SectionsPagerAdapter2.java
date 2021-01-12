package com.example.soccer4u.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.soccer4u.LiveMatchesTab;
import com.example.soccer4u.MatchLineupTab;
import com.example.soccer4u.MatchStatsTab;
import com.example.soccer4u.MatchesListTab;
import com.example.soccer4u.PlayerStatTab;
import com.example.soccer4u.R;
import com.example.soccer4u.TeamStandingTab;

public class SectionsPagerAdapter2 extends FragmentPagerAdapter {
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_match_tab1, R.string.tab_match_tab2};
    private final Context mContext;
    private final int total_tabs = 2;
    private String leagueName;
    private String matchId;
    public SectionsPagerAdapter2(Context context, FragmentManager fm,String leagueName,String matchId) {
        super(fm);
        mContext = context;
        this.leagueName = leagueName;
        this.matchId = matchId;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MatchStatsTab tab1 = new MatchStatsTab(this.mContext,this.leagueName,this.matchId);
                return tab1;
            case 1:
                MatchLineupTab tab2 = new MatchLineupTab(this.mContext,this.leagueName,this.matchId);
                return tab2;
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
