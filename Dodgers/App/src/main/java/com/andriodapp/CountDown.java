package com.andriodapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class CountDown {
    int x = 0,y = 0;
    Bitmap counting3, counting2, counting1, countingGo;

    CountDown(int screenX, int screenY, Resources res){
        counting3 = BitmapFactory.decodeResource(res, R.drawable.three);
        counting2 = BitmapFactory.decodeResource(res, R.drawable.two);
        counting1 = BitmapFactory.decodeResource(res, R.drawable.one);
        countingGo = BitmapFactory.decodeResource(res, R.drawable.go);

        counting3 = Bitmap.createScaledBitmap(counting3, screenX, screenY, false);
        counting2 = Bitmap.createScaledBitmap(counting2, screenX, screenY, false);
        counting1 = Bitmap.createScaledBitmap(counting1, screenX, screenY, false);
        countingGo = Bitmap.createScaledBitmap(countingGo, screenX, screenY, false);
    }
}
