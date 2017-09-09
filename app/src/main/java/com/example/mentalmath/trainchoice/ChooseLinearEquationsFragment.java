package com.example.mentalmath.trainchoice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mentalmath.R;

/**
 * Created by Роман on 27.08.2017.
 * Fragment which represents choice of kind of linear equations.
 */

public class ChooseLinearEquationsFragment extends ChooseSubTrainFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.linear_equations_choice, container, false);
        return v;
    }

    public void launchTrain(View v) {

    }
}
