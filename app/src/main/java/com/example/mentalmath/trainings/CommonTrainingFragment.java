package com.example.mentalmath.trainings;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mentalmath.R;

/**
 * Created by Роман on 07.09.2017.
 */

public class CommonTrainingFragment extends Fragment {

    private TrainingState mState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle onSavedInstanceState) {
        super.onCreateView(inflater, container, onSavedInstanceState);
        View result = inflater.inflate(R.layout.common_training, container, false);
        return result;
    }

    public void onClick(View v) {
        mState.onClick(v);
    }

    public void setState(TrainingState state) {
        mState = state;
    }

    public void startTraining() {}

    public void startExample() {}

    public void pause () {}

    public void stopExample() {}

    public void stopTrain() {}


    abstract class TrainingState {
        CommonTrainingFragment mOwner;
        public TrainingState(CommonTrainingFragment fragment){
            mOwner = fragment;
        }
        public abstract  void onClick(View v);
        public void logWrongId() {
            Log.e(getClass().getSimpleName(), "Unexpected button id");
        }
    }
    class InitialState extends TrainingState {
        public InitialState(CommonTrainingFragment fragment){
            super(fragment);
        }
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.startButton:
                    break;
                default: logWrongId();
            }
        }
    }

    class StartedState extends TrainingState {
        public StartedState(CommonTrainingFragment fragment){
            super(fragment);
        }
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.okButton:
                    break;
                case R.id.pauseButton:
                    break;
                default: logWrongId();
            }
        }
    }

    class HonestCheckState extends TrainingState {
        public HonestCheckState(CommonTrainingFragment fragment){
            super(fragment);
        }
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pauseButton:
                    break;
                case R.id.rightButton:
                    break;
                case R.id.wrongButton:
                    break;
                default: logWrongId();
            }
        }
    }

    class PausedState extends TrainingState {
        public PausedState(CommonTrainingFragment fragment){
            super(fragment);
        }
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.startButton:
                    break;
                default:
                    break;
            }
        }
    }
}
