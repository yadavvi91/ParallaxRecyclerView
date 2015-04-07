package com.vyadav.parallaxrecylcerview;

/**
 * Created by vyadav on 24/3/15.
 */
public class EliminationRound implements Matches {
    String team1, team2;
    String winnerTeam;
    int team1Score, team2Score;
    boolean isDrawn;

    @Override
    public void setTeams(String team1, String team2) {

    }

    @Override
    public void setTeamScores(int team1Score, int team2Score) {
        this.team1Score = team1Score;

    }

    @Override
    public void setWinner(String winner) {
        if (winner == null || winner.equalsIgnoreCase("")) {
            isDrawn = true;
        }
        this.winnerTeam = winner;
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
        return winnerTeam;
    }

    @Override
    public boolean isDrawnOrAbandoned() {
        return isDrawn;
    }

    @Override
    public String toString() {
        return "Winner: " + winnerTeam;
    }
}
