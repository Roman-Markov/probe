package com.example.mentalmath.trainings;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mentalmath.R;

/**
 * Fragment which represents choice of kind of arithmetic operation. Launches
 * {@link ChooseArithmeticOperationActivity} and passes it kind of subtraining.
 */

public class ChooseArithmeticFragment extends ChooseSubTrainFragment {

    public static final String KEY_KIND_OF_ARITHMETIC = "arithmetic kind";

    public static final int ADDITION        = 1;
    public static final int SUBTRACTION     = 2;
    public static final int MULTIPLICATION  = 3;
    public static final int DIVISION        = 4;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.arithmetics_choice, container, false);
    }

    /**
     * Launches {@link ChooseArithmeticOperationActivity} and passes it kind of subtraining.
     * @param v - example of View with R.id which is used for defining which kind of arithmetic
     *          operation user wants to train.
     */
    public void launchTrain(View v) {
        int kind = -1;
        switch (v.getId()) {
            case R.id.addition:
                kind = ChooseSubTrainFragmentFactory.I_ARITH_ADDITION;
                break;
            case R.id.subtraction:
                kind = ChooseSubTrainFragmentFactory.I_ARITH_SUBTRACTION;
                break;
            case R.id.multiplication:
                kind = ChooseSubTrainFragmentFactory.I_ARITH_MULTIPLICATION;
                break;
            case R.id.division:
                kind = ChooseSubTrainFragmentFactory.I_ARITH_DIVISION;
                break;
            default:
                // todo: handle more smartly this case
                kind = ChooseSubTrainFragmentFactory.I_ARITHMETICS;
                Log.e(getClass().getSimpleName(), "Unknown type of training fragment:");
        }
        Intent i = new Intent(getActivity(), ChooseArithmeticOperationActivity.class);
        i.putExtra(KEY_KIND_OF_ARITHMETIC, kind);
        startActivity(i);
    }

}