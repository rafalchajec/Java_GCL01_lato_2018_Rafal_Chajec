package com.example.crazyrun;

import android.graphics.Canvas;
import android.graphics.Rect;

public class AnimationManager {

    private Animation [] animations;
    private int animationsIndex =0;
    public AnimationManager(Animation[] animations){
        this.animations=animations;
    }

    public void playAnim(int index){
        for( int i=0; i<animations.length; i++){
            if(i==index) {
                if(!animations[index].isPlaying())
                animations[i].play();
            }else
                animations[i].stop();
        }
        animationsIndex=index;
    }

    public void draw(Canvas canvas, Rect rect){
        if(animations[animationsIndex].isPlaying())
        animations[animationsIndex].draw(canvas,rect);
    }


    public void update(){
        if(animations[animationsIndex].isPlaying())
            animations[animationsIndex].update();
    }
}
