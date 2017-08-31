package com.example.mentalmath.trainings;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.example.mentalmath.Constants;

public class SetOptionActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        int type = getIntent().getIntExtra(Constants.KEY_KIND_OF_OPTIONS, -1);

        ChooseSubTrainFragment mSubTrainFragment = ChooseSubTrainFragmentFactory.getFragmentByType(this, type);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            transaction.add(android.R.id.content, mSubTrainFragment).commit();
        } else {
            transaction.replace(android.R.id.content, mSubTrainFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
