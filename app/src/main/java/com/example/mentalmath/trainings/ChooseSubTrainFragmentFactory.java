package com.example.mentalmath.trainings;

import android.content.Context;
import android.util.Log;

/**
 * Created by Роман on 26.08.2017.
 */

public class ChooseSubTrainFragmentFactory {

    public final static int I_ARITHMETICS           = 0;
    public final static int I_ARITH_ADDITION        = 1;
    public final static int I_ARITH_SUBTRACTION     = 2;
    public final static int I_ARITH_MULTIPLICATION  = 3;
    public final static int I_ARITH_DIVISION        = 4;

    public final static int I_EQUATIONS       = 100;
    public final static int I_MATRIX          = 200;
    public final static int I_MULTI_EQATIONS  = 300;
    public final static int I_POLYNOMIALS     = 400;

    public final static String S_ARIPHMETICS     = "ariphmetics";
    public final static String S_EQUATIONS       = "eqations";
    public final static String S_MATRIX          = "matrix";
    public final static String S_MULTI_EQATIONS  = "multi equations";
    public final static String S_POLYNOMIALS     = "polynomials";

    public static ChooseSubTrainFragment getTrainingFragment(Context ctxt, int type) {
        switch (type) {
            case I_ARITHMETICS:
                return new ChooseArithmeticFragment();
            case I_EQUATIONS:
                return new ChooseEquationsFragment();
            case I_MATRIX:
                return new ChooseMatrixFragment();
            case I_MULTI_EQATIONS:
                return new ChooseLinearEquationsFragment();
            case I_POLYNOMIALS:
                return new ChoosePolinomialsFragment();

            case I_ARITH_ADDITION:
                return new ChooseAditionSubTrainFragment();
            case I_ARITH_SUBTRACTION:
                return new ChooseSubtractionSubTrainFragment();
            case I_ARITH_MULTIPLICATION:
                return new ChooseMultiplicationSubTrainFragment();
            case I_ARITH_DIVISION:
                return new ChooseDivisionSubTrainFragment();

            default:
                Log.e("TrainingFragmentFactory", "Unlnown type of training fragment:" + type);
                return new ChooseArithmeticFragment();
        }
    }
}
