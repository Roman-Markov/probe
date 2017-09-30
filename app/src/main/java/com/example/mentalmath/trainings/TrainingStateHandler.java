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

        init(train);
    }

    public TrainingStateHandler(IHonestTrain train, IControlButtonField buttonField) {
        mStartButton    = buttonField.getStartButton();
        mOkButton       = buttonField.getOkButton();
        mPauseButton    = buttonField.getPauseButton();
        mRightButton    = buttonField.getRightButton();
        mWrongButton    = buttonField.getWrongButton();

        init(train);
    }

    public void onClick(View v) {
        mCurrentState.onClick(v);
    }

    public void setHonestMode(boolean isEnabled) {
        mIsHonestMode = isEnabled;
    }

    private void setState(TrainingState state) {
        mCurrentState = state;
    }

    private void init(IHonestTrain train) {
        mButtonList.add(mStartButton);
        mButtonList.add(mOkButton);
        mButtonList.add(mPauseButton);
        mButtonList.add(mRightButton);
        mButtonList.add(mWrongButton);

        mCurrentState = new InitialState(train);
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
        protected void setVisibileButton(Button... buttons) {
            HashSet<Button> setOfButtons = new HashSet<Button>(Arrays.asList(buttons));
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
            setVisibileButton(mStartButton);
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
            setVisibileButton(mOkButton, mPauseButton);
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
            init();
        }
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rightButton:
                    int temp = mOwner.getRightAnswerCounter();
                    temp++;
                case R.id.wrongButton:
                    setState(new StartedState(mOwner));
                    break;
                default: logWrongId();
            }
        }

        public void init() {
            mOwner.pause();
            setVisibileButton(mRightButton, mWrongButton);
            mOwner.showRightExampleResult();
        }
    }

    class PausedState extends TrainingState {
        public PausedState(IHonestTrain train){
            super(train);
        }
        public void init() {
            setVisibileButton(mStartButton);
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
