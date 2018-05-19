package com.example.mentalmath.trainings;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class TrainingActivity extends Activity {

    CommonTrainingFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        FragmentManager fragmentManager = getFragmentManager();
        Fragment trainingFragment = fragmentManager.findFragmentByTag(CommonTrainingFragment.TAG);
        if (trainingFragment == null) {
            mFragment = new CommonTrainingFragment();
        } else {
            mFragment = (CommonTrainingFragment) trainingFragment;
        }
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            transaction.add(android.R.id.content, mFragment, CommonTrainingFragment.TAG).commit();
        }
    }

    public void onClick(View v) {
        mFragment.onClick(v);
    }
}
