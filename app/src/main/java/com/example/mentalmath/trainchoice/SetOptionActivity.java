package com.example.mentalmath.trainchoice;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

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

            case Constants.I_KIND_ARITH_ADDITION:
                return new SetOptionActivity.TuneAdditionFragment();
            case Constants.I_KIND_ARITH_SUBTRACTION:
                return new SetOptionActivity.TuneSubtractionFragment();
            case Constants.I_KIND_ARITH_MULTIPLICATION:
                return new SetOptionActivity.TuneMultiplicationFragment();
            case Constants.I_KIND_ARITH_DIVISION:
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
            addPreferencesFromResource(R.xml.multiplication_prefs);
        }
    }

    public static class TuneDivisionFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // // TODO: 14.04.2018 replace with appropriate xml file
            addPreferencesFromResource(R.xml.division_prefs);

            Preference divisorPref = getPreferenceScreen().findPreference(getActivity().getString(R.string.divisorRangeListKey));
            divisorPref.setOnPreferenceChangeListener(this);


        }

        // Check that divisor less than or equals to dividend
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String dividendValue = PreferenceManager
                    .getDefaultSharedPreferences(getActivity())
                    .getString(getActivity().getString(R.string.dividendRangeListKey), "-1");
            if (dividendValue.equals("-1")) {
                Log.e("TuneDivisionFragment", "Dividend value is not specified");
                Toast.makeText(getActivity(), R.string.dividend_is_not_specified, Toast.LENGTH_SHORT).show();
                return false;
            }
            String[] dividendValues = getActivity().getResources().getStringArray(R.array.dividendId);
            int indexOfDividend = 0;
            while (indexOfDividend < dividendValues.length && !dividendValues[indexOfDividend].equals(dividendValue)) {
                indexOfDividend++;
            }
            String[] divisorValues = getActivity().getResources().getStringArray(R.array.divisorId);
            String divisorValue = (String) newValue;
            int indexOfDivisor = 0;
            while (indexOfDivisor < divisorValues.length && !divisorValues[indexOfDivisor].equals(divisorValue)) {
                indexOfDivisor++;
            }
            if (indexOfDividend < indexOfDivisor) {
                Toast.makeText(getActivity(), R.string.divisor_more_than_dividend, Toast.LENGTH_LONG).show();
                return false;
            }
            return true;
        }
    }

}
