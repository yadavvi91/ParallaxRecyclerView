package com.vyadav.parallaxrecylcerview;

/**
 * Created by vyadav on 24/3/15.
 */
public interface Matches {
    public void setTeams(String team1, String team2);
    void setTeamScores(int team1Score, int team2Score);
    void setWinner(String s);

    public String[] getTeams();
    public int getPoints();
    public String getWinningTeam();
    public boolean isDrawnOrAbandoned();
}
