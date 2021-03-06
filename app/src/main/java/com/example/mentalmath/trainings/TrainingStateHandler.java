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

        mWrongButton.getContext();

        initializeHandler(train);
    }

    public void setButtonField(IControlButtonField buttonField) {
        mStartButton    = buttonField.getStartButton();
        mOkButton       = buttonField.getOkButton();
        mPauseButton    = buttonField.getPauseButton();
        mRightButton    = buttonField.getRightButton();
        mWrongButton    = buttonField.getWrongButton();
        initButtonList();
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
    }

    private void initializeHandler(IHonestTrain train) {
        Log.d(getClass().getSimpleName(), "initializeHandler()");
        initButtonList();
        mCurrentState = new InitialState(train);
    }

    private void initButtonList() {
        mButtonList.add(mStartButton);
        mButtonList.add(mOkButton);
        mButtonList.add(mPauseButton);
        mButtonList.add(mRightButton);
        mButtonList.add(mWrongButton);
    }

    abstract class TrainingState {
        IHonestTrain mOwner;
        public TrainingState(IHonestTrain train){
            mOwner = train;
            init();
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
                    mOwner.hideInputMethod();
                    mOwner.resetFocus();
                    mOwner.stopExample();
                    mOwner.showRightExampleResult();
                    if (mIsHonestMode) {
                        setState(new HonestCheckState(mOwner));
                    } else {
                        setState(new ShowAnswersAfterSetState(mOwner));
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
            String result = new String();
            switch (v.getId()) {
                case R.id.rightButton:
                    result = IHonestTrain.RIGHT;
                case R.id.wrongButton:
                    if (! IHonestTrain.RIGHT.equals(result)) {
                        result = IHonestTrain.WRONG;
                    }
                    mOwner.handleResult(result);
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

        }
    }

    class PausedState extends TrainingState {
        public PausedState(IHonestTrain train){
            super(train);
        }
        public void init() { setVisibleButton(mStartButton); }
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

    class ShowAnswersAfterSetState extends TrainingState {
        public ShowAnswersAfterSetState(IHonestTrain train){
            super(train);
        }
        public void init() {
            if (mOwner.shouldProceed()) {
                setVisibleButton(mStartButton);
            } else {
                setVisibleButton(mOkButton);
            }
        }
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.startButton:
                    mOwner.startExample();
                    setState(new StartedState(mOwner));
                    break;
                case R.id.okButton:
                    mOwner.stopTrain();
                    setState(new InitialState(mOwner));
                    break;
                default:
                    break;
            }
        }
    }
}
