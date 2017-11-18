package com.example.mentalmath.trainchoice;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;

import com.example.mentalmath.R;
import com.example.mentalmath.core.Constants;

public class SetOptionActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        int type = getIntent().getIntExtra(Constants.KEY_KIND_OF_OPTIONS, -1);

        PreferenceFragment subTrainFragment = getSettingsFragment(type);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            transaction.add(android.R.id.content, subTrainFragment).commit();
        } else {
            transaction.replace(android.R.id.content, subTrainFragment).commit();
        }
    }

    public PreferenceFragment getSettingsFragment(int type) {
        switch (type) {

            case SetOrStartFragment.I_KIND_ARITH_ADDITION:
                return new SetOptionActivity.TuneAdditionFragment();
            case SetOrStartFragment.I_KIND_ARITH_SUBTRACTION:
                return new SetOptionActivity.TuneSubtractionFragment();
            case SetOrStartFragment.I_KIND_ARITH_MULTIPLICATION:
                return new SetOptionActivity.TuneMultiplicationFragment();
            case SetOrStartFragment.I_KIND_ARITH_DIVISION:
                return new SetOptionActivity.TuneDivisionFragment();

            default:
                //todo handle more smartly
                Log.e("TrainingFragmentFactory", "Unknown type of training fragment:" + type);
                return null;
        }
    }


    public static class TuneAdditionFragment extends PreferenceFragment  {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.addition_prefs);
        }
    }

    public static class TuneSubtractionFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.subtraction_prefs);
        }
    }
    
    public static class TuneMultiplicationFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.addition_prefs);
        }
    }

    public static class TuneDivisionFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.addition_prefs);
        }
    }

}
