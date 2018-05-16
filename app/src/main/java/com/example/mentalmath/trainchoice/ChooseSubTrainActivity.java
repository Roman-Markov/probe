package com.example.mentalmath.trainchoice;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mentalmath.core.Constants;

/**
 * According to arguments passed in Intents currently may contain fragments: {@link
 * ChooseArithmeticFragment}. These fragments
 * clarify the appropriate subtrain and launch activity for training itself or launch one more
 * activity for choice sub subtrain.
 */

public class ChooseSubTrainActivity extends Activity {

    private ChooseSubTrainFragment mSubTrainFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        int type = getIntent().getIntExtra(Constants.KEY_KIND_OF_TRAININGS, -1);

        mSubTrainFragment = (ChooseSubTrainFragment) getFragmentByType(type);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            transaction.add(android.R.id.content, mSubTrainFragment).commit();
        } else {
            transaction.replace(android.R.id.content, mSubTrainFragment);
            transaction.commit();
        }
    }

    public static Fragment getFragmentByType(int type) {
        switch (type) {
            case Constants.I_KIND_ARITHMETIC:
                return new ChooseArithmeticFragment();
//            case Constants.I_KIND_EQUATIONS:
//                return new ChooseEquationsFragment();
//            case Constants.I_KIND_MATRIX:
//                return new ChooseMatrixFragment();
//            case Constants.I_KIND_MULTI_EQUATIONS:
//                return new ChooseLinearEquationsFragment();
//            case Constants.I_KIND_POLYNOMIALS:
//                return new ChoosePolinomialsFragment();

            default:
                Log.e("TrainingFragmentFactory", "Unknown type of training fragment:" + type);
                return new ChooseArithmeticFragment();
        }
    }

}
