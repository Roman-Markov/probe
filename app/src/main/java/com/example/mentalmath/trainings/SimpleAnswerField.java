package com.example.mentalmath.trainings;

import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.mentalmath.R;

/**
 * Created by Роман on 27.09.2017.
 */

public class SimpleAnswerField implements IAnswerField {

    LinearLayout mLayout;
    EditText mAnswerField;

    public SimpleAnswerField(LinearLayout parentLayout) {

        mLayout = parentLayout.findViewById(R.layout.answer_field);
        mAnswerField = mLayout.findViewById(R.id.answer_field);
        parentLayout.addView(mLayout);
    }

    @Override
    public void prepareField() {
        mAnswerField = mLayout.findViewById(R.id.answer_field);
        mAnswerField.setText("");
    }

    @Override
    public void pause() {
        mAnswerField.setEnabled(false);
    }

    @Override
    public void resume() {
        mAnswerField.setEnabled(true);
    }

    @Override
    public String getAnswer() {

        return mAnswerField.getText().toString();
    }

    @Override
    public void clean() {
        mAnswerField.setText("");
    }

    @Override
    public void showRightResult(String answer) {
        mAnswerField.setText(answer);
    }
}
