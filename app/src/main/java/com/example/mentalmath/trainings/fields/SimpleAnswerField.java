package com.example.mentalmath.trainings.fields;

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
import com.example.mentalmath.trainings.ABaseField;
import com.example.mentalmath.trainings.IAnswerField;

import android.text.Editable;
import android.widget.TextView;

/**
 * Created by Роман on 27.09.2017.
 */

public class SimpleAnswerField extends ABaseField implements IAnswerField {

    EditText mAnswerField;
    TextView mUserAnswerField;
    TextView mRightAnswerField;
    boolean mIsHonestMode;

    public SimpleAnswerField (LayoutInflater inflater, ViewGroup container, boolean isHonestMode) {
        super(inflater, container, R.layout.answer_field);
        init(isHonestMode);
    }

    public SimpleAnswerField (ViewGroup container, boolean isHonestMode) {
        super(container, R.id.answerField);
        init(isHonestMode);
    }

    private void init(boolean isHonestMode) {
        mAnswerField = mLayout.findViewById(R.id.answer_field);

        mIsHonestMode = isHonestMode;
        if (mIsHonestMode) {
            mAnswerField.setVisibility(View.GONE);
        } else {
            mAnswerField.setEnabled(false);
        }

        mUserAnswerField = mLayout.findViewById(R.id.user_answer);
        mUserAnswerField.setVisibility(View.GONE);

        mRightAnswerField = mLayout.findViewById(R.id.right_answer);
        mRightAnswerField.setVisibility(View.GONE);
    }

    @Override
    public void resetFields(ViewGroup layout) {
        mLayout = layout;
        boolean isEnabled = mAnswerField.isEnabled();
        int visibility = mAnswerField.getVisibility();
        mAnswerField = mLayout.findViewById(R.id.answer_field);
        mAnswerField.setEnabled(isEnabled);
        mAnswerField.setVisibility(visibility);

        visibility = mUserAnswerField.getVisibility();
        CharSequence text = mUserAnswerField.getText();
        mUserAnswerField = mLayout.findViewById(R.id.user_answer);
        mUserAnswerField.setVisibility(visibility);
        mUserAnswerField.setText(text);

        visibility = mRightAnswerField.getVisibility();
        text = mRightAnswerField.getText();
        mRightAnswerField = mLayout.findViewById(R.id.right_answer);
        mRightAnswerField.setVisibility(visibility);
        mRightAnswerField.setText(text);
    }

    @Override
    public void prepareField() {
        mRightAnswerField.setText("");
        mRightAnswerField.setVisibility(View.GONE);
        if(!mIsHonestMode) {
            mAnswerField.setVisibility(View.VISIBLE);
            mAnswerField.setText("");
            mAnswerField.setHint(mLayout.getContext().getString(R.string.hint_for_result));

            mUserAnswerField.setText("");
            mUserAnswerField.setVisibility(View.GONE);
        }
    }

    @Override
    public void pause() {
        mAnswerField.setEnabled(false);
    }

    @Override
    public void resume() {
        if(!mIsHonestMode) {
            mAnswerField.setVisibility(View.VISIBLE);
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
            mAnswerField.setHint("");
            mAnswerField.setEnabled(false);

            mUserAnswerField.setText("");
            mUserAnswerField.setVisibility(View.GONE);

            mRightAnswerField.setText("");
            mRightAnswerField.setVisibility(View.GONE);
        } else {
            mRightAnswerField.setText("");
            mRightAnswerField.setVisibility(View.GONE);
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
        String rightAnswer = String.format(mLayout.getContext().getString(R.string.right_answer_format), correctAnswer);
        mRightAnswerField.setVisibility(View.VISIBLE);
        mRightAnswerField.setText(rightAnswer);
    }

    private void showUserAndRightResult(String correctAnswer, CharSequence userAnswer) {
        if (! userAnswer.equals(correctAnswer)) {
            userAnswer = Helper.paintString(userAnswer, R.color.red, mLayout);
        }
        String begin = mLayout.getContext().getString(R.string.your);
        if (userAnswer instanceof SpannableStringBuilder) {
            userAnswer = new SpannableStringBuilder().append(begin).append(userAnswer);
        } else {
            userAnswer = begin + userAnswer;
        }
        mAnswerField.setVisibility(View.GONE);

        mUserAnswerField.setVisibility(View.VISIBLE);
        mUserAnswerField.setText(userAnswer);

        String rightAnswer = String.format(mLayout.getContext().getString(R.string.right_answer_format), correctAnswer);
        mRightAnswerField.setVisibility(View.VISIBLE);
        mRightAnswerField.setText(rightAnswer);
    }
}
