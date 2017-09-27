package com.example.mentalmath.trainings;

import android.widget.LinearLayout;

/**
 * Created by Роман on 27.09.2017.
 */

public class SimpleSessionResultField implements ISessionResultField {

    LinearLayout mLayout;

    public SimpleSessionResultField(LinearLayout layout) {
        mLayout = layout;
    }

    @Override
    public void addExampleResult() {

    }

    @Override
    public void addCommonResult(String commonResult) {

    }

    @Override
    public void reset() {

    }
}
