package com.example.mentalmath.trainings;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class TrainingActivity extends Activity {

    CommonTrainingFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mFragment = new CommonTrainingFragment();
        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            getFragmentManager().beginTransaction().add(android.R.id.content,
                    mFragment).commit();
        }
    }

    public void onClick(View v) {
        mFragment.onClick(v);
    }
}
