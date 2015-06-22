package dk.dtu.gruppe9.flaskehalsenpegerp;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
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
        return inflater.inflate(R.layout.tab_fragment, container, false);
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
    }

    @Override
    public void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
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
    public void showStats() {
        Log.i(TAG, getClass().getSimpleName() + ":entered showStats()");
        if (index==OPTIONS_INDEX){
            drinksAmountField = null;
            drinksPercentageField = null;
            estimatedBACField = null;
            estimatedTimeUntilSober = null;
        }
        drinkTypeSpinner = (Spinner) getView().findViewById(R.id.drinkTypeSpinner);
        weightField = (EditText) getView().findViewById(R.id.weightField);

        index = STATS_INDEX;
    }


    public void showOptions() {
        Log.i(TAG, getClass().getSimpleName() + ":entered showStats()");
        if (index==STATS_INDEX){
            drinkTypeSpinner = null;
            weightField = null;
        }
        drinksAmountField = (TextView) getView().findViewById(R.id.drinksAmountField);
        drinksPercentageField = (TextView) getView().findViewById(R.id.drinksPercentageField);
        estimatedBACField = (TextView) getView().findViewById(R.id.estimatedBACField);
        estimatedTimeUntilSober = (TextView) getView().findViewById(R.id.estimatedTimeUntilSoberField);

        index = OPTIONS_INDEX;
    }
}
