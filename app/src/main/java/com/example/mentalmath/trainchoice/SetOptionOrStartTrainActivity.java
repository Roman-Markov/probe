package com.example.mentalmath.trainchoice;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class SetOptionOrStartTrainActivity extends Activity {

    private final String KEY_FRAGMENT = "fragment";
    SetOrStartFragment mFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        mFragment = new SetOrStartFragment();
        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            transaction.add(android.R.id.content, mFragment).commit();
        } else {
            transaction.replace(android.R.id.content, mFragment).commit();
        }
    }

    //todo rename
    public void launchTrain(View v) {
        mFragment.launchTrain(v);
    }

    public void showOptions(View v) {
        mFragment.showOptions(v);
    }
}
