package com.example.soccer4u;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Live_Matches_Adapter extends ArrayAdapter<Match> {
    ArrayList<Match> matchList = new ArrayList<Match>();
    Context context;

    public Live_Matches_Adapter(@NonNull Context context, ArrayList<Match> matchList) {
        super(context, android.R.layout.simple_selectable_list_item,matchList);
        this.matchList = matchList;
        this.context = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        TextView team1;
        TextView team2;
        TextView score1;
        TextView score2;
        ImageView img1;
        ImageView img2;


        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.tab_livematches_item,parent,false);
        }

        team1 = convertView.findViewById(R.id.matchTeam1Name);
        team2 = convertView.findViewById(R.id.matchTeam2Name);
        score1 = convertView.findViewById(R.id.team1Score);
        score2 = convertView.findViewById(R.id.team2Score);

        Match currentMatch = matchList.get(position);

        team1.setText(currentMatch.team1Name);
        team2.setText(currentMatch.team2Name);
        score1.setText(String.valueOf(currentMatch.team1Score));
        score2.setText(String.valueOf(currentMatch.team2Score));

        img1 = convertView.findViewById(R.id.team1Image);
        img2 = convertView.findViewById(R.id.team2Image);


        String name1 = currentMatch.team1Name.toLowerCase();
        name1 = name1.replaceAll("\\s+","");
        String name2 = currentMatch.team2Name.toLowerCase();
        name2 = name2.replaceAll("\\s+","");

        String uri1 = "drawable/"+name1+"small";
        String uri2 = "drawable/"+name2+"small";


        int img1Id = this.context.getResources().getIdentifier(uri1,null,context.getPackageName());
        int img2Id = this.context.getResources().getIdentifier(uri2,null,context.getPackageName());

        Drawable image1 = context.getResources().getDrawable(img1Id);
        Drawable image2 = context.getResources().getDrawable(img2Id);

        img1.setImageDrawable(image1);
        img2.setImageDrawable(image2);

        return convertView;
    }

}
