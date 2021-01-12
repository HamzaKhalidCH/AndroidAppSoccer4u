package com.example.soccer4u;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.soccer4u.ui.main.SectionsPagerAdapter;
import com.example.soccer4u.ui.main.SectionsPagerAdapter2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

public class MatchTabbedActivity extends AppCompatActivity {

    private String leagueName;
    private String matchId;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.context = getApplicationContext();


        this.leagueName = getIntent().getStringExtra("leagueName");
        this.matchId = getIntent().getStringExtra("id");


        if(leagueName.equals("La Liga"))
        {setTheme(R.style.laiga);}
        else if(leagueName.equals("Premier League"))
        {setTheme(R.style.premierleague);}
        else if(leagueName.equals("SeriaA"))
        {setTheme(R.style.premierleague);}
        else if(leagueName.equals("Bundesliga"))
        {setTheme(R.style.bundesliga);}
        else {setTheme(R.style.laiga);}

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_tabbed);

        TextView team1Name = findViewById(R.id.Team1name);
        TextView team2Name = findViewById(R.id.Team2name);
        TextView team1Score = findViewById(R.id.team1Score);
        TextView team2Score = findViewById(R.id.team2Score);

        ImageView img1 = (ImageView) findViewById(R.id.team1Image);
        ImageView img2 = (ImageView) findViewById(R.id.team2Image);

        String name1 = getIntent().getStringExtra("team1Name").toLowerCase();
        name1 = name1.replaceAll("\\s+","");
        String name2 = getIntent().getStringExtra("team2Name").toLowerCase();
        name2 = name2.replaceAll("\\s+","");

        String uri1 = "drawable/"+name1+"large";
        String uri2 = "drawable/"+name2+"large";

        int img1Id = this.getApplicationContext().getResources().getIdentifier(uri1,null,context.getPackageName());
        int img2Id = this.getApplicationContext().getResources().getIdentifier(uri2,null,context.getPackageName());

        Drawable image1 = context.getResources().getDrawable(img1Id);
        Drawable image2 = context.getResources().getDrawable(img2Id);

        img1.setImageDrawable(image1);
        img2.setImageDrawable(image2);

        team1Name.setText(getIntent().getStringExtra("team1Name"));
        team2Name.setText(getIntent().getStringExtra("team2Name"));

        team1Score.setText(String.valueOf(getIntent().getIntExtra("team1Score",0)));
        team2Score.setText(String.valueOf(getIntent().getIntExtra("team2Score",0)));



        SectionsPagerAdapter2 sectionsPagerAdapter = new SectionsPagerAdapter2(this,
                                                     getSupportFragmentManager(),this.leagueName,this.matchId);
        ViewPager viewPager = findViewById(R.id.view_pager2);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs2);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab2);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
