package com.andriodapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;
import java.util.Random;

@SuppressLint("ViewConstructor")
public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying, isGameOver = false;
    private int hearts = 1;
    private final int screenX, screenY;
    private int score = 0;
    public int level = 2;
    public static float screenRatioX, screenRatioY;
    private final Paint paint;
    private final Walls[] walls;
    private final Random ran;
    private final Guy guy;
    private final Background background1, background2;
    private final GameOver gameOver;
    private final SharedPreferences preferences;
    private final GameActivity mainAct;


    public GameView(GameActivity mainAct, int screenX, int screenY) {
        super(mainAct);
        this.mainAct = mainAct;
        preferences = mainAct.getSharedPreferences("Dodger", Context.MODE_PRIVATE);
        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;
        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());
        gameOver = new GameOver(screenX, screenY, getResources());
        guy = new Guy(screenX, getResources());
        background2.y = screenY;
        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);
        walls = new Walls[5];
        for (int i = 0; i < 5; i++) {
            Walls wall = new Walls(getResources());
            walls[i] = wall;
            }
        ran = new Random();
    }
    @Override
    public void run() {
        background1.y -=200*screenRatioY;
        background2.y -=200*screenRatioY;
        guy.sleeps = 1;

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
        background1.y -=200*screenRatioY;
        background2.y -=200*screenRatioY;
        guy.sleeps = 1;
        if (guy.starting) {
            guy.y = (int) (800 * screenRatioY);
            guy.x = (int) (300 * screenRatioX);
            guy.starting =false;
        }
        if (background1.y + background1.background.getHeight() < 0) {
            background1.y = screenY;
        }
        if (background2.y + background2.background.getHeight() < 0) {
            background2.y = screenY;
        }
        if (guy.isGoingLeft) {
            guy.x += (int) (screenRatioX - 40);
        }
        if (guy.isGoingRight) {
            guy.x += (int) (screenRatioX + 40);
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
        for (Walls wall2: walls){
            wall2.y -= wall2.speed;
            if (wall2.y + wall2.height < 0) {
                int bound = (int) (100 * screenRatioY);
                score++;
                level+=1;
                wall2.speed = ran.nextInt(bound);
                levelUp(wall2);
                if (wall2.speed < 100 * screenRatioY) {
                    wall2.speed = (int) (100 * screenRatioY);
                }
                wall2.y = screenY;
                wall2.x = ran.nextInt(screenX - wall2.width);
            }
            if (Rect.intersects(wall2.getCollisionRect(), guy.getCollisionRect())) {
                if (hearts == 0) {
                    isGameOver = true;
                }
                    hearts-=1;
            }
        }
    }
    private void draw() throws InterruptedException {
        Canvas canvas = getHolder().lockCanvas();
        if (getHolder().getSurface().isValid()) {

            canvas.drawBitmap(background1.background, background1.x,background1.y,paint);
            canvas.drawBitmap(background2.background, background2.x,background2.y,paint);
            canvas.drawText(String.valueOf(score-5), screenX / 2f, 160, paint);


            if (isGameOver){
                if (hearts == 0){
                    canvas.drawBitmap(gameOver.gameOver, gameOver.x, gameOver.y, paint);
                }
                isPlaying = false;
                getHolder().unlockCanvasAndPost(canvas);

                saving();
                quit();
                return;
            }
            for (Walls wall : walls) {
                if (level >= walls.length) {
                    canvas.drawBitmap(wall.getWall1(), wall.x, wall.y, paint);
                }
            }
            canvas.drawBitmap(guy.getGuy(),guy.x,guy.y,paint);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void quit() throws InterruptedException {
        Thread.sleep(1000);
        mainAct.startActivity(new Intent(mainAct,MainActivity.class));
        mainAct.finish();
    }

    private void saving() {
        if (preferences.getInt("HighScore", 0) < score){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("HighScore", score);
            editor.apply();
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
    public void levelUp(Walls wall2) {
        System.out.println(level);
        if (level > 20) {
            wall2.speed *=3;
        }else if (level > 60){
            wall2.speed *= 10;
        }else if (level > 150){
            wall2.speed *= 25;
        }else {
            wall2.speed += 40;
        }

    }
        @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (event.getX() < screenX / 2) {
                    guy.move = true;
                    guy.isGoingLeft = true;
                }
                if (event.getX() > screenX / 2) {
                    guy.move = true;
                    guy.isGoingRight = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                guy.move = false;
                guy.isGoingRight = false;
                guy.isGoingLeft = false;
                break;
        }
        return true;
    }
}
