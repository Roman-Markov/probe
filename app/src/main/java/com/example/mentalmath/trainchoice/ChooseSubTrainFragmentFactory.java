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

    public static PreferenceFragment getSettingsFragment(Context ctxt, int type) {
        switch (type) {

            case Constants.I_KIND_ARITH_ADDITION:
                return new SetOptionActivity.TuneAdditionFragment();
            case Constants.I_KIND_ARITH_SUBTRACTION:
                return new SetOptionActivity.TuneSubtractionFragment();
            case Constants.I_KIND_ARITH_MULTIPLICATION:
                return new SetOptionActivity.TuneMultiplicationFragment();
            case Constants.I_KIND_ARITH_DIVISION:
                return new SetOptionActivity.TuneDivisionFragment();

            default:
                //todo handle more smartly
                Log.e("TrainingFragmentFactory", "Unknown type of training fragment:" + type);
                return null;
        }
    }


}
