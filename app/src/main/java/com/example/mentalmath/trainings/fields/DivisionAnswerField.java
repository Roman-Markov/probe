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
    @BindView(R.id.reminder_answer_field)
    EditText mEditTextForReminder;

    @BindView(R.id.user_result_answer)
    TextView mUserResultTextView;
    @BindView(R.id.user_reminder_answer)
    TextView mUserReminderTextView;

    @BindView(R.id.right_result_answer)
    TextView mRightResultTextView;
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
                mEditTextForResult.setVisibility(View.GONE);
            } else {
                mEditTextForResult.setEnabled(false);
            }
            mUserResultTextView.setVisibility(View.GONE);
            mRightResultTextView.setVisibility(View.GONE);
        }

        @Override
        public void prepareField() {
            mRightResultTextView.setText("");
            mRightResultTextView.setVisibility(View.GONE);
            if(!mIsHonestMode) {
                mEditTextForResult.setVisibility(View.VISIBLE);
                mEditTextForResult.setText("");
                mEditTextForResult.setHint(mLayout.getContext().getString(R.string.hint_for_result));

                mUserResultTextView.setText("");
                mUserResultTextView.setVisibility(View.GONE);
            }
        }

        @Override
        public void pause() {
            mEditTextForResult.setEnabled(false);
        }

        @Override
        public void resume() {
            if(!mIsHonestMode) {
                mEditTextForResult.setVisibility(View.VISIBLE);
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
            if(!mIsHonestMode) {
                mEditTextForResult.setText("");
                mEditTextForResult.setHint("");
                mEditTextForResult.setEnabled(false);

                mUserResultTextView.setText("");
                mUserResultTextView.setVisibility(View.GONE);

                mRightResultTextView.setText("");
                mRightResultTextView.setVisibility(View.GONE);
            } else {
                mRightResultTextView.setText("");
                mRightResultTextView.setVisibility(View.GONE);
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
            String rightAnswer = String.format(mLayout.getContext().getString(R.string.right_answer_format), correctAnswer);
            mRightResultTextView.setVisibility(View.VISIBLE);
            mRightResultTextView.setText(rightAnswer);
        }

        protected void showUserAndRightResult(final String correctAnswer, final CharSequence userAnswer) {
            CharSequence userString = null;
            if (! userAnswer.equals(correctAnswer)) {
                userString = Helper.paintString(userAnswer, R.color.red, mLayout);
            }
            String begin = mLayout.getContext().getString(R.string.your_result);
            if (userString != null && userString instanceof SpannableStringBuilder) {
                userString = new SpannableStringBuilder().append(begin).append(userAnswer);
            } else {
                userString = begin + userAnswer;
            }
            mEditTextForResult.setVisibility(View.GONE);

            mUserResultTextView.setVisibility(View.VISIBLE);
            mUserResultTextView.setText(userString);

            String rightAnswer = String.format(mLayout.getContext().getString(R.string.right_answer_format), correctAnswer);
            mRightResultTextView.setVisibility(View.VISIBLE);
            mRightResultTextView.setText(rightAnswer);
        }
    }

    private class WithReminder extends SimpleState {

        @Override
        public void init() {
            super.init();
            if (mIsHonestMode) {
                mEditTextForReminder.setVisibility(View.GONE);
            } else {
                mEditTextForReminder.setVisibility(View.VISIBLE);
                mEditTextForReminder.setEnabled(false);
            }
            mUserReminderTextView.setVisibility(View.GONE);
            mRightReminderAnswerField.setVisibility(View.GONE);
        }

        @Override
        public void prepareField() {
            super.prepareField();
            mRightReminderAnswerField.setText("");
            mRightReminderAnswerField.setVisibility(View.GONE);
            if(!mIsHonestMode) {
                mEditTextForReminder.setVisibility(View.VISIBLE);
                mEditTextForReminder.setText("");
                mEditTextForReminder.setHint(mLayout.getContext().getString(R.string.hint_for_reminder));

                mUserReminderTextView.setText("");
                mUserReminderTextView.setVisibility(View.GONE);
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
                mEditTextForReminder.setVisibility(View.VISIBLE);
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
            mRightReminderAnswerField.setText("");
            mRightReminderAnswerField.setVisibility(View.GONE);
            if(!mIsHonestMode) {
                mEditTextForReminder.setText("");
                mEditTextForReminder.setHint("");
                mEditTextForReminder.setEnabled(false);

                mUserReminderTextView.setText("");
                mUserReminderTextView.setVisibility(View.GONE);
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
            String rightAnswer = String.format(mLayout.getContext().getString(R.string.right_answer_format), correctAnswer);
            mRightReminderAnswerField.setVisibility(View.VISIBLE);
            mRightReminderAnswerField.setText(rightAnswer);
        }

        protected void showUserAndRightResult(String correctAnswer, final CharSequence userAnswer) {
            CharSequence userString = null;
            if (! userAnswer.equals(correctAnswer)) {
                userString = Helper.paintString(userAnswer, R.color.red, mLayout);
            }
            String begin = mLayout.getContext().getString(R.string.your_reminder);
            if (userString != null && userString instanceof SpannableStringBuilder) {
                userString = new SpannableStringBuilder().append(begin).append(userAnswer);
            } else {
                userString = begin + userAnswer;
            }
            mEditTextForReminder.setVisibility(View.GONE);

            mUserReminderTextView.setVisibility(View.VISIBLE);
            mUserReminderTextView.setText(userString);

            String rightAnswer = String.format(mLayout.getContext().getString(R.string.right_answer_format), correctAnswer);
            mRightReminderAnswerField.setVisibility(View.VISIBLE);
            mRightReminderAnswerField.setText(rightAnswer);
        }
    }
}
