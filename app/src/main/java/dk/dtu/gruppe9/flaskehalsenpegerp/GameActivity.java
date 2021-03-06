package dk.dtu.gruppe9.flaskehalsenpegerp;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.Random;

import dk.dtu.gruppe9.flaskehalsenpegerp.views.BottleView;
import dk.dtu.gruppe9.flaskehalsenpegerp.views.PlayerHandler;
import dk.dtu.gruppe9.flaskehalsenpegerp.views.PlayerView;


public class GameActivity extends Activity{

    FrameLayout frame;
    BottleView bottleView;
    Button settingButton, helpButton;
    int centerX, centerY, playerWon, previousPlayerAmount;
    final int GET_PLAYERS = 1;
    final int GET_INFO_PLAYER = 2;
    Random rand = new Random();
    final int ROTATE_RATE_DELAY = 40;
    PlayerView[] playerViews;
    Player[] playerObjects;
    ObjectAnimator bottleAnim;
    Bitmap playerImage;

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

        bottleAnim = ObjectAnimator.ofFloat(bottleView, "rotation", 0f, 0f);

        openMenu();

    }

    private void openMenu(){

        Intent menuIntent = new Intent(GameActivity.this, MenuActivity.class);
        startActivityForResult(menuIntent, GET_PLAYERS);

    }

    private void openInstructions(){

        Intent instrucIntent = new Intent(GameActivity.this, InstrucActivity.class);
        startActivity(instrucIntent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == GET_PLAYERS){
                int amount = data.getIntExtra("amount", 4);
                setPlayers(amount);
                if(amount == 1) Toast.makeText(getApplicationContext(), "GO' STIL!", Toast.LENGTH_LONG).show();
            }
            if(requestCode == GET_INFO_PLAYER){
                int tempID = data.getIntExtra("playerBack", 0);
                playerViews[tempID].setCustomImage(PlayerHandler.getPlayer(tempID).getImage(), true);
                playerViews[tempID].setName(PlayerHandler.getPlayer(tempID).getName());
                playerViews[tempID].invalidate();
            }
        }
    }

    private void setPlayers(int playerAmount){
        if(playerViews != null){
            if(playerViews.length == playerAmount) {
                /*
                for (int i = 0; i < playerViews.length; i++) {
                    frame.removeView(playerViews[i]);
                }
                */
                for (int i = 0; i < playerViews.length; i++) {
                    frame.addView(playerViews[i]);
                }
                return;
            }
        }

        playerObjects = new Player[playerAmount];
        playerViews = new PlayerView[playerAmount];

        for(int i = 0; i < playerViews.length; i++){

            // Calculates placement of playerViews according to an Ellipse function
            int posX = (int)(centerX + centerX*1.5 / 2 * Math.cos((double)i/(double)playerViews.length * 2.0 * Math.PI + Math.PI / playerViews.length));
            int posY = (int)(centerY + centerX*1.5 / 2 * Math.sin((double)i/(double)playerViews.length * 2.0 * Math.PI + Math.PI / playerViews.length));

            playerObjects[i] = new Player(i);
            playerViews[i] = new PlayerView(getApplicationContext(), posX, posY, ""+(i+1));


            frame.addView(playerViews[i]);

            //Bitmap bid = BitmapFactory.decodeResource(getResources(), R.drawable.bott);
            //playerViews[i].setCustomImage(bid, true);

        }
        PlayerHandler.setPlayers(playerObjects);

        gestures();
    }

    @Override
    protected void onPause() {



        super.onPause();
    }

    private void gestures() {

        /*

        bottleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("clicked " + " GAME");
                System.out.println(v.toString());

                return false;
            }
        });



        for(PlayerView pp : playerViews){
            pp.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    System.out.println("clicked " + " GAME");
                    System.out.println(v.toString());

                    return false;
                }
            });
        }


        */
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {

                if(!bottleAnim.isRunning()){
                    for(int i = 0; i < playerViews.length; i++){
                        if(playerViews[i].intersects(e.getX(), e.getY())){

                            Intent playerIntent = new Intent(GameActivity.this, PlayerActivity.class);
                            playerIntent.putExtra("player", i);
                            startActivityForResult(playerIntent, GET_INFO_PLAYER);

                            return true;
                        }
                    }

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


        settingButton = (Button) findViewById(R.id.settingButton);

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < playerViews.length; i++) {
                    frame.removeView(playerViews[i]);
                }
                openMenu();

            }
        });

        helpButton = (Button) findViewById(R.id.helpButton);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstructions();
            }
        });

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        gestureDetector.onTouchEvent(event);

        return false;

    }

    private void startRotation(float rotEnd){

        float rotStart = bottleView.getRotation() % 360f;

        bottleAnim = ObjectAnimator.ofFloat(bottleView, "rotation", rotStart, rotEnd);

        bottleAnim.setDuration(2000);
        bottleAnim.setInterpolator(new DecelerateInterpolator());

        bottleAnim.start();

        bottleAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                clearPlayerWin();
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

        playerWon = (int)((bottleAngle/(2 * Math.PI / playerViews.length)));

        System.out.println("Vinder " + playerWon);
        playerViews[playerWon].setWin(true);
        PlayerHandler.getPlayer(playerWon).playerDrink();
        playerViews[playerWon].invalidate();

    }

    private void clearPlayerWin() {
        // Just fixes the color problem
        // UPDATE: Or not.. whatever just don't
        // continuously press the screen.
        for (PlayerView player : playerViews) {
            player.setWin(false);
            player.invalidate();
        }
    }


}
