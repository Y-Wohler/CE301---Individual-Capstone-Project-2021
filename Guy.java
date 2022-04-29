package com.andriodapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Guy {
    public int sleeps=0;
    public boolean move = false;
    public boolean isGoingLeft = false;
    public boolean isGoingRight = false;    //Down
    public boolean starting = true;
    int x,y,width,height;

    Bitmap walk1;
    Guy(int screenX, Resources res) {

        walk1 = BitmapFactory.decodeResource(res, R.drawable.bk);
        width = walk1.getWidth() / 3;
        height = walk1.getHeight();
        width *= GameView.screenRatioX;
        height *= GameView.screenRatioY;
        walk1 = Bitmap.createScaledBitmap(walk1,width,height,false);
        x = screenX / 2;
        y = (int) (64 * GameView.screenRatioY);

    }
    Bitmap getGuy() throws InterruptedException {
            return walk1;
    }
    Rect getCollisionRect() {
        return new Rect(x,y,x+width,y+height);
    }
}
