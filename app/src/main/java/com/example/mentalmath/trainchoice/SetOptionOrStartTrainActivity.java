package com.example.mentalmath.trainchoice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class SetOptionOrStartTrainActivity extends Activity {

    SetOrStartFragment mFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mFragment = new SetOrStartFragment();
        super.onCreate(savedInstanceState);
        if(getFragmentManager().findFragmentById(android.R.id.content) == null) {
            getFragmentManager().beginTransaction()
                    .add(android.R.id.content, mFragment)
                    .commit();
        }
    }

    //todo rename
    public void launchTrain(View v) {
        mFragment.launchTrain(v);
    }

    public void showOptions(View v) {
        mFragment.showOptions(v);
    }

}
