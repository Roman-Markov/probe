package com.example.mentalmath.trainings;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.LayoutRes;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mentalmath.R;
import com.example.mentalmath.core.Constants;
import com.example.mentalmath.core.Helper;
import com.example.mentalmath.core.Utils;
import com.example.mentalmath.trainings.fields.SimpleButtonField;
import com.example.mentalmath.trainings.fields.SimpleExampleDisplay;
import com.example.mentalmath.trainings.trainfactory.AdditionFactory;
import com.example.mentalmath.trainings.trainfactory.DivisionFactory;
import com.example.mentalmath.trainings.trainfactory.MultiplicationFactory;
import com.example.mentalmath.trainings.trainfactory.SubtractionFactory;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Роман on 07.09.2017.
 */

public class CommonTrainingFragment extends Fragment implements IHonestTrain {

    public static final String TAG = "CommonTrainingFragment";

    private IStopWatchField mStopWatcherField;
    private IExampleDisplay mExampleDisplay;
    private IAnswerField mAnswerField;
    private ISessionResultField mSessionResult;
    private IControlButtonField mButtonField;
    private LinearLayout mParentLayout;

    private TextView mCounterView;

    private int mExampleAmount;
    private int mCounter;
    private int mRightCounter;

    private boolean mIsHonestMode = false;
    private boolean mIsFirstRunning = true;
    private boolean mIsRunning = false;

