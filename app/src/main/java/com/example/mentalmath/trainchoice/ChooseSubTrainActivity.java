package com.example.mentalmath.trainchoice;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.example.mentalmath.core.Constants;

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

        int type = getIntent().getIntExtra(Constants.KEY_KIND_OF_TRAININGS, -1);

        mSubTrainFragment = (ChooseSubTrainFragment) ChooseSubTrainFragmentFactory.getFragmentByType(this, type);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            transaction.add(android.R.id.content, mSubTrainFragment).commit();
        } else {
            transaction.replace(android.R.id.content, mSubTrainFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public void launchTrain(View v) {
        mSubTrainFragment.launchTrain(v);
    }

}
