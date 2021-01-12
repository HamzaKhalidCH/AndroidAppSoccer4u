package com.example.soccer4u;

public class Team implements Comparable{
    public String name;
    public int points;
    public int played;
    public int win;
    public int lost;
    public int tied;
    public int gd;

    public Team(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Match)
        {
            Team t = (Team) o;
            return t.points - ((Team) o).points;
        }
        else
        {
            return 0;
        }
    }
}
