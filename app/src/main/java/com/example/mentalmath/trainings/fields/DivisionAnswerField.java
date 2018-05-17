package com.example.mentalmath.trainings.fields;

import android.preference.PreferenceManager;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mentalmath.R;
import com.example.mentalmath.core.Helper;
import com.example.mentalmath.trainings.ABaseField;
import com.example.mentalmath.trainings.IAnswerField;
import com.example.mentalmath.trainings.examplegenerator.DivisionBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Роман on 13.05.2018.
 */

public class DivisionAnswerField extends ABaseField implements IAnswerField {

    @BindView(R.id.result_answer_field)
    EditText mEditTextForResult;
    @BindView (R.id.result_answer_hint)
    TextView mTextViewForResult;

    @BindView(R.id.reminder_answer_field)
    EditText mEditTextForReminder;
    @BindView (R.id.reminder_answer_hint)
    TextView mTextViewForReminder;

    @BindView(R.id.right_result_hint)
    TextView mRightResultHint;
    @BindView(R.id.right_result_answer)
    TextView mRightResultTextView;

    @BindView(R.id.right_reminder_hint)
    TextView mRightReminderHint;
    @BindView(R.id.right_reminder_answer)
    TextView mRightReminderAnswerField;

    boolean mIsHonestMode;

    private DivisionAnswerState state;

    public DivisionAnswerField (LayoutInflater inflater, ViewGroup container, boolean isHonestMode) {

        super(inflater, container, R.layout.division_answer_field);
        mIsHonestMode = isHonestMode;

        ButterKnife.bind(this, mLayout);

        boolean withReminder = PreferenceManager.getDefaultSharedPreferences(Helper.mGlobalContext)
                .getBoolean(Helper.mGlobalContext.getString(R.string.divisionCbWithReminderKey), false);
        state = withReminder? new WithReminder() : new SimpleState();

        state.init();
    }

    @Override
    public void prepareField() {
        state.prepareField();
    }

    @Override
    public void pause() {
        state.pause();
    }

    @Override
    public void resume() {
        state.resume();
    }

    @Override
    public String getAnswer() {
        return state.getAnswer();
    }

    @Override
    public void clean() {
        state.clean();
    }

    @Override
    public void showRightResult(String correctAnswer) {
        state.showRightResult(correctAnswer);
    }

    private interface DivisionAnswerState {
        void init();
        void prepareField();
        void pause();
        void resume();
        String getAnswer();
        void clean();
        void showRightResult(final String answer);
    }

    private class SimpleState implements DivisionAnswerState {

        @Override
        public void init() {
            if (mIsHonestMode) {
                mTextViewForResult.setVisibility(View.GONE);
                mEditTextForResult.setVisibility(View.GONE);
            } else {
                mEditTextForResult.setEnabled(false);
            }
            mRightResultHint.setVisibility(View.GONE);
            mRightResultTextView.setVisibility(View.GONE);
        }

        @Override
        public void prepareField() {
            mRightResultTextView.setText("");
            mRightResultTextView.setVisibility(View.GONE);
            mRightResultHint.setVisibility(View.GONE);
            if(!mIsHonestMode) {
                mEditTextForResult.setVisibility(View.VISIBLE);
                mEditTextForResult.setText("");
            }
        }

        @Override
        public void pause() {
            mEditTextForResult.setEnabled(false);
        }

        @Override
        public void resume() {
            if(!mIsHonestMode) {
                mEditTextForResult.setEnabled(true);
            }
        }

        @Override
        public String getAnswer() {
            if(!mIsHonestMode) {
                return mEditTextForResult.getText().toString() + ":0"; // ":0" - reminder
            } else return "";
        }

        @Override
        public void clean() {
            mRightResultTextView.setText("");
            mRightResultTextView.setVisibility(View.GONE);
            mRightResultHint.setText(R.string.rightHint);
            mRightResultHint.setVisibility(View.GONE);
            if(!mIsHonestMode) {
                mEditTextForResult.setText("");
                mEditTextForResult.setEnabled(false);
            }
        }

        @Override
        public void showRightResult(final String correctAnswer) {
            CharSequence userAnswer = mEditTextForResult.getText().toString().trim();
            // in order to eliminate reminder after ":"
            String answer = correctAnswer.split(DivisionBuilder.SPLITTER)[0].trim();
            if (!mIsHonestMode) {
                showUserAndRightResult(answer, userAnswer);
            } else {
                showOnlyRightResult(answer);
            }
        }

