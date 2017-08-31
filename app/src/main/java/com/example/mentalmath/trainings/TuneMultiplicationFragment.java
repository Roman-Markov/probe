package com.example.mentalmath.trainings;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mentalmath.Constants;
import com.example.mentalmath.R;

/**
 * Created by Роман on 27.08.2017.
 */

public class TuneMultiplicationFragment extends ChooseSubTrainFragment {

    public static final String KEY_KIND_OF_MULTIPLICATION = "kind of multiplication";

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        return inflater.inflate(R.layout.multiplication_option, container, false);
    }

    @Override
    public void launchTrain(View v) {
        int kind = 0;
        switch (v.getId()) {
            case R.id.NxM:
                kind = ExampleBuilderFactory.N_MULT_M;
                break;
            case R.id.NNxM:
                kind = ExampleBuilderFactory.NN_MULT_M;
                break;
            case R.id.NNxMM:
                kind = ExampleBuilderFactory.NN_MULT_MM;
                break;
            case R.id.NNNxM:
                kind = ExampleBuilderFactory.NNN_MULT_M;
                break;
            case R.id.NNNxMM:
                kind = ExampleBuilderFactory.NNN_MULT_MM;
                break;
            case R.id.NNNxMMM:
                kind = ExampleBuilderFactory.NNN_MULT_MMM;
                break;
            default:
                kind = ExampleBuilderFactory.N_MULT_M;
                // todo: handle more smartly this case
                Log.e(getClass().getSimpleName(), "Unknown type of training fragment:");
        }

        Intent i = new Intent(getActivity(), TrainingActivity.class);
        i.putExtra(Constants.KEY_KIND_OF_TRAININGS, kind);
        startActivity(i);
    }
}
