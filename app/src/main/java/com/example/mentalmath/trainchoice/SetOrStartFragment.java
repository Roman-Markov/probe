package com.example.mentalmath.trainchoice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mentalmath.core.Constants;
import com.example.mentalmath.R;
import com.example.mentalmath.trainings.ExampleBuilderFactory;
import com.example.mentalmath.trainings.TrainingActivity;

/**
 * Created by Роман on 31.08.2017.
 */

public class SetOrStartFragment extends ChooseSubTrainFragment {

    private int mOptionKind;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOptionKind = getActivity().getIntent().getIntExtra(Constants.KEY_KIND_OF_OPTIONS, -1);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

        return inflater.inflate(R.layout.set_or_start, container, false);
    }

    @Override
    public void launchTrain(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.start:
                i = new Intent(getActivity(), TrainingActivity.class);
                //hardcoded yet
                i.putExtra(Constants.KEY_KIND_OF_TRAININGS, ExampleBuilderFactory.N_PLUS_M);
                break;
            default:
                Log.e(getClass().getSimpleName(), "Unknown id of button.");
                return;
        }
        startActivity(i);
    }

    public void showOptions(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.set_options:
                i = new Intent(getActivity(), SetOptionActivity.class);
                i.putExtra(Constants.KEY_KIND_OF_OPTIONS, mOptionKind);
                break;
            default:
                Log.e(getClass().getSimpleName(), "Unknown id of button.");
                return;
        }
        startActivity(i);
    }

}
