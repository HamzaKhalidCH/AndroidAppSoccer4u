package com.example.soccer4u;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Team_Standings_Adapter extends ArrayAdapter<Team> {
    ArrayList<Team> teamList = new ArrayList<Team>();
    Context context;

    public Team_Standings_Adapter(@NonNull Context context, ArrayList<Team> teamList) {
        super(context, android.R.layout.simple_selectable_list_item,teamList);
        this.teamList = teamList;
        this.context = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.tab_standings_item,parent,false);
        }

        TextView pos = convertView.findViewById(R.id.position);
        TextView teamName = convertView.findViewById(R.id.teamName);
        TextView teamPoints= convertView.findViewById(R.id.points);
        TextView teamPlays= convertView.findViewById(R.id.matchesPlayed);
        TextView teamWins= convertView.findViewById(R.id.matchesWon);
        TextView teamTies= convertView.findViewById(R.id.matchesTied);
        TextView teamLosses= convertView.findViewById(R.id.matchesLost);
        TextView gd = convertView.findViewById(R.id.goalDiff);

        Team team = this.teamList.get(position);

        pos.setText(String.valueOf(position+1));
        teamName.setText(team.name);
        teamPoints.setText(String.valueOf(team.points));
        teamPlays.setText(String.valueOf(team.played));
        teamWins.setText(String.valueOf(team.win));
        teamLosses.setText(String.valueOf(team.lost));
        teamTies.setText(String.valueOf(team.tied));
        gd.setText(String.valueOf(team.gd));

        return convertView;

    }
}
