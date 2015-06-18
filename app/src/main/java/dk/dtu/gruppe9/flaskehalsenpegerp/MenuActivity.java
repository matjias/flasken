package dk.dtu.gruppe9.flaskehalsenpegerp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MenuActivity extends Activity {

    Button decrease, increase, start;
    final int GET_PLAYERS = 1;
    TextView playerAmount ;
    int bufferAmount = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        playerAmount = (TextView)findViewById(R.id.playerAmount);

        decrease = (Button)findViewById(R.id.decrease);
        increase = (Button)findViewById(R.id.increase);
        start = (Button)findViewById(R.id.start);


        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseAmount();
            }
        });

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseAmount();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
    }

    public void decreaseAmount(){
        if (bufferAmount > 1){
            bufferAmount--;
        }
        playerAmount.setText("" + bufferAmount);
    }

    public void increaseAmount(){
        if (bufferAmount < Integer.MAX_VALUE){
            bufferAmount++;
        }
        playerAmount.setText("" + bufferAmount);
    }

    public void startGame(){

        Intent sendBack = new Intent();
        sendBack.putExtra("amount", bufferAmount);

        setResult(RESULT_OK, sendBack);
        finish();
    }

}
