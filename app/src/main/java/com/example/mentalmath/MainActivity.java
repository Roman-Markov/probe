package com.example.mentalmath;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.mentalmath.trainings.ChooseSubTrainActivity;

/**
 * Base activity, represents fragment {@link MainFragment} with choice of types of trainings e. g.
 * arithmetic, equations, polynomials and  so on. After clicking appropriate button
 * it can start {@link ChooseSubTrainActivity} and pass it kind of training.
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            getFragmentManager().beginTransaction().add(android.R.id.content,
                    new MainFragment()).commit();
        }
    }

    public void launchTrain(View v) {
        ((MainFragment) getFragmentManager().findFragmentById(android.R.id.content)).launchTrain(v);
    }
}
