package com.vyadav.parallaxrecylcerview;

/**
 * Created by vyadav on 24/3/15.
 */
public interface Matches {
    public void setTeams(String[] teams);
    public String[] getTeams();
    public int getPoints();
    public String getWinningTeam();
    public boolean isDrawnOrAbandoned();
}
