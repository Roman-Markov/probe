package com.example.mentalmath.trainings;

import android.app.Activity;
import android.os.Bundle;

import com.example.mentalmath.MainFragment;

/**
 * Created by Роман on 16.08.2017.
 */

public class ChooseSubTrainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        int type = getIntent().getIntExtra(MainFragment.KEY_KIND_OF_TRAININGS, -1);

        TraininigFragment fragment = (TraininigFragment) TraininigFragment.instantiate(this, null,
                savedInstanceState);

        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            getFragmentManager().beginTransaction().add(android.R.id.content,
                    new TraininigFragment()).commit();
        }
    }
}
