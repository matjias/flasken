package dk.dtu.gruppe9.flaskehalsenpegerp;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import java.util.Random;

import dk.dtu.gruppe9.flaskehalsenpegerp.views.BottleView;
import dk.dtu.gruppe9.flaskehalsenpegerp.views.PlayerView;


public class GameActivity extends Activity {

    FrameLayout frame;
    BottleView bottleView;
    int centerX, centerY, playerWon;
    final int GET_PLAYERS = 1;
    Random rand = new Random();
    final int ROTATE_RATE_DELAY = 40;
    PlayerView[] players;

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

        Intent menuIntent = new Intent(GameActivity.this, MenuActivity.class);
        startActivityForResult(menuIntent, GET_PLAYERS);

        gestures();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK && requestCode == GET_PLAYERS){
            setPlayers(data.getIntExtra("amount", 4));
        }
    }

    private void setPlayers(int playerAmount){

        players = new PlayerView[playerAmount];



        for(int i = 0; i < players.length; i++){

            // Calculates placement of players according to an Ellipse function
            int posX = (int)(centerX + centerX*1.5 / 2 * Math.cos((double)i/(double)players.length * 2.0 * Math.PI + Math.PI / players.length));
            int posY = (int)(centerY + centerY*1.5 / 2 * Math.sin((double)i/(double)players.length * 2.0 * Math.PI + Math.PI / players.length));

            players[i] = new PlayerView(getApplicationContext(), posX, posY, ""+(i+1));
            frame.addView(players[i]);

        }

    }

    @Override
    protected void onPause() {



        super.onPause();
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

        players[playerWon].setWin(false);
        players[playerWon].invalidate();

        bottleAnim.start();

        bottleAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                choosePlayerWin();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    private void choosePlayerWin(){

        double bottleAngle = Math.toRadians(bottleView.getRotation() % 360f);

        playerWon = (int)((bottleAngle/(2 * Math.PI / players.length)));

        System.out.println("Vinder " + playerWon);
        players[playerWon].setWin(true);
        players[playerWon].invalidate();

    }


}
