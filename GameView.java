package com.example.andriodapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying;
    private int screenX,screenY;
    public static float screenRatioX, screenRatioY;
    private final Paint paint;
    private Guy guy;
    private final Background background1;
    private final Background background2;

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;
        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());

        guy = new Guy(screenX, getResources());
        background2.y = screenY;
        paint = new Paint();

    }
    @Override
    public void run() {
        while (isPlaying) {
            update();
            try {
                draw();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            sleep();
        }
    }

    private void update(){
        if (guy.starting) {
            guy.y = (int) (800 * screenRatioY);
            guy.x = (int) (300 * screenRatioX);
            guy.starting =false;
        }

        if (guy.move) {
            background1.y -=200*screenRatioY;
            background2.y -=200*screenRatioY;
            guy.sleeps = 1;
        }

        if (background1.y + background1.background.getHeight() < 0) {
            background1.y = screenY;
        }
        if (background2.y + background2.background.getHeight() < 0) {
            background2.y = screenY;
        }
        if (guy.isGoingUp) {
            guy.y -= 35*screenRatioY;
        }
        if (guy.isGoingDown) {
            guy.y += (int) (40 * screenRatioY);
            guy.x += 1 * screenRatioX;
        }
        if (guy.y < 0){
            guy.y = 0;
        }
        if (guy.x < 0) {
            guy.x = 0;
        }
        if (guy.y > screenY - guy.height) {
            guy.y = screenY - guy.height;
        }
        if (guy.x > screenX - guy.width) {
            guy.x = screenX - guy.width;
        }
    }

    private void draw() throws InterruptedException {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x,background1.y,paint);
            canvas.drawBitmap(background2.background, background2.x,background2.y,paint);

            canvas.drawBitmap(guy.getGuy(),guy.x,guy.y,paint);
            getHolder().unlockCanvasAndPost(canvas);
        }

    }
    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();

    }
    public void pause() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (event.getY() < screenY / 2) {
                    guy.move = true;
                    guy.isGoingUp = true;
                }
                if (event.getY() > screenY / 2) {

                    guy.move = true;
                    guy.isGoingDown = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                guy.move = false;
                guy.isGoingDown = false;
                guy.isGoingUp = false;
                break;
        }
        return true;
    }
}
