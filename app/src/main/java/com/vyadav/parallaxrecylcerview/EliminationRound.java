package com.vyadav.parallaxrecylcerview;

/**
 * Created by vyadav on 24/3/15.
 */
public class EliminationRound implements Matches {
    @Override
    public void setTeams(String[] teams) {

    }

    @Override
    public String[] getTeams() {
        return new String[0];
    }

    @Override
    public int getPoints() {
        return 0;
    }

    @Override
    public String getWinningTeam() {
        return null;
    }

    @Override
    public boolean isDrawnOrAbandoned() {
        return false;
    }
}
