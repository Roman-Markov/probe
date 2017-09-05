package com.example.mentalmath.trainings;

import android.app.Fragment;
import android.content.Context;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;

import com.example.mentalmath.Constants;

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

    public static PreferenceFragmentCompat getSettingsFragment(Context ctxt, int type) {
        switch (type) {

            case Constants.I_KIND_ARITH_ADDITION:
                return new TuneAdditionFragment();
            case Constants.I_KIND_ARITH_SUBTRACTION:
                return new TuneSubtractionFragment();
            case Constants.I_KIND_ARITH_MULTIPLICATION:
                return new TuneMultiplicationFragment();
            case Constants.I_KIND_ARITH_DIVISION:
                return new TuneDivisionFragment();

//            case Constants.I_ADDTION_OPTIONS:
//                return new TuneAdditionFragment();
//            case Constants.I_SUBTRACTION_OPTIONS:
//                return new TuneSubtractionFragment();
//            case Constants.I_MULTIPLICATION_OPTIONS:
//                return new TuneMultiplicationFragment();
//            case Constants.I_DIVISION_OPTIONS:
//                return new TuneDivisionFragment();

            default:
                //todo handle more smartly
                Log.e("TrainingFragmentFactory", "Unknown type of training fragment:" + type);
                return null;
        }
    }


}
