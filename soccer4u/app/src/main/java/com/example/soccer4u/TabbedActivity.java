package com.example.soccer4u;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.soccer4u.ui.main.SectionsPagerAdapter;

public class TabbedActivity extends AppCompatActivity {

    private String leagueName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.leagueName = getIntent().getStringExtra("Name");

        if(leagueName.equals("La Liga"))
        {setTheme(R.style.laiga);}
        else if(leagueName.equals("Premier League"))
        {setTheme(R.style.premierleague);}
        else if(leagueName.equals("SeriaA"))
        {setTheme(R.style.premierleague);}
        else if(leagueName.equals("Bundesliga"))
        {setTheme(R.style.bundesliga);}

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),leagueName);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        TextView txt = findViewById(R.id.title);
        txt.setText(leagueName);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}