        protected void showOnlyRightResult(final String correctAnswer) {
            mRightResultHint.setVisibility(View.VISIBLE);
            // since this method is used in honest mode check is excess
            mRightResultHint.setText(R.string.result);
            mRightResultTextView.setVisibility(View.VISIBLE);
            mRightResultTextView.setText(correctAnswer);
        }

        protected void showUserAndRightResult(final String correctAnswer, final CharSequence userAnswer) {
            CharSequence userString = null;
            if (! userAnswer.equals(correctAnswer)) {
                userString = Helper.paintString(userAnswer, R.color.red, mLayout);
            } else {
                userString = Helper.paintString(userAnswer, R.color.resultTextColor, mLayout);;
            }

            mEditTextForResult.setText(userString);
            mEditTextForResult.setEnabled(false);

            mRightResultHint.setVisibility(View.VISIBLE);
            mRightResultTextView.setVisibility(View.VISIBLE);
            mRightResultTextView.setText(correctAnswer);
        }
    }

    private class WithReminder extends SimpleState {

        @Override
        public void init() {
            super.init();
            if (mIsHonestMode) {
                mEditTextForReminder.setVisibility(View.GONE);
                mTextViewForReminder.setVisibility(View.GONE);
            } else {
                mEditTextForReminder.setVisibility(View.VISIBLE);
                mEditTextForReminder.setEnabled(false);
                mTextViewForReminder.setVisibility(View.VISIBLE);
            }
            mRightReminderHint.setVisibility(View.GONE);
            mRightReminderAnswerField.setVisibility(View.GONE);
        }

        @Override
        public void prepareField() {
            super.prepareField();
            mRightReminderHint.setVisibility(View.GONE);
            mRightReminderAnswerField.setText("");
            mRightReminderAnswerField.setVisibility(View.GONE);
            if(!mIsHonestMode) {
                mEditTextForReminder.setVisibility(View.VISIBLE);
                mEditTextForReminder.setText("");
                mTextViewForReminder.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void pause() {
            super.pause();
            mEditTextForReminder.setEnabled(false);
        }

        @Override
        public void resume() {
            super.resume();
            if(!mIsHonestMode) {
                mEditTextForReminder.setEnabled(true);
            }
        }

        @Override
        public String getAnswer() {
            if(!mIsHonestMode) {
                return super.getAnswer().split(DivisionBuilder.SPLITTER)[0]  // remove ":0"
                        + DivisionBuilder.SPLITTER + mEditTextForReminder.getText().toString();
            } else return "";
        }

        @Override
        public void clean() {
            super.clean();
            mRightReminderHint.setText(R.string.rightHint);
            mRightReminderHint.setVisibility(View.GONE);
            mRightReminderAnswerField.setText("");
            mRightReminderAnswerField.setVisibility(View.GONE);
            if(!mIsHonestMode) {
                mEditTextForReminder.setText("");
                mEditTextForReminder.setEnabled(false);
            }
        }

        @Override
        public void showRightResult(final String correctAnswer) {
            CharSequence userResultAnswer = mEditTextForResult.getText().toString().trim();
            CharSequence userReminderAnswer = mEditTextForReminder.getText().toString().trim();
            // in order to eliminate result before ":"
            String answerResult = correctAnswer.split(DivisionBuilder.SPLITTER)[0].trim();
            String answerReminder = correctAnswer.split(DivisionBuilder.SPLITTER)[1].trim();
            if (!mIsHonestMode) {
                super.showUserAndRightResult(answerResult, userResultAnswer);
                showUserAndRightResult(answerReminder, userReminderAnswer);
            } else {
                super.showOnlyRightResult(answerResult);
                showOnlyRightResult(answerReminder);
            }
        }

        protected void showOnlyRightResult(String correctAnswer) {
            mRightReminderHint.setVisibility(View.VISIBLE);
            // since this method is used in honest mode check is excess
            mRightReminderHint.setText(R.string.reminder);
            mRightReminderAnswerField.setVisibility(View.VISIBLE);
            mRightReminderAnswerField.setText(correctAnswer);
        }

        protected void showUserAndRightResult(String correctAnswer, final CharSequence userAnswer) {
            CharSequence userString;
            if (! userAnswer.equals(correctAnswer)) {
                userString = Helper.paintString(userAnswer, R.color.red, mLayout);
            } else {
                userString = Helper.paintString(userAnswer, R.color.resultTextColor, mLayout);;
            }

            mEditTextForReminder.setText(userString);
            mEditTextForReminder.setEnabled(false);

            mRightReminderHint.setVisibility(View.VISIBLE);
            mRightReminderAnswerField.setVisibility(View.VISIBLE);
            mRightReminderAnswerField.setText(correctAnswer);
        }
    }
}
