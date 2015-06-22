package dk.dtu.gruppe9.flaskehalsenpegerp;

import android.content.Intent;
import android.graphics.Bitmap;
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
    private ImageButton cameraButton;
    private Button statisticsButton, optionsButton, continueButton;
    private TabFragment tabFragment;
    private FrameLayout frame;
    private Bundle extras;
    private Player curPlayer;

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
        cameraButton = (ImageButton) findViewById(R.id.cameraButton);
        statisticsButton = (Button) findViewById(R.id.statistics_button);
        optionsButton = (Button) findViewById(R.id.options_button);
        continueButton = (Button) findViewById(R.id.continue_button);
        tabFragment = (TabFragment) getFragmentManager().findFragmentById(R.id.tab_fragment);
        frame = (FrameLayout) findViewById(R.id.frame);

        curPlayer = PlayerHandler.getPlayer(getIntent().getIntExtra("player",2));

        // Set default nameBox text
        nameEdit.setText(curPlayer.getName());


        //TODO: Tilf�j funktion til at inds�tte spillerv�rdier



        //Funktion til at gemme spillernavn
        nameEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView tv, int id, KeyEvent event) {
                if (id == EditorInfo.IME_ACTION_DONE) {
                    playerName = (String) tv.getText();
                    return true;
                }
                return false;
            }
        });


        //Funktion til at tage billede
        cameraButton.setOnClickListener(new View.OnClickListener() {
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

        tabFragment.showStats();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityResult()");
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            extras = data.getExtras();
            playerImage = (Bitmap) extras.get("data");
            System.out.println(playerImage);
            curPlayer.setImage(playerImage);
        }
    }

    //Funktioner til at v�lge options
    public void onOptionsSelect() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onOptionsSelection()");
        //Checks that current menu is not the requested menu
        if (tabFragment.getIndex() != 1) {
            optionsButton.setBackgroundColor(getResources().getColor(R.color.popup));
            statisticsButton.setBackgroundColor(getResources().getColor(R.color.transparent));

            // Opens the requested menu
            tabFragment.showOptions();
        }
    }

    //Funktion til at v�lge stats
    public void onStatssSelect() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStatsSelection()");
        //Checks that current menu is not the requested menu
        if (tabFragment.getIndex() != 0) {
            optionsButton.setBackgroundColor(getResources().getColor(R.color.transparent));
            statisticsButton.setBackgroundColor(getResources().getColor(R.color.popup));

            // Opens the requested menu
            tabFragment.showStats();
        }
    }

    public void onContinue() {
        // TODO: Finish everything here instead of onBackPressed()

        finish();
    }

    @Override
    public void onBackPressed() {

        Intent backIntent = new Intent();
        backIntent.putExtra("playerBack", curPlayer.getID());
        setResult(RESULT_OK, backIntent);
        finish();

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
