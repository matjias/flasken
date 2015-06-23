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
        setDrinksAmount(player.getNumberOfDrinks());

        estimatedBAC = (TextView) getView().findViewById(R.id.estimatedBACField);
        setEstimatedBAC(player.getBAC());

        estimatedSoberTime = (TextView) getView().findViewById(R.id.estimatedSoberTimeField);
        setEstimatedSoberTime(player.timeTillSober());
    }

    public void setDrinksAmount(int number) {
        drinksAmount.setText("" + number);
    }

    public void setEstimatedBAC(double number) {
        // Rounds the number to 2 decimal places
        Double buf = (double) Math.round(number * 1000) / 1000;
        estimatedBAC.setText("" + buf);
    }

    public void setEstimatedSoberTime(double number) {
        // Should display in the variant X days X hours X minutes X seconds
        // or maybe just Xd Xh Xm Xs
        String display = DateFormat.getDateTimeInstance().format(new Date());

        Double buf = (double) Math.round(number * 1000) / 1000;
        estimatedSoberTime.setText("" + buf);
    }
}
