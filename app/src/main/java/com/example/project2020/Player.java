package com.example.project2020;


public class Player {
    String Username;
    String Password;
    String Email;
    int numberOfGames;
    int logestGame;
    int MostGoldEarnedInSingleGame;
    int longestCombo;
    int currentGold;
    int highScore;
    boolean SE;
    boolean BK;

   public Player(String username, String password) {
        Password = password;
        Username=username;
    }

    public Player() {

    }
 public Player(String username, String password, String email) {
        Username = username;
        Password = password;
        Email = email;
        numberOfGames=0;
        logestGame=0;
        MostGoldEarnedInSingleGame=0;
        longestCombo=0;
        currentGold=0;
        highScore=0;
        BK=true;
        SE=true;
    }
    public Player(String username, String password, String email, int numberOfGames, int logestGame, int currentGold,int highScore) {
        Username = username;
        Password = password;
        Email = email;
        this.numberOfGames = numberOfGames;
        this.logestGame = logestGame;
        this.currentGold = currentGold;
    }
    public Player( String username,int numberOfGames, int logestGame, int mostGoldEarnedInSingleGame, int longestCombo, int currentGold,int highScore){
        Username = username;
        this.numberOfGames = numberOfGames;
        this.logestGame = logestGame;
        MostGoldEarnedInSingleGame = mostGoldEarnedInSingleGame;
        this.longestCombo = longestCombo;
        this.currentGold = currentGold;
        this.highScore=highScore;
    }


    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public boolean isSE() {
        return SE;
    }

    public void setSE(boolean SE) {
        this.SE = SE;
    }

    public boolean isBK() {
        return BK;
    }

    public void setBK(boolean BK) {
        this.BK = BK;
    }

    public Player(String username, String password, String email, int numberOfGames, int logestGame, int mostGoldEarnedInSingleGame, int longestCombo, int currentGold, int highScore, boolean BK, boolean SE) {
         Username = username;
         Password = password;
         Email = email;
         this.numberOfGames = numberOfGames;
         this.logestGame = logestGame;
         MostGoldEarnedInSingleGame = mostGoldEarnedInSingleGame;
         this.longestCombo = longestCombo;
         this.currentGold = currentGold;
         this.highScore=highScore;
         this.SE=SE;
         this.BK=BK;
     }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public int getLogestGame() {
        return logestGame;
    }

    public void setLogestGame(int logestGame) {
        this.logestGame = logestGame;
    }

    public void setMostGoldEarnedInSingleGame(int mostGoldEarnedInSingleGame) {
        MostGoldEarnedInSingleGame = mostGoldEarnedInSingleGame;
    }

    public void setLongestCombo(int longestCombo) {
        this.longestCombo = longestCombo;
    }

    public int getCurrentGold() {
        return currentGold;
    }

    public void setCurrentGold(int currentGold) {
        this.currentGold = currentGold;
    }
    public int getMostGoldEarnedInSingleGame() {
        return MostGoldEarnedInSingleGame;
    }

    public int getLongestCombo() {
        return longestCombo;
    }
}


