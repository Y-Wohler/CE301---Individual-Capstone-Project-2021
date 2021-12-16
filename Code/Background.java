package com.example.andriodapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Background {
    int x=0,y=0;
    Bitmap background, background2;
    

    Background(int screenX, int screenY, Resources res){
//        background = BitmapFactory.decodeResource(res,R.drawable.bk2);
        background2 = BitmapFactory.decodeResource(res, R.drawable.bk);
        background = BitmapFactory.decodeResource(res,R.drawable.bk2);
        background2 = Bitmap.createScaledBitmap(background2, screenX, screenY*6,false);
        background = Bitmap.createScaledBitmap(background, screenX, screenY*6, false);
    }
}
