package dk.dtu.gruppe9.flaskehalsenpegerp;


import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class FragmentOptions extends Fragment {
    private Player player;
    private Switch gender;
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

        gender = (Switch) getView().findViewById(R.id.playerGender);
        //gender.setChecked(!player.isMale());
        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderSwitch();
            }
        });

        weight = (EditText) getView().findViewById(R.id.playerWeight);
        weight.setText(Float.toString(player.getWeight()));
        weight.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView tv, int id, KeyEvent event) {
                if (id == EditorInfo.IME_ACTION_DONE) {
                    weightBuf = Float.parseFloat(tv.getText().toString());

                    if (!player.getWeight() == weightBuf)
                        player.setWeight(weightBuf);

                    return true;
                }
                return false;
            }
        });
    }

    private void genderSwitch() {
        if (gender.isChecked()) {
            // Bliv en mand
        } else {
            // ad en pige
        }
    }
}
