package dk.dtu.gruppe9.flaskehalsenpegerp;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import dk.dtu.gruppe9.flaskehalsenpegerp.views.PlayerHandler;

public class FragmentOptions extends Fragment {
    private Player player;
    private ToggleButton gender;
    private EditText weight;
    private float weightBuf;

    public FragmentOptions() {}

    public FragmentOptions setPlayer(Player player) {
        this.player = player;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_options, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        gender = (ToggleButton) getView().findViewById(R.id.playerGender);
        gender.setChecked(player.isMale());
        gender.setTextColor(player.isMale() ? Color.BLUE : Color.RED);
        gender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                player.setMale(gender.isChecked());

                gender.setTextColor(player.isMale() ? Color.BLUE : Color.RED);
            }
        });

        weight = (EditText) getView().findViewById(R.id.playerWeight);
        weight.setText(Float.toString(player.getWeight()));
        weight.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView tv, int id, KeyEvent event) {
                weightBuf = Float.parseFloat(tv.getText().toString());
                if (player.getWeight() != weightBuf)
                    player.setWeight(weightBuf);

                weight.setFocusable(false);

                return false;
            }
        });
        weight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                weight.setFocusableInTouchMode(true);
                return false;
            }
        });
    }

}
