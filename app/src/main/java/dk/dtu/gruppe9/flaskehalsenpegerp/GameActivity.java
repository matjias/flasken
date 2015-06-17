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
    int centerX, centerY;
    Random rand = new Random();
    final int ROTATE_RATE_DELAY = 40;

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

                    startRotation(1080f + rand.nextFloat() * 360f);
                    return true;
                }
                return false;
            }
            /*
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                System.out.println("x: " + velocityX);
                System.out.println("y: " + velocityY);
                System.out.println("rot: " + bottleView.getRotation()%360f);

                float tempRot = 0;

                if(bottleView.intersects(e1.getX(),e1.getY())){

                    float rotationDeg = bottleView.getRotation();

                    if((rotationDeg % 360f) > 90 || (rotationDeg % 360f) < 270) velocityY *= -1;
                    if((rotationDeg % 360f) >=0 && (rotationDeg % 360f) < 180) velocityX *= -1;


                    float angleRad = (float)Math.toRadians(360f - bottleView.getRotation() % 360f);

                    System.out.println("angle: " + angleRad);

                    System.out.println("sin: " + Math.sin(angleRad));
                    System.out.println("cos: " + Math.cos(angleRad));

                    float speedY = velocityY / ROTATE_RATE_DELAY * (float)Math.cos(angleRad);
                    float speedX = velocityX / ROTATE_RATE_DELAY * (float)Math.sin(angleRad);

                    System.out.println("speed X: " + speedX);
                    System.out.println("speed Y: " + speedY);

                    //
                    tempRot = (float)speedX + speedY;

                    System.out.println("temp " + tempRot);
                }

                startRotation(tempRot);
                return false;
            }*/
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        gestureDetector.onTouchEvent(event);

        return false;

    }

    private void startRotation(float rotEnd){

        float rotStart = bottleView.getRotation() % 360f;

        ObjectAnimator bottleAnim = ObjectAnimator.ofFloat(bottleView, "rotation", rotStart, rotEnd);

        bottleAnim.setDuration(2000);
        bottleAnim.setInterpolator(new DecelerateInterpolator());

        bottleAnim.start();

    }

}
