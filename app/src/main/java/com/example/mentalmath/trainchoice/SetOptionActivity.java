package com.example.mentalmath.trainchoice;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.example.mentalmath.core.Constants;

public class SetOptionActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        int type = getIntent().getIntExtra(Constants.KEY_KIND_OF_OPTIONS, -1);

        PreferenceFragmentCompat mSubTrainFragment = (PreferenceFragmentCompat) ChooseSubTrainFragmentFactory
                .getSettingsFragment(this, type);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            transaction.add(android.R.id.content, mSubTrainFragment).commit();
        } else {
            transaction.replace(android.R.id.content, mSubTrainFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
