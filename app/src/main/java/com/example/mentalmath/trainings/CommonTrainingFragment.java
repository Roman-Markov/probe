package com.example.mentalmath.trainings;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mentalmath.R;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Роман on 07.09.2017.
 */

public class CommonTrainingFragment extends Fragment implements ITraining {

    private IStopWatchField mStopWatcherField;
    private IExampleDisplay mExampleDisplay;
    private IAnswerField mAnswerField;
    private ISessionResultField mSessionResult;
    private IExampleBuilder mExampleBuilder;

    private Button mStartButton;
    private Button mOkButton;
    private Button mPauseButton;
    private Button mRightButton;
    private Button mWrongButton;

    private HashSet<Button> mButtonList = new HashSet<Button>();


    private int mExampleAmount;
    private int mCounter;
    private int mRightCounter;

    private boolean mIsHonestMode = false;

    private String mCurrentAnswer = "";

    private TrainingState mState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle onSavedInstanceState) {
        super.onCreateView(inflater, container, onSavedInstanceState);
        View result = inflater.inflate(R.layout.common_training, container, false);

        mStartButton = (Button) result.findViewById(R.id.startButton);
        mOkButton = (Button) result.findViewById(R.id.okButton);
        mPauseButton = (Button) result.findViewById(R.id.pauseButton);
        mRightButton = (Button) result.findViewById(R.id.rightButton);
        mWrongButton = (Button) result.findViewById(R.id.wrongButton);

        addButtons(mStartButton, mOkButton, mPauseButton, mRightButton, mWrongButton);

        return result;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        setState(new InitialState(this));
    }

    public void onClick(View v) {
        mState.onClick(v);
    }

    public void setState(TrainingState state) {
        mState = state;
        mState.init();

    }

    public void startTraining() {
        mStopWatcherField.start();
        mAnswerField.prepareField();
        startExample();
    }

    public void startExample() {
        mExampleDisplay.showNewExample();
        mStopWatcherField.startExample();
    }

    public void pause () {
        mStopWatcherField.pause();
        mAnswerField.pause();
        mExampleDisplay.hideExample();
    }

    public void resume() {
        mStopWatcherField.resume();
        mAnswerField.resume();
        mExampleDisplay.showExample();
    }

    public void stopExample() {
        mCounter++;
        mStopWatcherField.stopExample();
        mExampleDisplay.hideExample();
        mCurrentAnswer = mAnswerField.getAnswer();
        handleResult(mCurrentAnswer);
        mAnswerField.clean();
        mSessionResult.addExampleResult();
    }

    public void stopTrain() {
        mStopWatcherField.stopAll();
        mSessionResult.addCommonResult(getCommonResult());
        mAnswerField.clean();
    }

    public void handleResult(String answer) {
        boolean isRight = false;
        if (answer != null) {
            isRight = mExampleBuilder.checkResult(answer);
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

    public boolean shouldProceed() {
        if (mCounter < mExampleAmount) {
            return true;
        } else {
            return false;
        }
    }

    public void showRightExampleResult(String result) {
        mAnswerField.showRightResult(result);
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

    private void addButtons(Button... buttons) {
        for (Button b: buttons) {
            mButtonList.add(b);
        }
    }

    abstract class TrainingState {
        ITraining mOwner;
        public TrainingState(ITraining train){
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
        public InitialState(ITraining fragment){
            super(fragment);
        }
        public void init() {
            setVisibileButton(mStartButton);
        }
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.startButton:

                    mOwner.startTraining();
                    setState(new StartedState(CommonTrainingFragment.this));
                    break;
                default: logWrongId();
            }
        }
    }

    class StartedState extends TrainingState {
        public StartedState(ITraining train){
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
                        setState(new HonestCheckState(CommonTrainingFragment.this));
                    } else {
                        if (shouldProceed()) {
                            startExample();
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
        public HonestCheckState(ITraining train){
            super(train);
            init();
        }
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rightButton:
                    mCounter++;
                case R.id.wrongButton:
                    setState(new StartedState(CommonTrainingFragment.this));
                    break;
                default: logWrongId();
            }
        }

        public void init() {
            pause();
            setVisibileButton(mRightButton, mWrongButton);
            showRightExampleResult(mCurrentAnswer);
        }
    }

    class PausedState extends TrainingState {
        public PausedState(ITraining train){
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
