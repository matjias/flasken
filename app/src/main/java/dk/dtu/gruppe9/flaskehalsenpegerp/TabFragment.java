package dk.dtu.gruppe9.flaskehalsenpegerp;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

//Kode til logging er genbrugt fra Coursera-kurset

public class TabFragment extends Fragment {

    private static final String TAG = "TabFragment";
    private int index = -1;
    private final int STATS_INDEX = 0;
    private final int OPTIONS_INDEX = 1;


    private TextView drinksAmountField;
    private TextView drinksPercentageField;
    private TextView estimatedBACField;
    private TextView estimatedTimeUntilSober;

    private Spinner drinkTypeSpinner;
    private EditText weightField;


    public TabFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();

        drinksAmountField = (TextView) getView().findViewById(R.id.drinksAmountField);
        drinksPercentageField = (TextView) getView().findViewById(R.id.drinksPercentageField);
        estimatedBACField = (TextView) getView().findViewById(R.id.estimatedBACField);
        estimatedTimeUntilSober = (TextView) getView().findViewById(R.id.estimatedSoberTimeField);

        //drinkTypeSpinner = (Spinner) getView().findViewById(R.id.drinkTypeSpinner);
        //weightField = (EditText) getView().findViewById(R.id.weightField);

        System.out.println("drinksAmountField: " + drinksAmountField);
        System.out.println("drinksPercentageField: " + drinksPercentageField);
        System.out.println("estimatedBACField: " + estimatedBACField);
        System.out.println("estimatedTimeUntilSober: " + estimatedTimeUntilSober);

        System.out.println("drinkTypeSpinner: " + drinkTypeSpinner);
        System.out.println("weightField: " + weightField);
    }

    @Override
    public void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();

        showStats();
    }

    @Override
    public void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDetach()");
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroyView()");
        super.onDestroyView();
    }

    public int getIndex() {
        return index;
    }

    //TODO: Optimer ueffektiv kode
    public void showOptions() {
        Log.i(TAG, getClass().getSimpleName() + ":entered showOptions()");
        index = OPTIONS_INDEX;

        switchFocusable();


    }


    public void showStats() {
        Log.i(TAG, getClass().getSimpleName() + ":entered showStats()");
        index = STATS_INDEX;

        switchFocusable();


    }

    private void switchFocusable() {
        boolean switchBool = index == STATS_INDEX;

        System.out.println(drinkTypeSpinner);
        if (drinkTypeSpinner != null)
            drinkTypeSpinner.setFocusable(!switchBool);
        if (weightField != null)
            weightField.setFocusable(!switchBool);

        if (drinksAmountField != null)
            drinksAmountField.setFocusable(switchBool);
        if (drinksPercentageField != null)
            drinksPercentageField.setFocusable(switchBool);
        if (estimatedBACField != null)
            estimatedBACField.setFocusable(switchBool);
        if (estimatedTimeUntilSober != null)
            estimatedTimeUntilSober.setFocusable(switchBool);
    }
}
