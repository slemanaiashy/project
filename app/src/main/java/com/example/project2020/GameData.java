package com.example.project2020;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.provider.Settings;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.InterfaceAddress;
import java.util.Map;

import androidx.annotation.NonNull;

public class GameData {
    protected   int MostGoldEarnedInSingleGame;
    protected int longestCombo;
    protected  int HighestGold;
    protected int HighestScore;

    public GameData(int  MostGoldEarnedInSingleGame,int longestCombo,int HighestGold,int HighestScore){
        this.MostGoldEarnedInSingleGame=MostGoldEarnedInSingleGame;
        this.longestCombo=longestCombo;
        this.HighestGold=HighestGold;
        this.HighestScore=HighestScore;
    }

    public int getMostGoldEarnedInSingleGame() {
        return MostGoldEarnedInSingleGame;
    }

    public int getLongestCombo() {
        return longestCombo;
    }

    public int getHighestGold() {
        return HighestGold;
    }

    public int getHighestScore() {
        return HighestScore;
    }

    public void setMostGoldEarnedInSingleGame(int mostGoldEarnedInSingleGame) {
        MostGoldEarnedInSingleGame = mostGoldEarnedInSingleGame;
    }

    public void setLongestCombo(int longestCombo) {
        this.longestCombo = longestCombo;
    }

    public void setHighestGold(int highestGold) {
        HighestGold = highestGold;
    }

    public void setHighestScore(int highestScore) {
        HighestScore = highestScore;
    }
}