    private TrainingStateHandler mStateHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle onSavedInstanceState) {
        super.onCreateView(inflater, container, onSavedInstanceState);
        setRetainInstance(true);

        int kind = getActivity().getIntent().getIntExtra(Constants.KEY_KIND_OF_TRAININGS, -1);
        int layoutId = getLayoutId(kind);

        mParentLayout = (LinearLayout) inflater.inflate(layoutId, container, false);

        if (mIsFirstRunning) {
            errorLog("first running");
            constructAllFields(inflater, mParentLayout, kind);
//            addAllToParent(mParentLayout);
            mCounterView = ((SimpleButtonField) mButtonField).getCounterView();
            mStateHandler = new TrainingStateHandler(this, mButtonField);
            mStateHandler.setHonestMode(mIsHonestMode);
            mIsFirstRunning = false;
        } else {
            resetAllFields(mParentLayout);
        }

        return mParentLayout;
    }

    @LayoutRes
    private int getLayoutId(int kind) {
        switch (kind) {
            case Constants.I_KIND_ARITH_ADDITION:
            case Constants.I_KIND_ARITH_SUBTRACTION:
            case Constants.I_KIND_ARITH_MULTIPLICATION:
                return R.layout.common_training;
            case Constants.I_KIND_ARITH_DIVISION:
                return R.layout.division_training;
            default:
                errorLog("Unknown type of training: " + kind);
                return R.layout.common_training;
        }
    }

    @Override
    public void onStop() {
        if (mIsRunning) {
            mStopWatcherField.pause();
            ((SimpleExampleDisplay) mExampleDisplay).onStrop();
        }
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        // was moved here from onCreateView() because when screen is switch off
        // method onCreateView() will not be called and stopwatch couldn't resume itself
        restore();
        ((SimpleExampleDisplay) mExampleDisplay).onStart();
    }

    private void restore() {
        if (mIsRunning) {
            mStopWatcherField.resume();
        }
    }

    public void onClick(View v) {
        mStateHandler.onClick(v);
    }

    @Override
    public void startTraining() {
        errorLog("Start hole training");
        mIsRunning = true;
        mRightCounter = 0;
        mCounter = 0;
        mStopWatcherField.start();
        mAnswerField.prepareField();
        mSessionResult.reset();
        setCounterView();
        startExample();
    }

    @Override
    public void startExample() {
        if (mIsHonestMode) {
            // on honest mode training counter should update after stopExample()
            setCounterView();
        }
        mAnswerField.clean();
        mAnswerField.resume();
        mIsRunning = true;
        errorLog("Start single example: " + mCounter);
        mExampleDisplay.showNewExample();
        mStopWatcherField.startExample();
    }

    @Override
    public void pause () {
        errorLog("Pause example" + mCounter);
        mStopWatcherField.pause();
        mIsRunning = false;
        mAnswerField.pause();
        mExampleDisplay.hideExample();
    }

    @Override
    public void resume() {
        errorLog("Resume example" + mCounter);
        mIsRunning = true;
        mStopWatcherField.resume();
        mAnswerField.resume();
        mExampleDisplay.showExample();
    }

    @Override
    public void stopExample() {
        mCounter++;
        errorLog("Stop example" + mCounter);
        mStopWatcherField.stopExample();
        // in case when example will be invisible after some time due to user prefs
        mExampleDisplay.showHiddenExample();

        mIsRunning = false;
//        mExampleDisplay.hideExample();
        String currentAnswer = mAnswerField.getAnswer();
        if (!mIsHonestMode) {
            handleResultInternally(currentAnswer);
            setCounterView();
        }
        // mAnswerField.clean() - it can be here but logic is that when we stop example we probably will want to see answer

        if (!shouldProceed() && !mIsHonestMode) {
            mSessionResult.addCommonResult(formTotalResult());
        }
    }

    @Override
    public void stopTrain() {
        errorLog("Stop hole train, counter - " + mCounter + ", right counter - " + mRightCounter);
        if (mIsHonestMode) {
            // startExample() will not be invoked, hence updating train counters must be here
            setCounterView();
            mSessionResult.addCommonResult(formTotalResult());
        } else {
            mExampleDisplay.hideExample();
        }
        mIsRunning = false;
        mStopWatcherField.stopAll();
        mAnswerField.clean();
    }

    @Override
    public void handleResult(String answer) {
        errorLog("Handle result: " + answer);
        boolean isRight = false;
        if (answer != null) {
            if (mIsHonestMode) {
                if (IHonestTrain.RIGHT.equals(answer)) {
                    isRight = true;
                    mRightCounter++;
                } else {
                    isRight = false;
                }
            } else {
                errorLog("This method should be called in honest mode only");
            }
        } else {
            throw new NullPointerException("passed result is equals null");
        }
        String time = mStopWatcherField.getCurrentExampleTime();
        mSessionResult.addExampleResult(time, isRight);
    }

    @Override
    public boolean shouldProceed() {
        errorLog("shouldProceed(): " + mCounter + " < " + mExampleAmount);
        return mCounter < mExampleAmount;
    }

    @Override
    public void showRightExampleResult() {
        errorLog("showRightExampleResult(): " + mExampleDisplay.getCurrentRightAnswer());
        mAnswerField.showRightResult(mExampleDisplay.getCurrentRightAnswer());
    }

    @Override
    public void hideInputMethod() {
        Utils.hideKeyBoard(getActivity());
    }

    @Override
    public void resetFocus() {
        mAnswerField.resetFocus();
    }

    /**
     * should not be called in honest mode state
     * @param answer
     */
    private void handleResultInternally(String answer) {
        if (mIsHonestMode) {
            errorLog("handleResultInternally() should not be called in honest mode state");
            return;
        }
        errorLog("Handle result internally: " + answer);
        boolean isRight = false;
        if (answer != null) {
            isRight = mExampleDisplay.checkResult(answer);

            if (isRight) {
                mRightCounter++;
                showToast(R.string.right);
            } else {
                showToast(R.string.wrong);
            }
        } else {
            showToast(R.string.noanswer);
        }
        String time = mStopWatcherField.getCurrentExampleTime();
        mSessionResult.addExampleResult(time, isRight);
    }

    private void setCounterView() {
        String amount = Integer.toString(mExampleAmount);
        String counter = Integer.toString(mCounter);
        CharSequence rightCounter = Integer.toString(mRightCounter);
        String result = String.format(getString(R.string.counterFormat), amount, counter, rightCounter);
        CharSequence commonResult = result;

        if (mRightCounter != mCounter) {
            CharSequence start = result.subSequence(0, result.lastIndexOf(Integer.toString(mRightCounter)));
            rightCounter = Helper.paintString(rightCounter, R.color.red, mCounterView);
            commonResult = Helper.insertStringToStart(start, (SpannableStringBuilder) rightCounter);
        }

        mCounterView.setText(commonResult);
    }

    // crates all fields
    private void constructAllFields(LayoutInflater inflater, ViewGroup container, int kind) {
        errorLog("constructAllFields():");

        ITrainingPartsFactory factory = getTrainingFactory(inflater, container, getActivity(), kind);

        mStopWatcherField   = factory.getStopWatcherField();
        mExampleDisplay     = factory.getExampleDisplay();
        mAnswerField        = factory.getAnswerField();
        mSessionResult      = factory.getSessionResultField();
        mButtonField        = factory.getButtonField();

        mExampleAmount      = factory.getAmountOfExamples();
        mIsHonestMode       = factory.isHonestModeEnabled();
    }

    private void resetAllFields(LinearLayout layout) {
        mStopWatcherField.resetFields(layout);
        mExampleDisplay.resetFields(layout);
        mAnswerField.resetFields(layout);
        mSessionResult.resetFields(layout);
        mButtonField.resetFields(layout);

        mStateHandler.setButtonField(mButtonField);

        CharSequence text = mCounterView.getText();
        int visibility = mCounterView.getVisibility();
        mCounterView = ((SimpleButtonField) mButtonField).getCounterView();
        mCounterView.setVisibility(visibility);
        mCounterView.setText(text);
    }

    // adds all fields in appropriate order
