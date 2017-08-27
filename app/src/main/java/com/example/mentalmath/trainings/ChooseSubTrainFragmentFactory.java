package com.example.mentalmath.trainings;

import android.app.Fragment;
import android.content.Context;

import com.example.mentalmath.R;

/**
 * Created by Роман on 26.08.2017.
 */

public class ChooseSubTrainFragmentFactory {

    public final static int I_ARIPHMETICS     = 0;
    public final static int I_EQUATIONS       = 1;
    public final static int I_MATRIX          = 2;
    public final static int I_MULTI_EQATIONS  = 3;
    public final static int I_POLYNOMIALS     = 4;

    public final static String S_ARIPHMETICS     = "ariphmetics";
    public final static String S_EQUATIONS       = "eqations";
    public final static String S_MATRIX          = "matrix";
    public final static String S_MULTI_EQATIONS  = "multi equations";
    public final static String S_POLYNOMIALS     = "polinomials";

    public Fragment getTrainingFragment(Context ctxt, int type) {
        int rId = 0;
        switch (type) {
            case I_ARIPHMETICS:
                rId = R.layout.arithmetics_choice;
                break;
            case I_EQUATIONS:
                rId = R.layout.equtions_choice;
                break;
            case I_MATRIX:
                rId = R.layout.matrix_choice;
                break;
            case I_MULTI_EQATIONS:
                rId = R.layout.linear_equations_choice;
                break;
            case I_POLYNOMIALS:
                rId = R.layout.polinomials_choice;
                break;
        }
        return TraininigFragment.instantiate(ctxt, rId);
    }
}
