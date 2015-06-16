package dk.dtu.gruppe9.flaskehalsenpegerp;


import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import dk.dtu.gruppe9.flaskehalsenpegerp.views.BottleView;


public class GameActivity extends Activity {

    FrameLayout frame;
    BottleView bottleView;
    int bottleWidth, bottleHeight, centerX, centerY;

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
        // New bottle
        bottleView = new BottleView(getApplicationContext(), size.x, size.y, centerX, centerY);

        // Adds bottle to Game frame
        frame.addView(bottleView);


    }





}
