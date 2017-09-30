package com.example.mentalmath.trainings;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        Button startButton    = result.findViewById(R.id.startButton);
        Button okButton       = result.findViewById(R.id.okButton);
        Button pauseButton    = result.findViewById(R.id.pauseButton);
        Button rightButton    = result.findViewById(R.id.rightButton);
        Button wrongButton    = result.findViewById(R.id.wrongButton);

        mStateHandler = new TrainingStateHandler(this,
                startButton, okButton, pauseButton, rightButton, wrongButton);
        ITrainingPartsFactory factory = getTrainingFactory((LinearLayout) result, new SimpleStopWatch());

        mStopWatcherField   = factory.getStopWatcherField();
        mExampleDisplay     = factory.getExampleDisplay();
        mAnswerField        = factory.getAnswerField();
        mSessionResult      = factory.getSessionResultField();
        mExampleAmount      = factory.getAmountOfExamles();

        return result;
    }

    public void onClick(View v) {
        mStateHandler.onClick(v);
    }


    @Override
    public void startTraining() {
        mRightCounter = 0;
        mCounter = 0;
        mStopWatcherField.start();
        mAnswerField.prepareField();
        startExample();
    }

    @Override
    public void startExample() {
        mExampleDisplay.showNewExample();
        mStopWatcherField.startExample();
    }

    public void pause () {
        mStopWatcherField.pause();
        mAnswerField.pause();
        mExampleDisplay.hideExample();
    }

    @Override
    public void resume() {
        mStopWatcherField.resume();
        mAnswerField.resume();
        mExampleDisplay.showExample();
    }

    @Override
    public void stopExample() {
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
        mStopWatcherField.stopAll();
        mSessionResult.addCommonResult(getCommonResult());
        mAnswerField.clean();
    }

    @Override
    public void handleResult(String answer) {
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
        if (mCounter < mExampleAmount) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void showRightExampleResult() {
        mAnswerField.showRightResult(mExampleDisplay.getExampleBuilder().getCurrentAnswer());
    }

    @Override
    public int getRightAnswerCounter() {
        return mRightCounter;
    }

    private void showToast(int stringId) {
        Toast toast = Toast.makeText(getActivity(), stringId, Toast.LENGTH_SHORT);
        // todo create dimension resource for third parameter
        toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 150);
        toast.show();
    }

    // todo
    private String getCommonResult() {
        return null;
    }

    //// TODO: 30.09.2017
    private ITrainingPartsFactory getTrainingFactory(LinearLayout parentLayout, IStopWatcher stopWatch) {
        return new ArithmeticTrainingPartsFactory(CommonTrainingFragment.this, parentLayout, stopWatch);
    }
}
