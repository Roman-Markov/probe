package com.example.mentalmath.trainings;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import static  com.example.mentalmath.trainings.ChooseArithmeticFragment.*;

import com.example.mentalmath.R;

/**
 * Created by Роман on 27.08.2017.
 * Shows kind of addition trainings.
 */

public class ChooseAditionSubTrainFragment extends ChooseSubTrainFragment {

    public static final String KEY_KIND_OF_ADDITION = "kind of addition";

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        return inflater.inflate(R.layout.addition_choice, container, false);
    }

    @Override
    public void launchTrain(View v) {
        int kind = 0;
        switch (v.getId()) {
            case R.id.n_plus_m:
                kind = ExampleBuilderFactory.N_PLUS_M;
                break;
            case R.id.nn_plus_m:
                kind = ExampleBuilderFactory.NN_PLUS_M;
                break;
            case R.id.nn_plus_mm:
                kind = ExampleBuilderFactory.NN_PLUS_MM;
                break;
            case R.id.nnn_plus_mm:
                kind = ExampleBuilderFactory.NNN_PLUS_MM;
                break;
            case R.id.nnn_plus_mmm:
                kind = ExampleBuilderFactory.NNN_PLUS_MMM;
                break;
            default:
                kind = ExampleBuilderFactory.N_PLUS_M;
                // todo: handle more smartly this case
                Log.e(getClass().getSimpleName(), "Unknown type of training fragment:");
        }

        Intent i = new Intent(getActivity(), TrainingActivity.class);
        i.putExtra(KEY_KIND_OF_ADDITION, kind);
        i.putExtra(KEY_KIND_OF_ARITHMETIC, ADDITION);
        startActivity(i);
    }
}
