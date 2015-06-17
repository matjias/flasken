package dk.dtu.gruppe9.flaskehalsenpegerp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;


public class IntroSplash extends Activity {
    private final int INTROSPLASH_LENGTH = 50000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                endSplash();
            }
        }, INTROSPLASH_LENGTH);


        View view = findViewById(android.R.id.content);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                endSplash();
                return false;
            }
        });
    }

    private void endSplash() {
        Intent jumpToGame = new Intent(IntroSplash.this, GameActivity.class);
        jumpToGame.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(jumpToGame);
        finish();
    }
}
