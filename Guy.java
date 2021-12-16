package com.example.andriodapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static com.example.andriodapp.GameView.screenRatioX;
import static com.example.andriodapp.GameView.screenRatioY;


public class Guy {
    public int sleeps=0;
    public boolean move = false;
    public boolean isGoingUp = false;
    public boolean isGoingDown = false;
    public boolean positioning = false;
    public boolean starting = true;
    int x,y,width,height, guyCounter = 0;
    Bitmap guy,guy1,guy2;
    Bitmap walk1,walk2,walk3,walk4,walk5,walk6,walk7;
    Guy(int screenX, Resources res) {
//        guy = BitmapFactory.decodeResource(res, R.drawable.guy);
//        guy1 = BitmapFactory.decodeResource(res, R.drawable.guy1);
//        guy2 = BitmapFactory.decodeResource(res, R.drawable.guy2);
        walk1 = BitmapFactory.decodeResource(res, R.drawable.walk1);
        walk2 = BitmapFactory.decodeResource(res, R.drawable.walk2);
        walk3 = BitmapFactory.decodeResource(res, R.drawable.walk3);
        walk4 = BitmapFactory.decodeResource(res, R.drawable.walk4);
        walk5 = BitmapFactory.decodeResource(res, R.drawable.walk5);
        walk6 = BitmapFactory.decodeResource(res, R.drawable.walk6);
        walk7 = BitmapFactory.decodeResource(res, R.drawable.walk7);

//        width = guy.getWidth();
//        height = guy.getHeight();
        width = walk1.getWidth();
        height = walk1.getHeight();
//        System.out.println(width);
//        System.out.println(screenRatioX);
//        System.out.println(screenRatioY);

//        width/=4;
//        height/=4;



        width *= screenRatioX;
        height *= screenRatioY;
        System.out.println("width:");
        System.out.println(width);
        System.out.println("Height:");
        System.out.println(height);

//        guy = Bitmap.createScaledBitmap(guy,width,height,false);
//        guy1 = Bitmap.createScaledBitmap(guy1,width,height,false);
//        guy2 = Bitmap.createScaledBitmap(guy2,width,height,false);
        walk1 = Bitmap.createScaledBitmap(walk1,width,height,false);
        walk2 = Bitmap.createScaledBitmap(walk2,width,height,false);
        walk3 = Bitmap.createScaledBitmap(walk3,width,height,false);
        walk4 = Bitmap.createScaledBitmap(walk4,width,height,false);
        walk5 = Bitmap.createScaledBitmap(walk5,width,height,false);
        walk6 = Bitmap.createScaledBitmap(walk6,width,height,false);
        walk7 = Bitmap.createScaledBitmap(walk7,width,height,false);


        x = screenX / 2;
        y = (int) (64 * screenRatioY);
    }
    public void sleeper() throws InterruptedException {
        if (sleeps == 0) {
//            Thread.sleep(75);
        }
    }

    Bitmap getGuy() throws InterruptedException {
        if (guyCounter == 0) {
            sleeps = 0;
            sleeper();
            sleeps = 1;
            guyCounter++;
            return walk1;
        } else if (guyCounter == 1) {
            sleeps = 0;
            sleeper();
            sleeps=1;
            guyCounter++;
            return walk2;
        }else if (guyCounter == 2){
            sleeps=0;
            sleeper();
            sleeps=1;
            guyCounter++;
            return walk3;
        }else if (guyCounter == 3){
            sleeps=0;
            sleeper();
            sleeps=1;
            guyCounter++;
            return walk4;
        }else if (guyCounter == 4){
            sleeps=0;
            sleeper();
            sleeps=1;
            guyCounter++;
            return walk5;
        }else if (guyCounter == 5) {
            sleeps=0;
            sleeper();
            sleeps=1;
            guyCounter++;
            return walk6;
        }else {
            sleeps=0;
            sleeper();
            sleeps=1;
            guyCounter=0;
            return walk7;
        }
    }
}
