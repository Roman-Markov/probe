package com.example.mentalmath.trainchoice;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.mentalmath.R;
import com.example.mentalmath.core.Constants;

public class SetOptionActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        int type = getIntent().getIntExtra(Constants.KEY_KIND_OF_OPTIONS, -1);

        PreferenceFragment subTrainFragment = ChooseSubTrainFragmentFactory
                .getSettingsFragment(this, type);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            transaction.add(android.R.id.content, subTrainFragment).commit();
        } else {
            transaction.replace(android.R.id.content, subTrainFragment).commit();
        }
    }

    /**
     * Created by Роман on 27.08.2017.
     * Shows kind of addition trainings.
     */
    public static class TuneAdditionFragment extends PreferenceFragment  {
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

    public static class TuneMultiplicationFragment extends PreferenceFragment {
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
            addPreferencesFromResource(R.xml.addition_prefs);
        }
    }
}
