package dk.dtu.gruppe9.flaskehalsenpegerp;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class FragmentStatistics extends Fragment {
    private Player player;
    private TextView drinksAmount, estimatedBAC, estimatedSoberTime;

    public FragmentStatistics() {}

    public FragmentStatistics setPlayer(Player player) {
        this.player = player;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        drinksAmount = (TextView) getView().findViewById(R.id.drinksAmountField);
        drinksAmount.setText(player.getNumberOfDrinks());

        estimatedBAC = (TextView) getView().findViewById(R.id.estimatedBACField);
        estimatedBAC.setText(Double.toString(player.getBAC()));

        estimatedSoberTime = (TextView) getView().findViewById(R.id.estimatedSoberTimeField);
        estimatedSoberTime.setText(Double.toString(player.timeTillSober()));

    }

    public void setDrinksAmount(int number) {
        drinksAmount.setText("" + number);
    }

    public void setEstimatedBAC(float number) {
        estimatedBAC.setText("" + number);
    }

    public void setEstimatedSoberTime(long number) {
        // Should display in the variant X days X hours X minutes X seconds
        // or maybe just Xd Xh Xm Xs
        String display = DateFormat.getDateTimeInstance().format(new Date());
        estimatedSoberTime.setText(display);
    }
}
