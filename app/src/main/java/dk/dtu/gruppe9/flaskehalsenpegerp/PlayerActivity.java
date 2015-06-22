package dk.dtu.gruppe9.flaskehalsenpegerp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


public class PlayerActivity extends Activity {

    private static final String TAG = "PlayerActivity";
    private final int REQUEST_IMAGE_CAPTURE = 100;
    private final EditText nameEdit = (EditText) findViewById(R.id.nameEdit);
    private final ImageButton cameraButton = (ImageButton) findViewById(R.id.cameraButton);
    private final Button statisticsButton = (Button) findViewById(R.id.statistics_button);
    private final Button optionsButton = (Button) findViewById(R.id.options_button);
    private final Button continueButton = (Button) findViewById(R.id.continueGame);
    private final TabFragment tabFragment = (TabFragment) getFragmentManager().findFragmentById(R.id.tab_fragment);
    private final FrameLayout frame = (FrameLayout) findViewById(R.id.frame);

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

        // Set default nameBox text
        nameEdit.setText("Player Name");


        //TODO: Tilfoj funktion til at indsatte spillervardier



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

            public void onActivityResult(int requestCode, int resultCode, Intent data) {
                Log.i(TAG, getClass().getSimpleName() + ":entered onActivityResult()");
                if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    playerImage = (Bitmap) extras.get("data");
                }
            }
        });


        //Button til at valge statistics
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onStatisticsSelect();
            }
        });


        //Button til at valge options
        optionsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onOptionsSelect();
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueGame();
            }
        });
    }

    //Funktioner til at valge options
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

    //Funktion til at valge stats
    public void onStatisticsSelect() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStatsSelection()");
        //Checks that current menu is not the requested menu
        if (tabFragment.getIndex() != 0) {
            statisticsButton.setBackgroundColor(getResources().getColor(R.color.popup));
            optionsButton.setBackgroundColor(getResources().getColor(R.color.transparent));
            // Opens the requested menu
            tabFragment.showStats();
        }
    }

    public void continueGame() {
        // Finish up stuff here

        finish();
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
