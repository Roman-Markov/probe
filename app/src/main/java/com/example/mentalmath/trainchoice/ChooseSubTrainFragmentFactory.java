package com.example.mentalmath.trainchoice;

import android.app.Fragment;
import android.content.Context;
import android.preference.PreferenceFragment;
import android.util.Log;

import com.example.mentalmath.core.Constants;

/**
 * Created by Роман on 26.08.2017.
 */

public class ChooseSubTrainFragmentFactory {

    public static Fragment getFragmentByType(Context ctxt, int type) {
        switch (type) {
            case Constants.I_KIND_ARITHMETICS:
                return new ChooseArithmeticFragment();
            case Constants.I_KIND_EQUATIONS:
                return new ChooseEquationsFragment();
            case Constants.I_KIND_MATRIX:
                return new ChooseMatrixFragment();
            case Constants.I_KIND_MULTI_EQUATIONS:
                return new ChooseLinearEquationsFragment();
            case Constants.I_KIND_POLYNOMIALS:
                return new ChoosePolinomialsFragment();


            default:
                Log.e("TrainingFragmentFactory", "Unknown type of training fragment:" + type);
                return new ChooseArithmeticFragment();
        }
    }
}
