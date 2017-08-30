package com.example.mentalmath.trainings;

import android.app.Activity;
import android.os.Bundle;

public class TrainingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            getFragmentManager().beginTransaction().add(android.R.id.content,
                    new TrainingFragment()).commit();
        }
    }
}
