package com.example.project2020;

public class GameData {
    protected  double AverageGameLength;
    protected  double AverageHoursplayedPerday;
    protected   int MostGoldEarnedInSingleGame;
    protected int longestCombo;
    protected  String HighestGold;
    protected int HighestScore;

    public GameData() {
         AverageGameLength=0;
         AverageHoursplayedPerday=0;
        MostGoldEarnedInSingleGame=0;
        longestCombo=0;
        HighestGold="0";
        HighestScore=0;
    }

    public double getAverageGameLength() {
        return AverageGameLength;
    }

    public void setAverageGameLength(double averageGameLength) {
        AverageGameLength = averageGameLength;
    }

    public double getAverageHoursplayedPerday() {
        return AverageHoursplayedPerday;
    }

    public void setAverageHoursplayedPerday(double averageHoursplayedPerday) {
        AverageHoursplayedPerday = averageHoursplayedPerday;
    }

    public int getMostGoldEarnedInSingleGame() {
        return MostGoldEarnedInSingleGame;
    }

    public void setMostGoldEarnedInSingleGame(int mostGoldEarnedInSingleGame) {
        MostGoldEarnedInSingleGame = mostGoldEarnedInSingleGame;
    }

    public int getLongestCombo() {
        return longestCombo;
    }

    public void setLongestCombo(int longestCombo) {
        this.longestCombo = longestCombo;
    }

    public String getHighestGold() {
        return HighestGold;
    }

    public void setHighestGold(String highestGold) {
        HighestGold = highestGold;
    }

    public int getHighestScore() {
        return HighestScore;
    }

    public void setHighestScore(int highestScore) {
        HighestScore = highestScore;
    }
}
