package com.example.project2020;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Switch;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import androidx.annotation.RequiresApi;


public class GameView extends View {




    // add setting button using X and Y from touch listener
    @RequiresApi(api = Build.VERSION_CODES.O)

    public void onClick(View view) {
        view.requestPointerCapture();
    }

    public boolean onCapturedPointerEvent(MotionEvent motionEvent) {
        float verticalOffset = motionEvent.getY();
        System.out.println(verticalOffset);
        return true;
    }

    public boolean onCapturedPointer(View view, MotionEvent motionEvent) {
        // Get the coordinates required by your app

        float horizontalOffset = motionEvent.getX();
        System.out.println(horizontalOffset);
        return true;
    }

    @Override
    public boolean onTouchEvent (MotionEvent event){
        float Ytouch = event.getY();// y
        float Xtouch = event.getX();//x
        System.out.println("Xtouch"+Xtouch);
        System.out.println("Ytouch"+Ytouch);
        long eventDuration = android.os.SystemClock.elapsedRealtime() - event.getDownTime();

        if (T % 4 == 0 && T != 1) {
            z = (int) eventDuration;
            up = true;
        }
        int action = event.getAction();
        if(action==MotionEvent.ACTION_MOVE){
        }
        else
        if (!(Xtouch > 0 && Xtouch < 105*fx && Ytouch > 0 && Ytouch < 106*fy)&&!settingpress) {
            T++;
        }

        else {
            settingpress = true;
            if(Xtouch>685*fx&&Xtouch<770*fx&&Ytouch>195*fy&&Ytouch<280*fy&&action==MotionEvent.ACTION_UP){
                bm=!bm;
                updateBK(player.getUsername(),bm);
            }

            if(Xtouch>685*fx&&Xtouch<770*fx&&Ytouch>295*fy&&Ytouch<380*fy&&action==MotionEvent.ACTION_UP){
                se=!se;
                updateSE(player.getUsername(),se);
            }

        }
        if(T%3==0)
            flag=true;
        if (action == MotionEvent.ACTION_UP && !settingpress&&T%5==0) {
            touch = false;

        }

        return true;


    }
    Random random = new Random();
    SoundPlayer soundPlayer;
    Boolean finish=false,recreate=false,done=true,se,bm;
    Animator animator;
    Player player ;
    Handler handler;
    Arrow arrow1;
    int arrowheadx,arrowheady,score=0,highscore=0;
    Runnable runnable;
    int rotation = 10,timee,maxCombo,maxGoldEarned=0;
    float xcoordinate=0f,ycoordinate=0f;
    double angle=0;
    Rect rect;
    Rect rect1;
    long startTime, timeInMilliseconds = 0;
    Handler customHandler = new Handler();
    Bitmap background, apple, Bow, settings,bow, bowafter, arrow,Score,gameoverback,settingback,coin;
    Point point;
    Display display;
   int   applecorx = (random. nextInt(644)+483), applecory=random.nextInt(428)+100;
   int heartnum=3;
    final int Delay = 30, Speed = 55;
    int delayfinish=0;
    float fx;
    float fy;
    final double speedHeight = 10, Gravity = -10;
    int x = 5, ground, Helpx, Helpy, Time = 1,combo=0;
    double arrowx, arrowy, pressTime;
    boolean appleboolean=true,firsttime=true;
    FirebaseDatabase database;
    DatabaseReference PlayerInfo;
    double yspeed=0, xspeed=0, yEquation, xEquation;
    int rotateafter;
    double z = 0;
    boolean check = true, touch = true, flag = false, up = false, first = true, settingpress = false, bound = true, maxDegree = true,angleboolean=true,finishgame=true;
    int width=1184, height=720, heartFrame = 0, bowFrame = 0, T = 1,wid,hei; // work
    Bitmap[]  heart1, heart3, heart2,number,numberscore,boxBM,boxSE;
    View view;
    Context context;
    private MotionEvent event;
    Intent intent12;
    public GameView(Context context,Player player) {
        super(context);
        this.context=context;
        soundPlayer=new SoundPlayer(context);
        handler = new Handler();
        this.player=player;
        database = FirebaseDatabase.getInstance();
        PlayerInfo= database.getReference("PlayersInfo");
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        Arrow arrow1;
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        wid = point.x;
        hei = point.y;
        System.out.println(wid+" shar"+hei);
        fx =(wid/1184f);
        System.out.println(fx+" wt "+2094/1184);
        fy=hei/720f;
        applecorx =(int)(applecorx*fx);
        applecory =(int)(applecory*fy);
        System.out.println(fy+"  wt1 "+1080/720);
        ground = height - 200;
        rect = new Rect(0, 0, wid, hei);
        rect1 = new Rect(10, 10, width, height);
        bow =BitmapFactory.decodeResource(getResources(), R.drawable.bow);
        bowafter = BitmapFactory.decodeResource(getResources(), R.drawable.bowbow);
        arrow =  BitmapFactory.decodeResource(getResources(), R.drawable.cropped);
        Score = BitmapFactory.decodeResource(getResources(),R.drawable.score);
        heart1 = new Bitmap[2];
        heart2 = new Bitmap[2];
        heart3 = new Bitmap[2];
        boxBM= new Bitmap[2];
        boxSE= new Bitmap[2];
        number=new Bitmap[10];
        numberscore=new Bitmap[10];
        bm=player.isBK();
        se=player.isSE();
        settings = BitmapFactory.decodeResource(getResources(), R.drawable.finalsettingsbutton);
        settingback=BitmapFactory.decodeResource(getResources(),R.drawable.settings);
        gameoverback= BitmapFactory.decodeResource(getResources(),R.drawable.gameover);
        boxBM[0] =BitmapFactory.decodeResource(getResources(),R.drawable.emptybox);
        boxSE[0] =BitmapFactory.decodeResource(getResources(),R.drawable.emptybox);
        boxBM[1] =BitmapFactory.decodeResource(getResources(),R.drawable.tickedbox);
        boxSE[1] =BitmapFactory.decodeResource(getResources(),R.drawable.tickedbox);
        number[0]=BitmapFactory.decodeResource(getResources(),R.drawable.n0);
        number[1]=BitmapFactory.decodeResource(getResources(),R.drawable.n1);
        number[2]=BitmapFactory.decodeResource(getResources(),R.drawable.n2);
        number[3]=BitmapFactory.decodeResource(getResources(),R.drawable.n3);
        number[4]=BitmapFactory.decodeResource(getResources(),R.drawable.n4);
        number[5]=BitmapFactory.decodeResource(getResources(),R.drawable.n5);
        number[6]=BitmapFactory.decodeResource(getResources(),R.drawable.n6);
        number[7]=BitmapFactory.decodeResource(getResources(),R.drawable.n7);
        number[8]=BitmapFactory.decodeResource(getResources(),R.drawable.n8);
        number[9]=BitmapFactory.decodeResource(getResources(),R.drawable.n9);
        coin = BitmapFactory.decodeResource(getResources(),R.drawable.coin);
        heart1[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fullheart);
        heart2[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fullheart);
        heart3[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fullheart);
        heart1[1] = BitmapFactory.decodeResource(getResources(), R.drawable.emptyheart);
        heart2[1] = BitmapFactory.decodeResource(getResources(), R.drawable.emptyheart);
        heart3[1] = BitmapFactory.decodeResource(getResources(), R.drawable.emptyheart);
        apple = BitmapFactory.decodeResource(getResources(), R.drawable.apple);
    }

