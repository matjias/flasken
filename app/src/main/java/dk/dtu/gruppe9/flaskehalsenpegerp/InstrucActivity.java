package dk.dtu.gruppe9.flaskehalsenpegerp;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;


public class InstrucActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruc);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        onBackPressed();
        return false;

    }


}
