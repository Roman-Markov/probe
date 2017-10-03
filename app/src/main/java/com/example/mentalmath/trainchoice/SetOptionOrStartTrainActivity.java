package com.example.mentalmath.trainchoice;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class SetOptionOrStartTrainActivity extends Activity {

    private final String KEY_FRAGMENT = "fragment";
    SetOrStartFragment mFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mFragment = (SetOrStartFragment) getFragmentManager().getFragment(savedInstanceState, KEY_FRAGMENT);
        }
        if (mFragment == null) {
            mFragment = new SetOrStartFragment();

            if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
                getFragmentManager().beginTransaction()
                        .add(android.R.id.content, mFragment)
                        .commit();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        getFragmentManager().putFragment(outState, KEY_FRAGMENT, mFragment);
        super.onSaveInstanceState(outState);
    }

    //todo rename
    public void launchTrain(View v) {
        mFragment.launchTrain(v);
    }

    public void showOptions(View v) {
        mFragment.showOptions(v);
    }

}
