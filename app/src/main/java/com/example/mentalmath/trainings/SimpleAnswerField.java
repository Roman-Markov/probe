package com.example.mentalmath.trainings;

import android.widget.LinearLayout;

/**
 * Created by Роман on 27.09.2017.
 */

public class SimpleAnswerField implements IAnswerField {

    LinearLayout mLayout;

    public SimpleAnswerField(LinearLayout layout) {
        mLayout = layout;
    }

    @Override
    public void prepareField() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void resetField() {

    }

    @Override
    public String getAnswer() {
        return null;
    }

    @Override
    public void clean() {

    }

    @Override
    public void showRightResult(String answer) {

    }
}
