package com.example.mentalmath.trainings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mentalmath.R;

/**
 * Created by Роман on 27.08.2017.
 */

public class ChooseMatrixFragment extends ChooseSubTrainFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.matrix_choice, container, false);
        return v;
    }

    public void launchTrain(View v) {

    }
}
