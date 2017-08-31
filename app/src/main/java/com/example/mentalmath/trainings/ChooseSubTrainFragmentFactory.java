package com.example.mentalmath.trainings;

import android.content.Context;
import android.util.Log;

import com.example.mentalmath.Constants;

/**
 * Created by Роман on 26.08.2017.
 */

public class ChooseSubTrainFragmentFactory {

    public static ChooseSubTrainFragment getTrainingFragment(Context ctxt, int type) {
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

            case Constants.I_KIND_ARITH_ADDITION:
                return new SetAdditionTrainingFragment();
            case Constants.I_KIND_ARITH_SUBTRACTION:
                return new SetSubtractionTrainingFragment();
            case Constants.I_KIND_ARITH_MULTIPLICATION:
                return new SetMultiplicationTrainingFragment();
            case Constants.I_KIND_ARITH_DIVISION:
                return new SetDivisionTrainingFragment();
            case Constants.I_KIND_OPT_OR_START:
                return new SetOrStartFragment();

            default:
                Log.e("TrainingFragmentFactory", "Unlnown type of training fragment:" + type);
                return new ChooseArithmeticFragment();
        }
    }
}
