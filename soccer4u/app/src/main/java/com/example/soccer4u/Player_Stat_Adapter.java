package com.example.soccer4u;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Player_Stat_Adapter extends ArrayAdapter<Player> {
    ArrayList<Player> playerList = new ArrayList<Player>();
    Context context;

    public Player_Stat_Adapter(@NonNull Context context, ArrayList<Player> teamList) {
        super(context, android.R.layout.simple_selectable_list_item,teamList);
        this.playerList = teamList;
        this.context = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.tab_stats_item,parent,false);
        }

        TextView playerPos = convertView.findViewById(R.id.position);
        TextView playerName= convertView.findViewById(R.id.playerName);
        TextView playerGoals= convertView.findViewById(R.id.TotalGoals);
        TextView playerAssists = convertView.findViewById(R.id.TotalAssists);

        Player player = this.playerList.get(position);

        playerPos.setText(String.valueOf(position+1));
        playerName.setText(player.name);
        playerGoals.setText(String.valueOf(player.goals));

        playerAssists.setText(String.valueOf(player.assists));

        return convertView;

    }
}
