package com.example.mentalmath.trainings;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mentalmath.R;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Роман on 30.09.2017.
 */

public class TrainingStateHandler {

    private boolean mIsHonestMode = false;

    private Button mStartButton;
    private Button mOkButton;
    private Button mPauseButton;
    private Button mRightButton;
    private Button mWrongButton;
    private HashSet<Button> mButtonList = new HashSet<Button>();

    private TrainingState mCurrentState;

    public TrainingStateHandler(
            IHonestTrain train,
            Button startButton,
            Button okButton,
            Button pauseButton,
            Button rightButton,
            Button wrongButton){

        mStartButton    = startButton;
        mOkButton       = okButton;
        mPauseButton    = pauseButton;
        mRightButton    = rightButton;
        mWrongButton    = wrongButton;

        initializeHandler(train);
    }

    public TrainingStateHandler(IHonestTrain train, IControlButtonField buttonField) {
        mStartButton    = buttonField.getStartButton();
        mOkButton       = buttonField.getOkButton();
        mPauseButton    = buttonField.getPauseButton();
        mRightButton    = buttonField.getRightButton();
        mWrongButton    = buttonField.getWrongButton();

        initializeHandler(train);
    }

    public void onClick(View v) {
        mCurrentState.onClick(v);
    }

    public void setHonestMode(boolean isEnabled) {
        mIsHonestMode = isEnabled;
    }

    private void setState(TrainingState state) {

        Log.e(getClass().getSimpleName(), String.format("change state: %s  --->   %s",
                mCurrentState.getClass().getSimpleName(),  state.getClass().getSimpleName()));
        mCurrentState = state;
        mCurrentState.init();
    }

    private void initializeHandler(IHonestTrain train) {
        Log.d(getClass().getSimpleName(), "initializeHandler()");
        mButtonList.add(mStartButton);
        mButtonList.add(mOkButton);
        mButtonList.add(mPauseButton);
        mButtonList.add(mRightButton);
        mButtonList.add(mWrongButton);

        mCurrentState = new InitialState(train);
        mCurrentState.init();
    }



    abstract class TrainingState {
        IHonestTrain mOwner;
        public TrainingState(IHonestTrain train){
            mOwner = train;
        }
        public abstract  void onClick(View v);
        public abstract void init();
        protected void logWrongId() {
            Log.e(getClass().getSimpleName(), "Unexpected button id");
        }
        protected void setVisibleButton(Button... buttons) {
            HashSet<Button> setOfButtons = new HashSet<>(Arrays.asList(buttons));
            for (Button b: mButtonList) {
                if (setOfButtons.contains(b)) {
                    b.setVisibility(View.VISIBLE);
                } else {
                    b.setVisibility(View.GONE);
                }
            }
        }
    }
    class InitialState extends TrainingState {
        public InitialState(IHonestTrain fragment){
            super(fragment);
        }
        public void init() {
            setVisibleButton(mStartButton);
        }
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.startButton:

                    mOwner.startTraining();
                    setState(new StartedState(mOwner));
                    break;
                default: logWrongId();
            }
        }
    }

    class StartedState extends TrainingState {
        public StartedState(IHonestTrain train){
            super(train);
        }
        public void init() {
            setVisibleButton(mOkButton, mPauseButton);
        }
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.okButton:
                    mOwner.stopExample();
                    if (mIsHonestMode) {
                        setState(new HonestCheckState(mOwner));
                    } else {
                        if (mOwner.shouldProceed()) {
                            mOwner.startExample();
                        } else {
                            mOwner.stopTrain();
                            setState(new InitialState(mOwner));
                        }
                    }
                    break;
                case R.id.pauseButton:
                    mOwner.pause();
                    setState(new PausedState(mOwner));
                    break;
                default: logWrongId();
            }
        }
    }

    class HonestCheckState extends TrainingState {
        public HonestCheckState(IHonestTrain train){
            super(train);
        }
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rightButton:
                    int temp = mOwner.getRightAnswerCounter();
                    temp++;
                case R.id.wrongButton:
                    if (mOwner.shouldProceed()) {
                        mOwner.startExample();
                        setState(new StartedState(mOwner));
                    } else {
                        mOwner.stopTrain();
                        setState(new InitialState(mOwner));
                    }
                    break;
                default: logWrongId();
            }
        }

        public void init() {
            setVisibleButton(mRightButton, mWrongButton);
            mOwner.showRightExampleResult();
        }
    }

    class PausedState extends TrainingState {
        public PausedState(IHonestTrain train){
            super(train);
        }
        public void init() {
            setVisibleButton(mStartButton);
        }
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.startButton:
                    mOwner.resume();
                    setState(new StartedState(mOwner));
                    break;
                default:
                    break;
            }
        }
    }
}
