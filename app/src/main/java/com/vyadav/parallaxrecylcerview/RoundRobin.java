package com.vyadav.parallaxrecylcerview;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by vyadav on 23/3/15.
 */
public class RoundRobin implements Matches {

    private String[] teams;
    private HashMap<String, Scores> scoresOfTeams;
    private Date date;
    private String winningTeam;
    private boolean isDrawn;

    public RoundRobin() {

    }

    @Override
    public void setTeams(String[] teams) {
        if (teams.length == 2) {
            this.teams = teams;
        } else {
            throw new IllegalArgumentException("Every Match must have 2 teams");
        }
    }

    @Override
    public String[] getTeams() {
        return teams;
    }

    @Override
    public int getPoints() {
        return 0;
    }

    @Override
    public String getWinningTeam() {
        return winningTeam;
    }

    @Override
    public boolean isDrawnOrAbandoned() {
        return isDrawn;
    }

    private class Scores {
        int runs;
        int wickets;
        float oversPlayed;
    }
}
