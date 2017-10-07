package com.example.mentalmath.trainings;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.mentalmath.R;
import com.example.mentalmath.core.Helper;

import android.text.Editable;

/**
 * Created by Роман on 27.09.2017.
 */

public class SimpleAnswerField extends ABaseField implements IAnswerField {

    EditText mAnswerField;
    EditText mRightAnswerField;
    boolean mIsHonestMode;

    public SimpleAnswerField (LayoutInflater inflater, ViewGroup container, boolean isHonestMode) {

        super(inflater, container, R.layout.answer_field);
        mAnswerField = mLayout.findViewById(R.id.answer_field);
        mRightAnswerField = mLayout.findViewById(R.id.right_answer_field);
        mRightAnswerField.setVisibility(View.GONE);

        mIsHonestMode = isHonestMode;
        if (mIsHonestMode) {
            mAnswerField.setVisibility(View.GONE);
        }
    }

    @Override
    public void prepareField() {
        if(!mIsHonestMode) {
            mAnswerField.setVisibility(View.VISIBLE);
            mAnswerField.setText("");
            mRightAnswerField.setText("");
            mRightAnswerField.setVisibility(View.GONE);
        } else {
            mAnswerField.setText("");
            mAnswerField.setEnabled(false);
        }
    }

    @Override
    public void pause() {
        mAnswerField.setEnabled(false);
    }

    @Override
    public void resume() {
        if(!mIsHonestMode) {
            mAnswerField.setEnabled(true);
        }
    }

    @Override
    public String getAnswer() {
        if(!mIsHonestMode) {
            return mAnswerField.getText().toString();
        } else return "";
    }

    @Override
    public void clean() {

        if(!mIsHonestMode) {
            mAnswerField.setText("");
            mRightAnswerField.setText("");
            mRightAnswerField.setVisibility(View.GONE);
        } else {
            mAnswerField.setText("");
            mAnswerField.setVisibility(View.GONE);
        }
    }

    @Override
    public void showRightResult(String correctAnswer) {
        CharSequence userAnswer = mAnswerField.getText().toString();
        if (userAnswer == null) {
            userAnswer = "";
        }
        if (!mIsHonestMode) {
            showUserAndRightResult(correctAnswer, userAnswer);
        } else {
            showOnlyRightResult(correctAnswer);
        }
    }

    private void showOnlyRightResult(String correctAnswer) {
        mAnswerField.setVisibility(View.VISIBLE);
        mAnswerField.setText(String.format(mLayout.getContext()
                .getString(R.string.right_answer_format), correctAnswer));
        mAnswerField.setEnabled(false);
    }

    private void showUserAndRightResult(String correctAnswer, CharSequence userAnswer) {
        if (! userAnswer.equals(correctAnswer)) {
            userAnswer = Helper.paintString(userAnswer, R.color.red, mLayout);
        }
        if (userAnswer instanceof SpannableStringBuilder) {
            String begin = mLayout.getContext().getString(R.string.your);
            userAnswer = new SpannableStringBuilder().append(begin).append(userAnswer);
        } else {
            String begin = mLayout.getContext().getString(R.string.your);
            userAnswer = begin + userAnswer;
        }
        mAnswerField.setText(userAnswer);

        String rightAnswer = String.format(mLayout.getContext().getString(R.string.right_answer_format), correctAnswer);
        mRightAnswerField.setVisibility(View.VISIBLE);
        mRightAnswerField.setText(rightAnswer);
    }

}
