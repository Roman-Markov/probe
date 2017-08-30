package com.example.mentalmath.trainings;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.mentalmath.MainFragment;

/**
 * According to arguments passed in Intents currently may contain fragments: {@link
 * ChooseArithmeticFragment}, {@link ChooseArithmeticFragment}, {@link ChooseEquationsFragment},
 * {@link ChooseLinearEquationsFragment},{@link ChoosePolinomialsFragment}. These fragments
 * clarify the appropriate subtrain and launch activity for training itself or launch one more
 * activity for choice sub subtrain.
 */

public class ChooseSubTrainActivity extends Activity {

    private ChooseSubTrainFragment mSubTrainFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        int type = getIntent().getIntExtra(MainFragment.KEY_KIND_OF_TRAININGS, -1);

        mSubTrainFragment = ChooseSubTrainFragmentFactory.getTrainingFragment(this, type);

        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            getFragmentManager().beginTransaction().add(android.R.id.content, mSubTrainFragment).commit();
        }
    }

    public void launchTrain(View v) {
        mSubTrainFragment.launchTrain(v);
    }

}