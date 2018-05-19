package com.example.mentalmath.trainchoice;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.example.mentalmath.R;

import java.util.List;

public class SetOptionOrStartTrainActivity extends Activity {

    SetOrStartFragment mFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initDefaultsSettings();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        mFragment = new SetOrStartFragment();
        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            transaction.add(android.R.id.content, mFragment).commit();
        } else {
            transaction.replace(android.R.id.content, mFragment).commit();
        }
    }

    private void initDefaultsSettings() {
        PreferenceManager.setDefaultValues(this, R.xml.division_prefs, true);
        PreferenceManager.setDefaultValues(this, R.xml.addition_prefs, true);
        PreferenceManager.setDefaultValues(this, R.xml.subtraction_prefs, true);
        PreferenceManager.setDefaultValues(this, R.xml.multiplication_prefs, true);
    }

    //todo rename
    public void launchTrain(View v) {
        mFragment.launchTrain(v);
    }

    public void showOptions(View v) {
        mFragment.showOptions(v);
    }
}