  /*  public GameView  recreate(){
        GameView gameView = new GameView(context);
        return gameView;
    }*/

    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.cropped, options);
        int arrowheight = options.outHeight;//12px
        int arrowwidth = options.outWidth;
        canvas.drawBitmap(background, null, rect, null);
        canvas.drawBitmap(settings, ((int)15*fx), 15*fy, null);
        canvas.drawBitmap(Score, 180*fx, 25*fy, null);
        final int distance = 60,distancescore=40; //distance between each number
        //  canvas.drawBitmap(Score2,380,25,null);
        System.out.println(fx+"  asd "+fy);

        switch (String.valueOf(score).length()) {
            case 1:
                canvas.drawBitmap(number[0], 380*fx, 15*fy, null);
                break;
            case 2:
                canvas.drawBitmap(number[score / 10], 380*fx, 15*fy, null);
                canvas.drawBitmap(number[0], (380 + distance)*fx, 15*fy, null);

                break;
            case 3:
                canvas.drawBitmap(number[score / 100], 380*fx, 15*fy, null);
                canvas.drawBitmap(number[(score / 10) % 10], (380 + distance)*fx, 15*fy, null);
                canvas.drawBitmap(number[0], (380 + distance * 2)*fx, 15*fy, null);
                break;
            case 4:
                canvas.drawBitmap(number[score / 1000], 380*fx, 15*fy, null);
                canvas.drawBitmap(number[(score / 100) % 10], (380 + distance)*fx, 15*fy, null);
                canvas.drawBitmap(number[(score / 10) % 10],(380 + distance * 2)*fx, 15*fy, null);
                canvas.drawBitmap(number[0],( 380 + distance * 3)*fx, 15*fy, null);


        }
        switch (heartnum) {
            case 3:
                canvas.drawBitmap(heart1[0], (width - 275)*fx, 20*fy, null);
                canvas.drawBitmap(heart2[0], (width - 185)*fx, 20*fy, null);
                canvas.drawBitmap(heart3[0], (width - 100)*fx, 20*fy, null);
                break;
            case 2:
                canvas.drawBitmap(heart1[1], (width - 275)*fx, 20*fy, null);
                canvas.drawBitmap(heart2[0], (width - 185)*fx, 20*fy, null);
                canvas.drawBitmap(heart3[0], (width - 100)*fx, 20*fy, null);
                break;
            case 1:
                canvas.drawBitmap(heart1[1], (width - 275)*fx, 20*fy, null);
                canvas.drawBitmap(heart2[1], (width - 185)*fx, 20*fy, null);
                canvas.drawBitmap(heart3[0], (width - 100)*fx, 20*fy, null);
                break;
            case 0:
                finishgame = false;
                float value=0;
                Matrix matrix=new Matrix();//455,322
                matrix.postTranslate(145*fx,40*fy);
                if(fx<1&&fy<1){
                    matrix.postScale(fx*1.2f,fy*1.2f);
                    value=fx*0.85f;
                }
                if(fx>1&&fy>1){
                    matrix.postScale(fx*0.73f,fy*0.73f);
                    value =fx*1.15f;
                }

                canvas.drawBitmap(gameoverback, matrix, null);
                System.out.println(135*fx);
                canvas.drawBitmap(coin,330*fx*1.15f,259*fy,null);
                if(done){
                    if(se)
                    soundPlayer.playGameOver();
                if(maxCombo<player.getLongestCombo())
                    maxCombo=player.getLongestCombo();
                 maxGoldEarned=score/10;
                if(score/10<player.getMostGoldEarnedInSingleGame())
                    maxGoldEarned=player.getMostGoldEarnedInSingleGame();
                highscore=score;
                if(score<player.getHighScore())
                    highscore=player.getHighScore();
                updatedata(player.getUsername(),new Player(player.getUsername(),player.getNumberOfGames()+1,0,maxGoldEarned,maxCombo,player.getCurrentGold()+score/10,highscore));
                    done =false;}
                switch (String.valueOf(score).length()) {
                    case 1:
                        canvas.drawBitmap(number[0], 625*fx, 465*fy, null);
                        break;
                    case 2:
                        canvas.drawBitmap(number[score / 10], 625*fx, 465*fy, null);
                        canvas.drawBitmap(number[0], (625 + distance)*fx, 465*fy, null);
                        break;
                    case 3:
                        canvas.drawBitmap(number[score / 100], 625*fx, 465*fy, null);
                        canvas.drawBitmap(number[(score / 10) % 10], (625 + distance)*fx + distance, 465*fy, null);
                        canvas.drawBitmap(number[0], (625 + distance*2)*fx, 465*fy, null);
                        break;
                    case 4:
                        canvas.drawBitmap(number[score / 1000], 625*fx, 465*fy, null);
                        canvas.drawBitmap(number[(score / 100) % 10], (625 + distance)*fx, 465*fy, null);
                        canvas.drawBitmap(number[(score / 10) % 10], (625 + distance*2)*fx, 465*fy, null);
                        canvas.drawBitmap(number[0], (625 + distance*3)*fx, 465*fy, null);
                        break;

                }
                System.out.println(player.getHighScore());
                System.out.println(highscore);
                System.out.println(score);

                switch (String.valueOf(highscore).length()){

                    case 1:
                        canvas.drawBitmap(number[0], 645*fx , 362*fy, null);
                        break;
                    case 2:
                        canvas.drawBitmap(number[highscore / 10], 645*fx  , 362*fy, null);
                        canvas.drawBitmap(number[0], (645 + distance)*fx, 362*fy, null);
                        break;
                    case 3:
                        canvas.drawBitmap(number[highscore / 100], 645*fx  , 362*fy, null);
                        canvas.drawBitmap(number[(highscore / 10) % 10], (645 + distance)*fx, 362*fy, null);
                        canvas.drawBitmap(number[0], (645 + distance * 2)*fx, 362*fy, null);
                        break;
                    case 4:
                        canvas.drawBitmap(number[highscore / 1000],645*fx  , 362*fy, null);
                        canvas.drawBitmap(number[(highscore / 100) % 10], (645 + distance )*fx, 362*fy, null);
                        canvas.drawBitmap(number[(highscore / 10) % 10], (645 + distance * 2)*fx, 362*fy, null);
                        canvas.drawBitmap(number[0], (645 + distance * 3)*fx, 362*fy, null);
                        break;
                }
                switch (String.valueOf(player.getCurrentGold()).length()){
                    case 1:
                        canvas.drawBitmap(number[player.getCurrentGold()%10], 420*value , 259*fy, null);
                        break;
                    case 2:
                        canvas.drawBitmap(number[player.getCurrentGold() / 10], 420*value , 259*fy, null);
                        canvas.drawBitmap(number[player.getCurrentGold()%10], (420 + distance)*value, 259*fy, null);
                        break;
                    case 3:
                        canvas.drawBitmap(number[player.getCurrentGold() / 100], 420*value, 259*fy, null);
                        canvas.drawBitmap(number[(player.getCurrentGold() / 10) % 10], (420 + distance)*value, 259*fy, null);
                        canvas.drawBitmap(number[player.getCurrentGold()%10], (420 + distance * 2)*value, 259*fy, null);
                        break;
                    case 4:
                        canvas.drawBitmap(number[player.getCurrentGold() / 1000],420*value , 259*fy, null);
                        canvas.drawBitmap(number[(player.getCurrentGold() / 100) % 10], (420 + distance)*value, 259*fy, null);
                        canvas.drawBitmap(number[(player.getCurrentGold() / 10) % 10], (420 + distance * 2)*value, 259*fy, null);
                        canvas.drawBitmap(number[player.getCurrentGold()%10], (420 + distance * 3)*value, 259*fy, null);
                        break;
                }
                break;

        }
        System.out.println(settingpress);
        if(settingpress){
            Matrix matrix=new Matrix();//455,322
            matrix.postTranslate(130*fx,25);
            if(fx!=1&&fy!=1)
            matrix.postScale(fx*0.75f,fy*0.75f);
            canvas.drawBitmap(settingback,matrix, null);
            System.out.println(bm+"  "+se);
            if(player.isBK()){
                canvas.drawBitmap(boxBM[1],690*fx,205*fy,null);
            }
            else{
                canvas.drawBitmap(boxBM[0],690*fx,205*fy,null);
            }
            if(player.isSE())
                canvas.drawBitmap(boxBM[1],690*fx,318*fy,null);
            else{
                canvas.drawBitmap(boxBM[0],690*fx,318*fy,null);
            }

        }
        Random random = new Random();
        if (finishgame) {
            if(!settingpress){
            if (!((arrowheady > applecory - 30 && arrowheady < applecory + 60) && (arrowheadx > applecorx - 25 && arrowheadx < applecorx + 55)) && appleboolean) {
                canvas.drawBitmap(apple, applecorx, applecory, null);
            } else {
                if (appleboolean == true&&se) {
                    soundPlayer.playHitSound();
                }
                appleboolean = false;
            }
      /* canvas.drawBitmap(black,width/3+41,height/2+44,null);
       canvas.drawBitmap(black,width /3,height/2+44,null);
      canvas.drawBitmap(black,width/3,height / 2,null);
        canvas.drawBitmap(black,width/3+41,height / 2,null);*/
            if (touch == true) {
                Matrix matrix = new Matrix();
                if (rotation == -47) {
                    maxDegree = true;
                }
                if (rotation == 46)
                    maxDegree = false;
                if (!flag) {
                    if (maxDegree && rotation < 46) {
                        matrix.postRotate(rotation, bow.getWidth() / 2, bow.getHeight() / 2);
                        rotation += 3;
                    }

                    if (!maxDegree && rotation > -48) {
                        matrix.postRotate(rotation, bow.getWidth() / 2, bow.getHeight() / 2);
                        rotation -= 3;
                    }
                } else {
                    matrix.postRotate(rotation, (bow.getWidth() / 2), bow.getHeight() / 2);
                }
                matrix.postTranslate((width / 9 - bow.getWidth() / 2)*fx, (height * 16 / 32 - bow.getHeight() / 2)*fy);
                canvas.drawBitmap(bow, matrix, null);
            }

            if (up) {

                if (first) {
                    pressTime = z;
                    if (pressTime > 1000)
                        pressTime = 1;
                    else {
                        pressTime = pressTime / 1000;
                    }
                }
                if (z != 0)
                    first = false;
                if (bound) {
                    bound = false;

                }

                rotateafter = rotation;

                if (angleboolean) {
                    yspeed = (pressTime * Math.sin(Math.toRadians(rotateafter)) * -Speed - (Time * 0.9));
                    xspeed = (pressTime * Math.cos(Math.toRadians(rotateafter)) * Speed * 1.65);
                }

                Matrix matrixafter = new Matrix();
                matrixafter.postRotate(rotateafter, bow.getWidth() / 2, bow.getHeight() / 2);
                matrixafter.postTranslate((width / 9 - bow.getWidth() / 2)*fx, (height * 16 / 32 - bow.getHeight() / 2)*fy);
                canvas.drawBitmap(bowafter, matrixafter, null);

                arrow1 = new Arrow(Time, yspeed, xspeed, arrow, width, height, arrow.getWidth(), arrow.getHeight(), canvas.getHeight() - arrowheight, canvas.getWidth() - arrowwidth,( width / 9 - arrow.getWidth() / 2)*fx, ((height - arrow.getHeight()) / 2)*fy, true, false, false,fx,fy);

                if (arrow1.getX() && arrow1.getY()) {
                    arrow1.setAb(true);
                    arrow1.setB(false);
                    arrow1.setA(false);

                } else {
                    arrow1.setTime(timee);
                    angleboolean = false;
                    arrow1.Setx(xcoordinate);
                    arrow1.Sety(ycoordinate);
                    arrow1.setAngle(angle);

                    if (arrow1.getX()) {
                        arrow1.setB(false);
                        arrow1.setA(true);
                        arrow1.setAb(false);

                    } else {
                        if (arrow1.getY()) {
                            arrow1.setB(true);
                            arrow1.setA(false);
                            arrow1.setAb(false);

                        } else {
                            arrow1.setB(false);
                            arrow1.setA(false);
                            arrow1.setAb(false);

                        }
                    }

                }

                canvas.drawBitmap(arrow, arrow1.getArrow(), null);
                if (arrow1.getAb()) {
                    timee = arrow1.Time;
                    xcoordinate = arrow1.x;
                    ycoordinate = arrow1.y;
                    angle = arrow1.getAngle();

                } else {
                    finish = true;
                    if (arrow1.isA()) {
                        xcoordinate = arrow1.x;
                        ycoordinate = (height- arrowheight - 110)*fy;
                    } else {


                        if (arrow1.isB()) {
                            ycoordinate = arrow1.y;
                            xcoordinate = canvas.getWidth() - arrowwidth - 50;
                        } else {

                            float ydiff = Math.abs(ycoordinate - canvas.getHeight() - arrowheight - 110);
                            float xdiff = Math.abs(xcoordinate - canvas.getWidth() - arrowwidth - 50);
                            if (ydiff > xdiff) {
                                xcoordinate = canvas.getWidth() - arrowwidth - 50;
                                if (firsttime && ydiff > 100) {
                                    firsttime = false;
                                    ycoordinate += (int) ((arrow1.y + arrow1.getySpeed()) * 0.05);
                                    angle += (angle / Math.abs(angle)) * 5;
                                }


                            } else {
                                ycoordinate = canvas.getHeight() - arrowheight - 90;
                                if (firsttime && xdiff > 100) {
                                    firsttime = false;
                                    xcoordinate += Math.abs(arrow1.xSpeed * (arrow1.Time)) * 0.1;
                                    angle += (angle / Math.abs(angle)) * 5;
                                }


                            }
                        }
                    }
                }
                arrowheady = (int)(arrow1.y + (int) (2 * Math.sqrt(Math.pow(56, 2) + Math.pow(19, 2)) * Math.sin(Math.toRadians(arrow1.getAngle()))));
                arrowheadx = (int) (arrow1.x + (int) (2 * Math.sqrt(Math.pow(56, 2) + Math.pow(19, 2)) * Math.cos(Math.toRadians(arrow1.getAngle()))));


                Time += 1;
            }
            if (finish) {
                delayfinish++;
                if (delayfinish == 4) {
                    if (appleboolean){
                        if(heartnum!=0)
                        heartnum--;
                        combo=0;
                    }
                    else {
                        score += 10;
                        combo++;
                        if(combo>maxCombo)
                            maxCombo=combo;
                    }
                    appleboolean = true;
                    firsttime = true;
                    finish = false;
                    recreate = false;
                    rotation = 10;
                    xcoordinate = 0;
                    ycoordinate = 0;
                    double angle = 0;
                    timeInMilliseconds = 0;
                    applecorx = (int)(fx*(random.nextInt(644) + 483));
                    applecory = (int)(fy*random.nextInt(428) + 100);
                    delayfinish = 0;
                    x = 5;
                    Time = 1;
                    z = 0;
                    check = true;
                    touch = true;
                    flag = false;
                    up = false;
                    first = true;
                    settingpress = false;
                    bound = true;
                    maxDegree = true;
                    angleboolean = true;
                    heartFrame = 0;
                    bowFrame = 0;
                    T = 1; // work

                }
            }


        }
    }
        handler.postDelayed(runnable, Delay);
    }
    private void updatedata(String key ,Player player1 ) {
        player.setCurrentGold(player1.getCurrentGold());
        player.setLogestGame(player1.getLogestGame());
        player.setLongestCombo(player1.getLogestGame());
        player.setMostGoldEarnedInSingleGame(player1.getMostGoldEarnedInSingleGame());
        player.setNumberOfGames(player1.getNumberOfGames());
        player.setHighScore(player1.getHighScore());
        PlayerInfo.child(key).setValue(player);
    }
    private void updateSE(String key ,boolean SE ) {
        player.setSE(SE);
        PlayerInfo.child(key).setValue(player);
        System.out.println("seee"+player.isSE());
    }
    private void updateBK(String key ,boolean BK ) {
        player.setBK(BK);
        PlayerInfo.child(key).setValue(player);
    }
}


