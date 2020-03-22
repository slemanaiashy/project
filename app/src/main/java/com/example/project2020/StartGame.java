package com.example.project2020;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class StartGame extends Activity {
    GameView gameView;
    ImageButton Play;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Play= findViewById(R.id.Playbutton);
        Intent i = getIntent();
        //  numberOfGames=0;
        //        logestGame=0;
        //        MostGoldEarnedInSingleGame=0;
        //        longestCombo=0;
        //        currentGold=0;
        //     s.putExtra("gamenum",player1.getNumberOfGames());
        //                           s.putExtra("longestgame",player1.getLogestGame());
        //                           s.putExtra("mostgoldinasinglegame",player1.getMostGoldEarnedInSingleGame());
        //                           s.putExtra("longestcombo",player1.getLongestCombo());
        //                           s.putExtra("currentgold",player1.getCurrentGold());
        if(i.getExtras().getBoolean("login")){
      Player player=new Player(i.getExtras().getString("username"),i.getExtras().getString("password"),i.getExtras().getString("email"),i.getExtras().getInt("gamenum"),i.getExtras().getInt("longestgame"),i.getExtras().getInt("mostgoldinasinglegame"),i.getExtras().getInt("longestcombo"),i.getExtras().getInt("currentgold"),i.getExtras().getInt("highscore"),i.getExtras().getBoolean("BK"),i.getExtras().getBoolean("SE"));
        gameView = new GameView(this,player);}
        else{
            gameView=new GameView(this,new Player(i.getExtras().getString("username"),i.getExtras().getString("password"),i.getExtras().getString("email")));
        }
        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContent(gameView);
            }
        });
    }
    public void setContent(GameView gameView){
        setContentView(gameView);
    }
}
