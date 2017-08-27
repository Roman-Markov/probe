package com.example.mentalmath.trainings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mentalmath.R;

/**
 * Created by Роман on 27.08.2017.
 */

public class ChooseSubtractionSubTrainFragment extends ChooseSubTrainFragment {

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        return inflater.inflate(R.layout.subtraction_choice, container, false);
    }

    @Override
    public void launchTrain(View v) {

    }
}
