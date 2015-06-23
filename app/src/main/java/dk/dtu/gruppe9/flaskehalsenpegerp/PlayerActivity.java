package dk.dtu.gruppe9.flaskehalsenpegerp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import dk.dtu.gruppe9.flaskehalsenpegerp.views.PlayerHandler;


public class PlayerActivity extends FragmentActivity {

    private static final String TAG = "PlayerActivity";
    private final int REQUEST_IMAGE_CAPTURE = 100;
    private final int GET_INFO_PLAYER = 2;
    private EditText nameEdit;
    private ImageButton imageButton;
    private Button statisticsButton, optionsButton, continueButton;
    private FrameLayout tabFrame;
    private Bundle extras;
    private Player curPlayer;
    private int curPlayerID;
    private boolean statisticsSelected = true;

    //Fields to be returned
    private Bitmap playerImage;
    private String playerName;
    private int height;
    private int weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        nameEdit = (EditText) findViewById(R.id.nameEdit);
        imageButton = (ImageButton) findViewById(R.id.cameraButton);
        statisticsButton = (Button) findViewById(R.id.statistics_button);
        optionsButton = (Button) findViewById(R.id.options_button);
        continueButton = (Button) findViewById(R.id.continue_button);

        FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
        fragTrans.add(R.id.tab_frame, new FragmentStatistics()).commit();

        curPlayerID = getIntent().getIntExtra("player", 0);
        curPlayer = PlayerHandler.getPlayer(curPlayerID);

        // Set default nameBox text
        nameEdit.setText(curPlayer.getName());


        //TODO: Tilf�j funktion til at inds�tte spillerv�rdier



        //Funktion til at gemme spillernavn
        nameEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView tv, int id, KeyEvent event) {
                if (id == EditorInfo.IME_ACTION_DONE) {
                    playerName = (String) tv.getText();
                    if(!playerName.equals(""))    curPlayer.setName(playerName);
                    return true;
                }
                return false;
            }
        });

        // Sets the image button and rounds it
        updateImage();

        //Funktion til at tage billede
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, getClass().getSimpleName() + ":entered onClick()");
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Unable to take picture", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }


        });

        //Button til at v�lge statistics
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onStatssSelect();
            }
        });


        //Button til at v�lge options
        optionsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onOptionsSelect();
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onContinue();
            }
        });

        //tabFrame.showStats();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityResult()");
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            extras = data.getExtras();
            playerImage = (Bitmap) extras.get("data");
            curPlayer.setImage(playerImage);
            updateImage();
        }
    }

    //Funktioner til at v�lge options
    public void onOptionsSelect() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onOptionsSelection()");
        //Checks that current menu is not the requested menu
        if (statisticsSelected) {
            optionsButton.setBackgroundColor(getResources().getColor(R.color.popup));
            optionsButton.setTextColor(getResources().getColor(R.color.black));
            statisticsButton.setBackgroundColor(getResources().getColor(R.color.transparent));
            statisticsButton.setTextColor(getResources().getColor(R.color.white));

            // Opens the requested menu
            showFragment(false);

            statisticsSelected = false;
        }
    }

    //Funktion til at v�lge stats
    public void onStatssSelect() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStatsSelection()");
        //Checks that current menu is not the requested menu
        if (!statisticsSelected) {
            optionsButton.setBackgroundColor(getResources().getColor(R.color.transparent));
            optionsButton.setTextColor(getResources().getColor(R.color.white));
            statisticsButton.setBackgroundColor(getResources().getColor(R.color.popup));
            statisticsButton.setTextColor(getResources().getColor(R.color.black));

            // Opens the requested menu
            showFragment(true);

            statisticsSelected = true;
        }
    }

    private void showFragment(boolean selector) {
        if (selector) {
            // Show statistics
            FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
            fragTrans.replace(R.id.tab_frame, new FragmentStatistics()).commit();
        } else {
            // Show options
            FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
            fragTrans.replace(R.id.tab_frame, new FragmentOptions()).commit();
        }
    }

    public void onContinue() {
        Intent backIntent = new Intent();
        backIntent.putExtra("playerBack", curPlayer.getId());

        String newName = nameEdit.getText().toString();
        if (!curPlayer.getName().equals(newName))
            curPlayer.setName(newName);

        setResult(RESULT_OK, backIntent);
        finish();
    }

    public void updateImage() {
        if (curPlayer.getImage() != null) {
            imageButton.setBackground(new BitmapDrawable(getResources(), roundBitmap(curPlayer.getImage())));
        } else {
            imageButton.setBackground(new BitmapDrawable(getResources(),
                    roundBitmap(BitmapFactory.decodeResource(getResources(),
                            R.drawable.player_default_picture))));
        }
    }

    // Tyv stjoelet fra PlayerView
    public Bitmap roundBitmap(Bitmap inBitmap) {

        Bitmap bitmap = Bitmap.createScaledBitmap(inBitmap, 200, 200, false);

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);

        final Paint painter = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, painter);
        painter.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, painter);

        return output;
    }

    @Override
    public void onBackPressed() {

        onContinue();

        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }
}
