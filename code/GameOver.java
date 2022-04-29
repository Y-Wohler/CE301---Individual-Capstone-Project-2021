package com.andriodapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameOver {
    int x=0,y=0;
    Bitmap gameOver;

    GameOver(int screenX, int screenY, Resources res){
        gameOver = BitmapFactory.decodeResource(res,R.drawable.gameover);
        gameOver = Bitmap.createScaledBitmap(gameOver, screenX, screenY*6, false);
    }
}
