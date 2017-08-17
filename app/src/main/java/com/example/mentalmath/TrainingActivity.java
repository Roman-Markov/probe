package com.example.mentalmath;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.widget.TextView;

/**
 * Created by Роман on 16.08.2017.
 */

public class TrainingActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            getFragmentManager().beginTransaction().add(android.R.id.content,
                    new TrainingFragment()).commit();
        }
    }
}
