package com.example.mentalmath.trainchoice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mentalmath.core.Constants;
import com.example.mentalmath.R;

/**
 * Fragment which represents choice of kind of arithmetic operation. Launches
 * {@link ChooseSubTrainActivity} and passes it kind of subtraining.
 */

public class ChooseArithmeticFragment extends ChooseSubTrainFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.arithmetics_choice, container, false);
    }

    /**
     * Launches {@link ChooseSubTrainActivity} and passes it kind of subtraining.
     * @param v - example of View with R.id which is used for defining which kind of arithmetic
     *          operation user wants to train.
     */
    public void launchTrain(View v) {
        int kind = -1;
        switch (v.getId()) {
            case R.id.addition:
                kind = Constants.I_KIND_ARITH_ADDITION;
                break;
            case R.id.subtraction:
                kind = Constants.I_KIND_ARITH_SUBTRACTION;
                break;
            case R.id.multiplication:
                kind = Constants.I_KIND_ARITH_MULTIPLICATION;
                break;
            case R.id.division:
                kind = Constants.I_KIND_ARITH_DIVISION;
                break;
            default:
                // todo: handle more smartly this case
                kind = Constants.I_KIND_ARITHMETICS;
                Log.e(getClass().getSimpleName(), "Unknown type of training fragment:");
        }
        Intent i = new Intent(getActivity(), SetOptionOrStartTrainActivity.class);
        i.putExtra(Constants.KEY_KIND_OF_OPTIONS, kind);
        startActivity(i);
    }

}
