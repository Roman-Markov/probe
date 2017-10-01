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

    private int mExampleAmount;
    private int mCounter;
    private int mRightCounter;

    private boolean mIsHonestMode = false;

    private String mCurrentAnswer = "";

    private TrainingStateHandler mStateHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle onSavedInstanceState) {
        super.onCreateView(inflater, container, onSavedInstanceState);
        View result = inflater.inflate(R.layout.common_training, container, false);

        constructAllFields((LinearLayout) result);
        addAllToParent((LinearLayout) result);

        mStateHandler = new TrainingStateHandler(this, mButtonField);
        mStateHandler.setHonestMode(mIsHonestMode);

        return result;
    }



    public void onClick(View v) {
        mStateHandler.onClick(v);
    }


    @Override
    public void startTraining() {
        debugLog("Start hole training");
        mRightCounter = 0;
        mCounter = 0;
        mStopWatcherField.start();
        mAnswerField.prepareField();
        startExample();
    }

    @Override
    public void startExample() {
        debugLog("Start single example: " + mCounter);
        mExampleDisplay.showNewExample();
        mStopWatcherField.startExample();
    }

    public void pause () {
        debugLog("Pause example" + mCounter);
        mStopWatcherField.pause();
        mAnswerField.pause();
        mExampleDisplay.hideExample();
    }

    @Override
    public void resume() {
        debugLog("Resume example" + mCounter);
        mStopWatcherField.resume();
        mAnswerField.resume();
        mExampleDisplay.showExample();
    }

    @Override
    public void stopExample() {
        debugLog("Stop example" + mCounter);
        mCounter++;
        mStopWatcherField.stopExample();
        mExampleDisplay.hideExample();
        mCurrentAnswer = mAnswerField.getAnswer();
        handleResult(mCurrentAnswer);
        mAnswerField.clean();
        mSessionResult.addExampleResult();
    }

    @Override
    public void stopTrain() {
        debugLog("Stop hole train, counter - " + mCounter + ", right counter - " + mRightCounter);
        mStopWatcherField.stopAll();
        mSessionResult.addCommonResult(getCommonResult());
        mAnswerField.clean();
    }

    @Override
    public void handleResult(String answer) {
        debugLog("Handle result: " + answer);
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
        debugLog("shouldProceed(): " + mCounter + " < " + mExampleAmount);
        return mCounter < mExampleAmount;
    }

    @Override
    public void showRightExampleResult() {
        debugLog("showRightExampleResult(): " + mExampleDisplay.getExampleBuilder().getCurrentAnswer());
        mAnswerField.showRightResult(mExampleDisplay.getExampleBuilder().getCurrentAnswer());
    }

    @Override
    public int getRightAnswerCounter() {
        return mRightCounter;
    }

    // crate all fields
    private void constructAllFields(LinearLayout parent) {
        debugLog("constructAllFields():");

        LinearLayout layout = parent.findViewById(R.layout.stopwatch_field);
        errorLog("layout: " + layout.toString());

        ITrainingPartsFactory factory = getTrainingFactory(parent, new SimpleStopWatch());

        mStopWatcherField   = factory.getStopWatcherField();
        mExampleDisplay     = factory.getExampleDisplay();
        mAnswerField        = factory.getAnswerField();
        mSessionResult      = factory.getSessionResultField();
        mButtonField        = new SimpleButtonField(parent);

        mExampleAmount      = factory.getAmountOfExamles();
        mIsHonestMode       = factory.isHonestModeEnabled();
    }

    // add all fields in appropriate order
    private void addAllToParent(LinearLayout result) {
        debugLog("addAllToParent():");
        result.addView(mStopWatcherField.getLayout());
        result.addView(mExampleDisplay.getLayout());
        result.addView(mAnswerField.getLayout());
        result.addView(mButtonField.getLayout());
        result.addView(mSessionResult.getLayout());
    }

    private void showToast(int stringId) {
        debugLog("showToast():");
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
    private ITrainingPartsFactory getTrainingFactory(LinearLayout parentLayout, IStopWatcher stopWatch) {
        debugLog("getTrainingFactory():");
        return new ArithmeticTrainingPartsFactory(CommonTrainingFragment.this, parentLayout, stopWatch);
    }

    private void debugLog(String msg) {
        Log.d(getClass().getSimpleName(), msg);
    }

    private void errorLog(String msg) {
        Log.e(getClass().getSimpleName(), msg);
    }
}
