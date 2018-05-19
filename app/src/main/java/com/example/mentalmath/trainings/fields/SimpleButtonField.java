package com.example.mentalmath.trainings.fields;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mentalmath.R;
import com.example.mentalmath.trainings.ABaseField;
import com.example.mentalmath.trainings.IControlButtonField;

/**
 * Created by Роман on 30.09.2017.
 */

public class SimpleButtonField extends ABaseField implements IControlButtonField {

    private Button mStartButton;
    private Button mOkButton;
    private Button mPauseButton;
    private Button mRightButton;
    private Button mWrongButton;


    public SimpleButtonField (LayoutInflater inflater, ViewGroup container) {
        super(inflater, container, R.layout.button_field);
        init();
    }

    public SimpleButtonField (ViewGroup container) {
        super(container, R.id.buttonField);
        init();
    }

    private void init() {
        mStartButton    = mLayout.findViewById(R.id.startButton);
        mOkButton       = mLayout.findViewById(R.id.okButton);
        mPauseButton    = mLayout.findViewById(R.id.pauseButton);
        mRightButton    = mLayout.findViewById(R.id.rightButton);
        mWrongButton    = mLayout.findViewById(R.id.wrongButton);
    }

    @Override
    public void resetFields(ViewGroup layout) {
        mLayout = layout;

        int visibility = mStartButton.getVisibility();
        mStartButton = mLayout.findViewById(R.id.startButton);
        mStartButton.setVisibility(visibility);

        visibility = mOkButton.getVisibility();
        mOkButton = mLayout.findViewById(R.id.okButton);
        mOkButton.setVisibility(visibility);

        visibility = mPauseButton.getVisibility();
        mPauseButton = mLayout.findViewById(R.id.pauseButton);
        mPauseButton.setVisibility(visibility);

        visibility = mRightButton.getVisibility();
        mRightButton = mLayout.findViewById(R.id.rightButton);
        mRightButton.setVisibility(visibility);

        visibility = mWrongButton.getVisibility();
        mWrongButton = mLayout.findViewById(R.id.wrongButton);
        mWrongButton.setVisibility(visibility);
    }

    @Override
    public Button getStartButton() {
        return mStartButton;
    }

    @Override
    public Button getOkButton() {
        return mOkButton;
    }

    @Override
    public Button getPauseButton() {
        return mPauseButton;
    }

    @Override
    public Button getRightButton() {
        return mRightButton;
    }

    @Override
    public Button getWrongButton() {
        return mWrongButton;
    }

    public TextView getCounterView() {
        return mLayout.findViewById(R.id.counter_view);
    }

}
