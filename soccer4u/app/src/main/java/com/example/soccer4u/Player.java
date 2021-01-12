package com.example.soccer4u;

public class Player implements Comparable{

    public String name;
    public String position;
    public int goals;
    public int assists;

    public Player(String name, String position, int goals, int assists) {
        this.name = name;
        this.position = position;
        this.goals = goals;
        this.assists = assists;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Player)
        {
            Player t = (Player) o;
            return t.goals - ((Player) o).goals;
        }
        else
        {
            return 0;
        }
    }
}