//    private void addAllToParent(LinearLayout result) {
//        errorLog("addAllToParent():");
//        result.addView(mStopWatcherField.getLayout());
//        result.addView(mExampleDisplay.getLayout());
//        result.addView(mAnswerField.getLayout());
//        result.addView(mButtonField.getLayout());
//        result.addView(mSessionResult.getLayout());
//    }

    private void showToast(int stringId) {
        errorLog("showToast():");
        Toast toast = Toast.makeText(getActivity(), stringId, Toast.LENGTH_SHORT);
        // todo create dimension resource for third parameter
        toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 150);
        toast.show();
    }

    // todo
    private String formTotalResult() {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format(getString(R.string.rightAnswers),
                mRightCounter, mExampleAmount));
        sb.append(String.format(getString(R.string.totalTimeFormat), mStopWatcherField.getTotalTime()));

        SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss:SS");
        timeFormat.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
        Date total = timeFormat.parse(mStopWatcherField.getTotalTime(), new ParsePosition(0));
        long totalTime = total.getTime();
        long averageTime = 0;

        if (mRightCounter != 0) {
            averageTime = totalTime / mRightCounter;
        }
        if (averageTime != 0) {
            Date average = new Date(averageTime);
            average.setTime(averageTime);
            sb.append(String.format(getString(R.string.averageTimeFormat), timeFormat.format(average)));
        } else {
            sb.append(String.format(getString(R.string.averageTimeFormat), "----"));
        }
        return sb.toString();
    }

    private ITrainingPartsFactory getTrainingFactory(LayoutInflater inflater, ViewGroup container, Activity activity, int kind) {
        switch (kind) {
            case Constants.I_KIND_ARITH_ADDITION:
                return new AdditionFactory(inflater, container, activity);
            case Constants.I_KIND_ARITH_SUBTRACTION:
                return new SubtractionFactory(inflater, container, activity);
            case Constants.I_KIND_ARITH_MULTIPLICATION:
                return new MultiplicationFactory(inflater, container, activity);
            case Constants.I_KIND_ARITH_DIVISION:
                return new DivisionFactory(inflater, container, activity);
            default:
                errorLog("Unknown type of training: " + kind);
        }
        return new AdditionFactory(inflater, container, activity);
    }

    private void errorLog(String msg) {
        Log.e(getClass().getSimpleName(), msg);
    }
}
