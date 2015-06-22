package dk.dtu.gruppe9.flaskehalsenpegerp;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import dk.dtu.gruppe9.flaskehalsenpegerp.views.Splash_BottleView;
import dk.dtu.gruppe9.flaskehalsenpegerp.views.Splash_TitleView;


public class IntroSplash extends Activity {
    private final int ROTATIONS = 3;
    ObjectAnimator bottleSpin;
    Splash_BottleView splashBottle;
    Splash_TitleView splashTitle;
    FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_splash);

        View view = findViewById(android.R.id.content);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bottleSpin.cancel();
                return false;
            }
        });

        frame = (FrameLayout) findViewById(R.id.splashFrame);

        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);

        int centerX = screenSize.x / 2;
        int centerY = screenSize.y / 2;

        // Sets up the title
        splashTitle = new Splash_TitleView(getApplicationContext(), centerX, centerY - screenSize.y / 5);
        splashBottle = new Splash_BottleView(getApplicationContext(), centerX, centerY);

        frame.addView(splashTitle);
        frame.addView(splashBottle);

        startAnimation();
    }

    private void endSplash() {
        Intent jumpToGame = new Intent(IntroSplash.this, GameActivity.class);
        jumpToGame.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(jumpToGame);
        finish();
    }

    private void startAnimation() {
        bottleSpin = ObjectAnimator.ofFloat(splashBottle, "rotation", 0, 360 * ROTATIONS);

        bottleSpin.setDuration(ROTATIONS * 2000);
        bottleSpin.setInterpolator(new LinearInterpolator());

        bottleSpin.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) { endSplash(); }

            @Override
            public void onAnimationCancel(Animator animation) { endSplash(); }

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });

        bottleSpin.start();
    }
}
