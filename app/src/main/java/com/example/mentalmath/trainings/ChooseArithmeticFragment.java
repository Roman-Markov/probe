package com.example.mentalmath.trainings;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mentalmath.R;

/**
 * Created by Роман on 27.08.2017.
 */

public class ChooseArithmeticFragment extends ChooseSubTrainFragment {

    public static final String KEY_KIND_OF_ARITHMETIC = "arithmetic kind";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.arithmetics_choice, container, false);
    }

    public void launchTrain(View v) {
        int id = v.getId();
        Intent i = new Intent(getActivity(), ChooseArithmeticOperationActivity.class);
        i.putExtra(KEY_KIND_OF_ARITHMETIC, id);
        startActivity(i);
    }

}
