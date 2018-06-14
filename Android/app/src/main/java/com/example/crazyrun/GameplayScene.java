package com.example.crazyrun;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;

public class GameplayScene implements Scene {
    private Rect r= new Rect();
    private RectPlayer player;
    private Point playerPoint;
    private ObstacleManager obstacleManager;

    private boolean movingPlayer =false;
    private boolean gameOver=false;

    private long gameOverTime;


    private OrientationData orientationData;
    private long frameTime;

    public GameplayScene(){
        player = new RectPlayer(new Rect(100,100,200,200), Color.rgb(55, 35, 80));

        playerPoint = new Point(Constants.SCREEN_WIDTH/2,3*Constants.SCREEN_HEIGHT/4);
        player.update(playerPoint);

        obstacleManager = new ObstacleManager(400, 700, 150, Color.BLUE);

        orientationData=new OrientationData();
        orientationData.register();
        frameTime=System.currentTimeMillis();
    }


    public void reset(){
        playerPoint = new Point(Constants.SCREEN_WIDTH/2,3*Constants.SCREEN_HEIGHT/4);
        player.update(playerPoint);
        obstacleManager = new ObstacleManager(400, 700, 150, Color.BLUE);
        movingPlayer=false;
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE=0;
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(!gameOver && player.getRectangle().contains((int)event.getX(), (int)event.getY()))
                    movingPlayer=true;
                if(gameOver && System.currentTimeMillis()-gameOverTime>=1000){
                    reset();
                    gameOver=false;
                    orientationData.newGame();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(movingPlayer && !gameOver)
                    playerPoint.set((int)event.getX(), (int)event.getY());
                break;
            case MotionEvent.ACTION_UP:
                movingPlayer=false;
                break;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(-70000);

        player.draw(canvas);

        obstacleManager.draw(canvas);

        if(gameOver){
            Paint paint = new Paint();
            paint.setTextSize(140);
            drawCenterText(canvas,paint, "PRZEGRANKO!!!");
        }
    }

    @Override
    public void update() {
        if (!gameOver) {
            if(frameTime<Constants.INIT_TIME)
                frameTime= Constants.INIT_TIME;
            int elapsedTime= (int)(System.currentTimeMillis()- frameTime);
            frameTime= System.currentTimeMillis();
            if(orientationData.getOrientation() !=null && orientationData.getStartOrientation() !=null){
                float pitch = orientationData.getOrientation()[1] - orientationData.getStartOrientation()[1];
                float roll = orientationData.getOrientation()[2] - orientationData.getStartOrientation()[2];

                float xSpeed=2* roll * Constants.SCREEN_WIDTH/1000f;
                float ySpeed=pitch * Constants.SCREEN_HEIGHT/1000f;

                playerPoint.x+=Math.abs(xSpeed*elapsedTime)>5? xSpeed*elapsedTime:0;
                playerPoint.y-=Math.abs(ySpeed*elapsedTime)>5? ySpeed*elapsedTime:0;
            }


            if(playerPoint.x < 0)
                playerPoint.x = 0;
            else if(playerPoint.x > Constants.SCREEN_WIDTH)
                playerPoint.x = Constants.SCREEN_WIDTH;
            if(playerPoint.y < 0)
                playerPoint.y = 0;
            else if(playerPoint.y > Constants.SCREEN_HEIGHT)
                playerPoint.y = Constants.SCREEN_HEIGHT;

            player.update(playerPoint);
            obstacleManager.update();

            if(obstacleManager.playerCollide(player)) {
                gameOver = true;
                gameOverTime = System.currentTimeMillis();
            }
        }
    }
    private void drawCenterText(Canvas yourCanvas, Paint mPaint, String pageTitle) {
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setColor(Color.argb(250, 200, 0, 125));
        r = yourCanvas.getClipBounds();
        RectF bounds = new RectF(r);
        bounds.right = mPaint.measureText(pageTitle, 0, pageTitle.length());
        bounds.bottom = mPaint.descent() - mPaint.ascent();
        bounds.left += (r.width() - bounds.right) / 2.0f;
        bounds.top += (r.height() - bounds.bottom) / 2.0f;
        yourCanvas.drawText(pageTitle, bounds.left, bounds.top - mPaint.ascent(), mPaint);
    }

}
