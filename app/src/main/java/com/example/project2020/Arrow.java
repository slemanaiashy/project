package com.example.project2020;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.preference.PreferenceManager;

public class Arrow {
    protected int Time;
    protected double ySpeed;
    protected double xSpeed,Angle;
    protected Bitmap arrow;
    protected int width ;
    protected int height;
    protected  float x,fx;
    protected  float y,fy;
    protected int arrowHeight;
    protected int arrowWidth;
    protected int canvasHeight;
    protected int canvasWidth;
    protected int Abx,Aby;

    private boolean a,b,ab;

    public Arrow(Bitmap Arrow){
        arrow=Arrow;
    }

    public void setA(boolean a) {
        this.a = a;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public void setAb(boolean ab) {
        this.ab = ab;
    }
    public boolean getAb() {
        return ab;
    }

    public void setTime(int time) {
        Time = time;
    }

    public Arrow(int time, double ySpeed, double xSpeed, Bitmap arrow, int width, int height, int arrowHeight, int arrowWidth, int canvasHeight, int canvasWidth, float x1, float y1, boolean ab, boolean a , boolean b,float fx,float fy) {
        Time = time;
        this.ySpeed = ySpeed;
        this.xSpeed = xSpeed;
        this.arrow = arrow;
        this.width = width;
        this.height = height;
        this.x=x1;
        this.y=y1;
        this.a =a ;
        this.b=b;
        this.ab=ab;
        this.arrowHeight = arrowHeight;
        this.arrowWidth = arrowWidth;
        this.canvasHeight = canvasHeight;
        this.canvasWidth = canvasWidth;
        this.fx=fx;
        this.fy=fy;
    }
    public void Setx (float x ){
        this.x=x;
    }
    public void Sety (float y ){
        this.y=y;
    }
    public  double getstepy(){
       return y+getySpeed() ;
    }
    public  double getstepx(){
        return x+(int) Math.abs(xSpeed * (Time));
    }
    public void setAngle(double Angle){
        this.Angle=Angle;
    }
    public double getAngle(){//-angle
        return Math.atan((ySpeed) / xSpeed) * -57.2958 ;
    }
    public double getySpeed(){
        return (-ySpeed*Time+Time*Time/10);
    }
    public boolean getX(){
        return x +Math.abs(xSpeed * (Time))<=canvasWidth-(50)*fx;
    }
    public boolean getY(){
        return y+getySpeed()<=canvasHeight-(110)*fy;
    }
    public boolean isA() {
        return a;
    }
    public boolean isB() {
        return b;
    }
    public Matrix getArrow(){
        System.out.println("T: "+Time);
        Matrix matrix = new Matrix();//-110,-90
        if(ab){
            matrix.postRotate((int)getAngle(), arrowWidth/2, arrowHeight / 2);
            x =x+(int) Math.abs(xSpeed * Time);
            y = y+(int)getySpeed();
            matrix.postTranslate(x,y );
            return matrix;
        }
        if (a&&!ab) {

            matrix.postRotate((int)Angle, arrowWidth/2, arrowHeight / 2);
            matrix.postTranslate(x,canvasHeight-110 );

            return matrix;
        }
        if(b&&!ab){
            matrix.postRotate((int)Angle, arrowWidth/2, arrowHeight / 2);
            matrix.postTranslate(canvasWidth-50,y );

            return matrix;

        }
        if(!a&&!b&&!ab){
            matrix.postRotate((int)Angle, arrowWidth/2, arrowHeight / 2);
            matrix.postTranslate(x,y );
            return matrix;
        }
        return matrix;
    }
}
