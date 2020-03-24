package com.example.project2020;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class StartGame extends Activity {
    GameView gameView;
    ImageButton easy,med,hard,shop,logout;
    private static final String PREFS_NAME="PrefsFile";
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE );
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        easy = findViewById(R.id.easy);
        med = findViewById(R.id.med);
        hard = findViewById(R.id.hard);
        shop = findViewById(R.id.shop);
        logout = findViewById(R.id.logoutbut);
        final Intent i = getIntent();
        final Context context=this;
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

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i.getExtras().getBoolean("login")){
                    Player player=new Player(i.getExtras().getString("username"),i.getExtras().getString("password"),i.getExtras().getString("email"),i.getExtras().getInt("gamenum"),i.getExtras().getInt("longestgame"),i.getExtras().getInt("mostgoldinasinglegame"),i.getExtras().getInt("longestcombo"),i.getExtras().getInt("currentgold"),i.getExtras().getInt("highscore"),i.getExtras().getBoolean("BK"),i.getExtras().getBoolean("SE"));
                    gameView = new GameView(context,player,1);}
                else{
                    gameView=new GameView(context,new Player(i.getExtras().getString("username"),i.getExtras().getString("password"),i.getExtras().getString("email")),1);
                }
                setContent(gameView);
            }
        });
        med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i.getExtras().getBoolean("login")){
                    Player player=new Player(i.getExtras().getString("username"),i.getExtras().getString("password"),i.getExtras().getString("email"),i.getExtras().getInt("gamenum"),i.getExtras().getInt("longestgame"),i.getExtras().getInt("mostgoldinasinglegame"),i.getExtras().getInt("longestcombo"),i.getExtras().getInt("currentgold"),i.getExtras().getInt("highscore"),i.getExtras().getBoolean("BK"),i.getExtras().getBoolean("SE"));
                    gameView = new GameView(context,player,2);}
                else{
                    gameView=new GameView(context,new Player(i.getExtras().getString("username"),i.getExtras().getString("password"),i.getExtras().getString("email")),2);
                }
                setContent(gameView);
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i.getExtras().getBoolean("login")){
                    Player player=new Player(i.getExtras().getString("username"),i.getExtras().getString("password"),i.getExtras().getString("email"),i.getExtras().getInt("gamenum"),i.getExtras().getInt("longestgame"),i.getExtras().getInt("mostgoldinasinglegame"),i.getExtras().getInt("longestcombo"),i.getExtras().getInt("currentgold"),i.getExtras().getInt("highscore"),i.getExtras().getBoolean("BK"),i.getExtras().getBoolean("SE"));
                    gameView = new GameView(context,player,3);}
                else{
                    gameView=new GameView(context,new Player(i.getExtras().getString("username"),i.getExtras().getString("password"),i.getExtras().getString("email")),3);
                }
                setContent(gameView);
            }
        });
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i.getExtras().getBoolean("login")){
                    Player player=new Player(i.getExtras().getString("username"),i.getExtras().getString("password"),i.getExtras().getString("email"),i.getExtras().getInt("gamenum"),i.getExtras().getInt("longestgame"),i.getExtras().getInt("mostgoldinasinglegame"),i.getExtras().getInt("longestcombo"),i.getExtras().getInt("currentgold"),i.getExtras().getInt("highscore"),i.getExtras().getBoolean("BK"),i.getExtras().getBoolean("SE"));
                    gameView = new GameView(context,player,4);}
                else{
                    gameView=new GameView(context,new Player(i.getExtras().getString("username"),i.getExtras().getString("password"),i.getExtras().getString("email")),4);
                }
                setContent(gameView);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                intent.putExtra("check",false);
                startActivity(intent);
            }
        });
    }
    public void setContent(GameView gameView){
        setContentView(gameView);
    }
}
