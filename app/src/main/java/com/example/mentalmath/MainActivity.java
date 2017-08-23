package com.example.mentalmath;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

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
