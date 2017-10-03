package com.example.mentalmath.trainings;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mentalmath.R;

/**
 * Created by Роман on 07.09.2017.
 */

public class CommonTrainingFragment extends Fragment implements IHonestTrain {

    private IStopWatchField mStopWatcherField;
    private IExampleDisplay mExampleDisplay;
    private IAnswerField mAnswerField;
    private ISessionResultField mSessionResult;
    private IControlButtonField mButtonField;
    private LinearLayout mParentLayout;

    private int mExampleAmount;
    private int mCounter;
    private int mRightCounter;

    private boolean mIsHonestMode = false;
    private boolean mIsFirstRunning = true;

    private String mCurrentAnswer = "";

    private TrainingStateHandler mStateHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle onSavedInstanceState) {
        super.onCreateView(inflater, container, onSavedInstanceState);
        setRetainInstance(true);
        if (mIsFirstRunning) {
            mIsFirstRunning = false;

            mParentLayout = (LinearLayout) inflater.inflate(R.layout.common_training, container, false);

            constructAllFields(inflater, container);
            addAllToParent((LinearLayout) mParentLayout);
            mStateHandler = new TrainingStateHandler(this, mButtonField);
            mStateHandler.setHonestMode(mIsHonestMode);
        } else if (onSavedInstanceState != null){
            restore(onSavedInstanceState);
        }

        return mParentLayout;
    }

    @Override
    public void onStop() {
        mStopWatcherField.pause();
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void restore(Bundle savedInstanceState) {
        mStopWatcherField.resume();
    }


    public void onClick(View v) {
        mStateHandler.onClick(v);
    }


    @Override
    public void startTraining() {
        errorLog("Start hole training");
        mRightCounter = 0;
        mCounter = 0;
        mStopWatcherField.start();
        mAnswerField.prepareField();
        startExample();
    }

    @Override
    public void startExample() {
        errorLog("Start single example: " + mCounter);
        mExampleDisplay.showNewExample();
        mStopWatcherField.startExample();
    }

    public void pause () {
        errorLog("Pause example" + mCounter);
        mStopWatcherField.pause();
        mAnswerField.pause();
        mExampleDisplay.hideExample();
    }

    @Override
    public void resume() {
        errorLog("Resume example" + mCounter);
        mStopWatcherField.resume();
        mAnswerField.resume();
        mExampleDisplay.showHiddenExample();
    }

    @Override
    public void stopExample() {
        mCounter++;
        errorLog("Stop example" + mCounter);
        mStopWatcherField.stopExample();
        mExampleDisplay.hideExample();
        mCurrentAnswer = mAnswerField.getAnswer();
        handleResult(mCurrentAnswer);
        mAnswerField.clean();
        mSessionResult.addExampleResult();
    }

    @Override
    public void stopTrain() {
        errorLog("Stop hole train, counter - " + mCounter + ", right counter - " + mRightCounter);
        mStopWatcherField.stopAll();
        mExampleDisplay.hideExample();
        mAnswerField.clean();
        mSessionResult.addCommonResult(getCommonResult());

    }

    @Override
    public void handleResult(String answer) {
        errorLog("Handle result: " + answer);
        boolean isRight = false;
        if (answer != null) {
            isRight = mExampleDisplay.getExampleBuilder().checkResult(answer);
            if (isRight) {
                mRightCounter++;
                showToast(R.string.right);
            } else {
                showToast(R.string.wrong);
            }
        } else {
            showToast(R.string.noanswer);
        }
    }

    @Override
    public boolean shouldProceed() {
        errorLog("shouldProceed(): " + mCounter + " < " + mExampleAmount);
        return mCounter < mExampleAmount;
    }

    @Override
    public void showRightExampleResult() {
        errorLog("showRightExampleResult(): " + mExampleDisplay.getExampleBuilder().getCurrentAnswer());
        mAnswerField.showRightResult(mExampleDisplay.getExampleBuilder().getCurrentAnswer());
    }

    @Override
    public int getRightAnswerCounter() {
        return mRightCounter;
    }

    // crates all fields
    private void constructAllFields(LayoutInflater inflater, ViewGroup container) {
        errorLog("constructAllFields():");

        ITrainingPartsFactory factory = getTrainingFactory(inflater, container, new SimpleStopWatch());

        mStopWatcherField   = factory.getStopWatcherField();
        mExampleDisplay     = factory.getExampleDisplay();
        mAnswerField        = factory.getAnswerField();
        mSessionResult      = factory.getSessionResultField();
        mButtonField        = new SimpleButtonField(inflater, container);

        mExampleAmount      = factory.getAmountOfExamles();
        mIsHonestMode       = factory.isHonestModeEnabled();
    }

    // adds all fields in appropriate order
    private void addAllToParent(LinearLayout result) {
        errorLog("addAllToParent():");
        result.addView(mStopWatcherField.getLayout());
        result.addView(mExampleDisplay.getLayout());
        result.addView(mAnswerField.getLayout());
        result.addView(mButtonField.getLayout());
        result.addView(mSessionResult.getLayout());
    }

    private void showToast(int stringId) {
        errorLog("showToast():");
        Toast toast = Toast.makeText(getActivity(), stringId, Toast.LENGTH_SHORT);
        // todo create dimension resource for third parameter
        toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 150);
        toast.show();
    }

    // todo
    private String getCommonResult() {
        return null;
    }

    // todo
    private ITrainingPartsFactory getTrainingFactory(LayoutInflater inflater, ViewGroup container, IStopWatch stopWatch) {
        errorLog("getTrainingFactory():");
        return new ArithmeticTrainingPartsFactory(CommonTrainingFragment.this, inflater, container, stopWatch);
    }

    private void errorLog(String msg) {
        Log.e(getClass().getSimpleName(), msg);
    }
}
