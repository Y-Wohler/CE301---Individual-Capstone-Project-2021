package com.andriodapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Walls {
    public int speed = 10;
    int x = 0, y, width, height, wallCounter = 1;
    Bitmap wall1, wall2, wall3, wall4,wall5;

    Walls(Resources res) {
        wall1 = BitmapFactory.decodeResource(res, R.drawable.wall);
        width = wall1.getWidth();
        height = wall1.getHeight();
        width /= 5;
        wall1 = Bitmap.createScaledBitmap(wall1, width, height, false);
        wall2 = Bitmap.createScaledBitmap(wall1, width, height, false);
        wall3 = Bitmap.createScaledBitmap(wall1, width, height, false);
        wall4 = Bitmap.createScaledBitmap(wall1, width, height, false);
        wall5 = Bitmap.createScaledBitmap(wall1, width, height, false);
        y = -height;
    }

    Bitmap getWall1() {
        if (wallCounter == 1) {
            wallCounter++;
            return wall1;
        }
        return wall1;
    }
    Rect getCollisionRect () {
        return new Rect(x,y,x+width,y+height);
    }
}