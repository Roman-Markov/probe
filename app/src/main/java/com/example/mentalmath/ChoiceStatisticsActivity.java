package com.example.mentalmath;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class ChoiceStatisticsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            getFragmentManager().beginTransaction().add(android.R.id.content,
                    new ChoiceStatisticsFragment()).commit();
        }
    }

    public void launchStatistics(View v) {
        ((ChoiceStatisticsFragment) getFragmentManager().findFragmentById(android.R.id.content)).showStatistics(v);
    }
}
