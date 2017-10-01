package com.example.mentalmath.trainings;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.mentalmath.R;

/**
 * Created by Роман on 27.09.2017.
 */

public class SimpleAnswerField extends ABaseField implements IAnswerField {

    EditText mAnswerField;

    public SimpleAnswerField (LayoutInflater inflater, ViewGroup container) {

        super(inflater, container, R.layout.answer_field);
        mAnswerField = mLayout.findViewById(R.id.answer_field);
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
    public void showRightResult(String correctAnswer) {

        String userAnswer = mAnswerField.getText().toString();
        if (userAnswer != null) {
            if (userAnswer.equals(correctAnswer)) {
                mAnswerField.setText(userAnswer + '\n' + correctAnswer);
            } else {
                // todo add pattern to string resource
                userAnswer = "<b>" + userAnswer + "<\b>";
                mAnswerField.setText(userAnswer + '\n' + correctAnswer);
            }
        }
    }
}
