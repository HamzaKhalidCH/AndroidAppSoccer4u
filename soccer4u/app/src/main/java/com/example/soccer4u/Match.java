package com.example.soccer4u;

import androidx.annotation.Nullable;

import com.google.firebase.Timestamp;

public class Match {

    public String Id;
    public String team1Name;
    public String team2Name;
    public int team1Score;
    public int team2Score;
    public String team1Poss;
    public String team2Poss;
    public int team1Passing;
    public int team2Passing;
    public int team1Passes;
    public int team2Passes;
    public String matchDate;
    public String matchTime;
    public String venue;
    public int matchStatus;
    public Timestamp timeStamp;

    public Match(String id,String team1Name, String team2Name) {
        this.Id = id;
        this.team1Name = team1Name;
        this.team2Name = team2Name;
        this.team1Score = 0;
        this.team2Score = 0;
        this.team1Passing = 0;
        this.team2Passing = 0;
        this.team1Passes = 0;
        this.team2Passes = 0;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof Match)
        {
            Match p = (Match) obj;
            return this.timeStamp.equals(p.timeStamp);
        }
        else
        {
            return false;
        }
    }
}
