package com.example.mentalmath.trainings;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.mentalmath.MainFragment;

/**
 * Created by Роман on 16.08.2017.
 */

public class ChooseSubTrainActivity extends Activity {

    private ChooseSubTrainFragment mSubTrainFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        int type = getIntent().getIntExtra(MainFragment.KEY_KIND_OF_TRAININGS, -1);

        mSubTrainFragment = ChooseSubTrainFragmentFactory.getTrainingFragment(this, type);
        mSubTrainFragment = (ChooseSubTrainFragment) getFragmentManager().
                findFragmentById(android.R.id.content);

        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            getFragmentManager().beginTransaction().add(android.R.id.content, mSubTrainFragment).commit();
        }
    }

    public void launchTrain(View v) {
        mSubTrainFragment.launchTrain(v);
    }

}