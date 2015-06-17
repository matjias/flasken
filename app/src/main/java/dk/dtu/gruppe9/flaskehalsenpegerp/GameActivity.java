package dk.dtu.gruppe9.flaskehalsenpegerp;


import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;

import java.util.Random;

import dk.dtu.gruppe9.flaskehalsenpegerp.views.BottleView;


public class GameActivity extends Activity {

    FrameLayout frame;
    BottleView bottleView;
    int bottleWidth, bottleHeight, centerX, centerY;
    Random rand = new Random();

    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Removes Title Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_game);

        // Hides Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        frame = (FrameLayout) findViewById(R.id.frame);




        // Sets bottle size according to screen size.
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        centerX = size.x/2;
        centerY = size.y/2;

        // New bottle
        bottleView = new BottleView(getApplicationContext(), centerX, centerY);

        // Adds bottle to Game frame
        frame.addView(bottleView);

        gestures();
    }

    private void gestures() {

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {

                if(bottleView.intersects(e.getX(), e.getY())){

                    startRotation();
                    return true;
                }
                return false;
            }

        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        gestureDetector.onTouchEvent(event);

        return false;

    }

    private void startRotation(){

        float rotStart = bottleView.getRotation() % 360f;
        float rotEnd = 1440f + rand.nextFloat() * 360f;

        ObjectAnimator bottleAnim = ObjectAnimator.ofFloat(bottleView, "rotation", rotStart, rotEnd);

        bottleAnim.setDuration(2000);
        bottleAnim.setInterpolator(new DecelerateInterpolator());

        bottleAnim.start();

    }

}
